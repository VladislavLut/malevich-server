package com.malevich.server.repository;

import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import com.malevich.server.view.Views;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TablesRepository extends JpaRepository<TableItem, Integer> {

    @JsonView(Views.Public.class)
    Optional<TableItem> findTableById(int id);

    List<TableItem> findAllByOpenedIsTrue();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE tables SET opened = :opened WHERE id = :id", nativeQuery = true)
    int updateStatus(@Param("id") int id, @Param("opened") boolean opened);
}
