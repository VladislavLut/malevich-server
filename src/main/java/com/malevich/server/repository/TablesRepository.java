package com.malevich.server.repository;

import com.fasterxml.jackson.annotation.JsonView;
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

    @Transactional
    @Query("select t from TableItem t where t.id <> -1 order by t.id")
    List<TableItem> findAllTablesExeptsDelivery();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE tables SET opened = :opened WHERE id = :id", nativeQuery = true)
    int updateStatus(@Param("id") int id, @Param("opened") boolean opened);
}
