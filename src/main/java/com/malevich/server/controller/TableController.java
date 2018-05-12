package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.repository.OrdersRepository;
import com.malevich.server.repository.TablesRepository;
import com.malevich.server.utils.Response;
import com.malevich.server.utils.Status;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private final TablesRepository tablesRepository;

    @Autowired
    private final OrdersRepository ordersRepository;

    @Autowired
    public TableController(final TablesRepository tablesRepository, final OrdersRepository ordersRepository) {
        this.tablesRepository = tablesRepository;
        this.ordersRepository = ordersRepository;
    }

    @GetMapping("/all")
    public List<TableItem> findAllTables() {
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Pair<TableItem, Order> findTableById(@PathVariable int id) {
        validateTable(id);


        TableItem table = this.tablesRepository.findTableById(id).get();
        Order order = this.ordersRepository
                .findFirstByTableItemIdAndStatusNotLike(id, Status.CLOSED)
                .orElse(new Order(id));

        return new Pair<>(table, order);
    }

    @PostMapping("/add")
    public String saveTable(@RequestBody TableItem tableItem) {
        if (this.tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    TableItem.ID_COLUMN + SPACE_QUOTE + tableItem.getId() + QUOTE);
        }

        this.tablesRepository.save(tableItem);
        return Response.SAVED.name();
    }

    @PostMapping("/update")
    public String updateTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());
        return Response.UPDATED.name();
    }

    @PostMapping("/remove")
    public String removeTable(@RequestBody TableItem tableItem) {
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
