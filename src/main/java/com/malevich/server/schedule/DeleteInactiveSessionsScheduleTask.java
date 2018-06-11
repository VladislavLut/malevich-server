package com.malevich.server.schedule;

import com.malevich.server.ServerApplication;
import com.malevich.server.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class DeleteInactiveSessionsScheduleTask {

    public static final long TIMEOUT = 600000; // 10 minutes
    private static final long PERIOD = 60000; // 1 minute

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = PERIOD)
    public void scheduledTask() {
        Time time = Time.valueOf(new Time(System.currentTimeMillis() - TIMEOUT).toString());

        String sql = "SELECT COUNT(*) FROM " + Session.TABLE_NAME + " s WHERE s.last_activity < ?";
        int size = jdbcTemplate.queryForObject(sql, new Object[]{time}, Integer.class);
        ServerApplication.log.info("inactive sessions count: " + size);

        sql = "DELETE FROM " + Session.TABLE_NAME + " s WHERE s.last_activity < ?";
        jdbcTemplate.update(sql, time);
        ServerApplication.log.info("close inactive sessions");
    }
}
