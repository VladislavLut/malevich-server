package com.malevich.server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.enums.Response;
import com.malevich.server.enums.Status;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.repository.ReservedRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.TablesRepository;
import com.malevich.server.service.AdminClientService;
import com.malevich.server.util.JsonUtil;
import com.malevich.server.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.malevich.server.controller.SessionController.SID;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/tables")
public class TableController {

    private static final int SERVER_PORT = 3444;

    @Autowired
    private final TablesRepository tablesRepository;

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    private final ReservedRepository reservedRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    private AdminClientService adminClientService;

    @Autowired
    public TableController(final TablesRepository tablesRepository,
                           final OrdersRepository ordersRepository,
                           final ReservedRepository reservedRepository,
                           final SessionsRepository sessionsRepository) {
        this.tablesRepository = tablesRepository;
        this.ordersRepository = ordersRepository;
        this.reservedRepository = reservedRepository;
        this.sessionsRepository = sessionsRepository;
        adminClientService = new AdminClientService(SERVER_PORT);
    }

    @GetMapping("/all")
    public List<TableItem> findAllTables(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return tablesRepository.findAll();
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/{id}/")
    public TableItem findTableById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateTable(id);

        TableItem table = tablesRepository.findTableById(id).get();
        table.setOrders(new ArrayList<>());
        table.getOrders().add(ordersRepository
                .findFirstByTableItemIdAndStatusNotLike(id, Status.CLOSED)
                .orElse(new Order(id)));
        table.setReservations(reservedRepository.findAllByDateAndTableItemId(
                new Date(System.currentTimeMillis()),
                table.getId()
        ).orElse(new ArrayList<>()));
        return table;
    }

    @GetMapping("/{id}/open")
    public TableItem openTable(@PathVariable Integer id, @CookieValue(name = SID) String sid) {
        return updateTable(new TableItem(id, true), sid);
    }

    @GetMapping("/{id}/close")
    public TableItem closeTable(@PathVariable Integer id, @CookieValue(name = SID) String sid) {
        return updateTable(new TableItem(id, false), sid);
    }

    @PostMapping("/add")
    public String saveTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    getClass().toString(),
                    TableItem.ID_COLUMN + SPACE_QUOTE + tableItem.getId() + QUOTE);
        }

        tablesRepository.save(tableItem);
        return Response.SAVED.name();
    }

    @PostMapping("/update")
    public TableItem updateTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE);
        validateTable(tableItem.getId());

        tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());
        adminClientService.send(JsonUtil.toJson(tableItem));
        return tableItem;
    }

    @PostMapping("/remove")
    public String removeTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateTable(tableItem.getId());

        tablesRepository.deleteById(tableItem.getId());
        return Response.REMOVED.name();
    }

    private void validateTable(int id) {
        tablesRepository.findTableById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        getClass().toString(), TableItem.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

}
