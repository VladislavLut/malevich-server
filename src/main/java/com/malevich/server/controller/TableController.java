package com.malevich.server.controller;

import com.malevich.server.controller.exception.EntityAlreadyExistException;
import com.malevich.server.controller.exception.EntityNotFoundException;
import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.repository.TablesRepository;
import org.aspectj.apache.bcel.generic.TABLESWITCH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/")
    public List<TableItem> findAllTables() {
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<TableItem> findTableById(@PathVariable int id) {
        validateTable(id);

        return this.tablesRepository.findTableById(id);
    }

    @GetMapping("/{id}/lastOrder")
    public Order findLastOrder(@PathVariable int id) {
        validateTable(id);

        List<Order> orders = this.tablesRepository.findTableById(id).get().getOrders();

        return orders.get(orders.size() - 1);
    }


    @PostMapping("/add")
    public ResponseEntity<?> saveTable(@RequestBody TableItem tableItem) {
        if (this.tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(this.getClass(), tableItem.getId());
        }

        this.tablesRepository.save(tableItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    @Transactional
    public ResponseEntity<?> updateTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.deleteById(tableItem.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateTable(int id) {
        this.tablesRepository.findTableById(id)
                .orElseThrow(() -> new EntityNotFoundException(this.getClass(), id));
    }

}
