package com.malevich.server.controller;

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
    public List<TableItem> getAllTables() {
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<TableItem> getTable(@PathVariable int id) {
        validateTable(id);

        return this.tablesRepository.findTableById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveTable(@RequestBody TableItem tableItem) {
        try {
            validateTable(tableItem.getId());
        } catch (TableNotFoundException e) {
            this.tablesRepository.save(tableItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        throw new TableAlreadyExistException(tableItem.getId());
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
                .orElseThrow( () -> new TableNotFoundException(id));
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(int id) {
        super("could not found table '" + id + "'.");
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class TableAlreadyExistException extends RuntimeException {
    public TableAlreadyExistException(int id) {
        super("table already exist '" + id + "'.");
    }
}