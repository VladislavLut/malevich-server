package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.TablesRepository;
import com.malevich.server.util.Response;
import com.malevich.server.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.malevich.server.controller.SessionController.SID;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.util.UserType.*;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private final TablesRepository tablesRepository;

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    public TableController(final TablesRepository tablesRepository,
                           final OrdersRepository ordersRepository,
                           final SessionsRepository sessionsRepository) {
        this.tablesRepository = tablesRepository;
        this.ordersRepository = ordersRepository;
        this.sessionsRepository = sessionsRepository;
    }

    @GetMapping("/all")
    public List<TableItem> findAllTables(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Pair<TableItem, Order> findTableById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateTable(id);

        TableItem table = this.tablesRepository.findTableById(id).get();
        Order order = this.ordersRepository
                .findFirstByTableItemIdAndStatusNotLike(id, Status.CLOSED)
                .orElse(new Order(id));

        return Pair.of(table, order);
    }

    @PostMapping("/add")
    public String saveTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (this.tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    TableItem.ID_COLUMN + SPACE_QUOTE + tableItem.getId() + QUOTE);
        }

        this.tablesRepository.save(tableItem);
        return Response.SAVED.name();
    }

    @PostMapping("/update")
    public String updateTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateTable(tableItem.getId());

        this.tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());
        return Response.UPDATED.name();
    }

    @PostMapping("/remove")
    public String removeTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        validateTable(tableItem.getId());

        this.tablesRepository.deleteById(tableItem.getId());
        return Response.REMOVED.name();
    }

    private void validateTable(int id) {
        this.tablesRepository.findTableById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), TableItem.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

}
