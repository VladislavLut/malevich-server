package com.malevich.server.controller;

import com.malevich.server.entity.TableItem;
import com.malevich.server.http.response.status.exception.EntityAlreadyExistException;
import com.malevich.server.http.response.status.exception.EntityNotFoundException;
import com.malevich.server.http.response.status.exception.OkException;
import com.malevich.server.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.malevich.server.controller.UserController.QUOTE;
import static com.malevich.server.controller.UserController.SPACE_QUOTE;
import static com.malevich.server.entity.TableItem.OPENED_COLUMN;
import static com.malevich.server.http.response.status.exception.OkException.*;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private final TablesRepository tablesRepository;

    @Autowired
    public TableController(final TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    @GetMapping("/all-tables")
    public List<TableItem> findAllTables() {
        return this.tablesRepository.findAll();
    }

    @GetMapping("/{id}/")
    public Optional<TableItem> findTableById(@PathVariable int id) {
        validateTable(id);

        return this.tablesRepository.findTableById(id);
    }

    @PostMapping("/active-order")
    public ModelAndView activeOrder(HttpServletRequest request) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:http://localhost:8080/orders/active-order");
    }


    @PostMapping("/add")
    public void saveTable(@RequestBody TableItem tableItem) {
        if (this.tablesRepository.findById(tableItem.getId()).isPresent()) {
            throw new EntityAlreadyExistException(
                    this.getClass().toString(),
                    TableItem.ID_COLUMN + SPACE_QUOTE + tableItem.getId() + QUOTE);
        }

        this.tablesRepository.save(tableItem);

        throw new OkException(SAVED, this.getClass().toString());
    }

    @PostMapping("/update-status")
    public void updateTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.updateStatus(tableItem.getId(), tableItem.isOpened());

        throw new OkException(UPDATED, this.getClass().toString(), OPENED_COLUMN);
    }

    @PostMapping("/remove")
    public void removeTable(@RequestBody TableItem tableItem) {
        validateTable(tableItem.getId());

        this.tablesRepository.deleteById(tableItem.getId());

        throw new OkException(REMOVED, this.getClass().toString());
    }

    private void validateTable(int id) {
        this.tablesRepository.findTableById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        this.getClass().toString(), TableItem.ID_COLUMN + SPACE_QUOTE + id + QUOTE));
    }

}
