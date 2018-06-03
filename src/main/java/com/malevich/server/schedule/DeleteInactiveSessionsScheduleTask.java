package com.malevich.server.schedule;

import com.malevich.server.ServerApplication;
import com.malevich.server.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;

import static sun.plugin2.util.SystemUtil.isDebug;

@Component
public class DeleteInactiveSessionsScheduleTask {

    private static final long TIMEOUT = 600000; // 10 minutes
    private static final long PERIOD = 60000; // 1 minute

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(initialDelay = PERIOD, fixedDelay = PERIOD)
    public void scheduledTask() {
        String sql = "SELECT COUNT(*) FROM " + Session.TABLE_NAME + " s WHERE s.last_activity < ?";
        int size = jdbcTemplate.queryForObject(sql, new Object[]{new Time(System.currentTimeMillis() - TIMEOUT)}, Integer.class);
        if (isDebug()) {
            ServerApplication.log.info("Inactive sessions count: " + size);
        }

        sql = "DELETE FROM " + Session.TABLE_NAME + " s WHERE s.last_activity < ?";
        jdbcTemplate.update(sql, new Time(System.currentTimeMillis() - TIMEOUT));
        if (isDebug()) {
            ServerApplication.log.info("Inactive sessions has been closed");
        }
    }
}
