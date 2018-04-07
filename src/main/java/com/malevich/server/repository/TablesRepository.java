package com.malevich.server.repository;

import com.malevich.server.entity.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TablesRepository extends CrudRepository<Table, Integer> {

    Optional<Table> findTableById(int id);

    List<Table> findAllByOpenedIsTrue();

    List<Table> findAllByIdIsNotNull();
}
