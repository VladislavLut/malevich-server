package com.malevich.server.repository;

import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TablesRepository extends JpaRepository<TableItem, Integer> {

    Optional<TableItem> findTableById(int id);

//    List<TableItem> findAllByOpenedIsTrue();
//
//    List<TableItem> findAllByIdIsNotNull();
//
}
