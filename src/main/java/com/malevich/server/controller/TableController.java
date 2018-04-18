package com.malevich.server.controller;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private final TablesRepository tablesRepository;

    @Autowired
    public TableController(final TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    @GetMapping("/all")
    public List<TableItem> findAllTables() {
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<TableItem> findTableById(@PathVariable int id) {
        validateTable(id);

        return this.tablesRepository.findTableById(id);
    }

    @GetMapping("/{id}/last-order")
    public Order findLastOrder(@PathVariable int id) {

//todo: replace this shit with something adequate :/

        validateTable(id);

        List<Order> orders = this.tablesRepository.findTableById(id).get().getOrders();

        return orders.get(orders.size() - 1);
    }


    @PostMapping("/add")
    public void saveTable(@RequestBody TableItem tableItem) {
        if (this.tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), "id '" + tableItem.getId() + "'");
        }

        this.tablesRepository.save(tableItem);

        throw new OkException("table saved in the database");
    }

    @PostMapping("/update")
    @Transactional
    public void updateTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());

        throw new OkException("table updated");
    }

    @PostMapping("/remove")
    public void removeTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.deleteById(tableItem.getId());

        throw new OkException("reservation removed from the database");
    }

    private void validateTable(int id) {
        this.tablesRepository.findTableById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), "id '" + id + "'."));
    }

}
