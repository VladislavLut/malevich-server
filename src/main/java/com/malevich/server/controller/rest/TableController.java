package com.malevich.server.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.TableItem;
import com.malevich.server.enums.Response;
import com.malevich.server.exception.EntityAlreadyExistException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.TablesRepository;
import com.malevich.server.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.malevich.server.controller.rest.SessionController.SID;
import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.*;
import static com.malevich.server.util.OrderUtil.removeNotActualInfo;
import static com.malevich.server.util.Strings.TABLES_ID_COLUMN;
import static com.malevich.server.util.ValidationUtil.validateAccess;
import static org.apache.logging.log4j.util.Chars.QUOTE;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TablesRepository tablesRepository;

    private final SessionsRepository sessionsRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public TableController(final TablesRepository tablesRepository,
                           final SessionsRepository sessionsRepository,
                           final SimpMessageSendingOperations messagingTemplate) {
        this.tablesRepository = tablesRepository;
        this.sessionsRepository = sessionsRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/all")
    public List<TableItem> findAllTables(@CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, true);
        List<TableItem> tableItems = tablesRepository.findAllTablesExeptsDelivery();
        for (TableItem tableItem : tableItems) {
            removeNotActualInfo(tableItem);
        }
        return tableItems;
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/{id}/")
    public TableItem findTableById(@PathVariable int id, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, KITCHENER, TABLE);
        validateTable(id);

        TableItem table = tablesRepository.findTableById(id).get();
        removeNotActualInfo(table);
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
    public TableItem saveTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR);
        if (tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    getClass().toString(),
                    TABLES_ID_COLUMN + SPACE_QUOTE + tableItem.getId() + QUOTE);
        }

        tablesRepository.save(tableItem);
        return tableItem;
    }

    @PostMapping("/update")
    public TableItem updateTable(@RequestBody TableItem tableItem, @CookieValue(name = SID) String sid) {
        validateAccess(sessionsRepository, sid, ADMINISTRATOR, TABLE);
        validateTable(tableItem.getId());

        tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());
        messagingTemplate.convertAndSend("/topic/public", tableItem);
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
                        getClass().toString(), TABLES_ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

}
