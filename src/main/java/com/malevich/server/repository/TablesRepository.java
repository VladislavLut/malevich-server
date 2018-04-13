package com.malevich.server.repository;

import com.malevich.server.entity.Order;
import com.malevich.server.entity.TableItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TablesRepository extends JpaRepository<TableItem, Integer> {

    Optional<TableItem> findTableById(int id);

    List<TableItem> findAllByOpenedIsTrue();

    @Modifying(clearAutomatically = true)
    @Query("update TableItem t set t.opened = :opened where t.id = :id")
    int updateStatus(@Param("id") int id, @Param("opened") boolean opened);
}
