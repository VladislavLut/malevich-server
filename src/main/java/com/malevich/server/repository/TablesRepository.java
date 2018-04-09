package com.malevich.server.repository;

import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TablesRepository extends JpaRepository<TableItem, Integer> {

    Optional<TableItem> findTableById(int id);

    List<TableItem> findAllByOpenedIsTrue();

    @Modifying(clearAutomatically = true)
    @Query("update tables t set t.isOpened = :isOpened where t.id = :id")
    int updateIsOpened(@Param("id") int id, @Param("isOpened") String isOpened);
}
