package com.malevich.server.repository;

import com.malevich.server.entity.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TablesRepository extends CrudRepository<Table, Integer> {

    List<Table> findAllByOpenedIsTrue();

    Table findTableById(int id);

    List<Table> findAllByIdIsNotNull();
}
