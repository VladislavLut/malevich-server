package com.malevich.server.schedule;

import com.malevich.server.ServerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;

import static com.malevich.server.util.Strings.SESSIONS_TABLE_NAME;

@Component
public class DeleteInactiveSessionsScheduleTask {

    private static final long TIMEOUT = 600000; // 10 minutes
    private static final long PERIOD = 60000; // 1 minute

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeleteInactiveSessionsScheduleTask(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedDelay = PERIOD)
    public void scheduledTask() {
        Time time = Time.valueOf(new Time(System.currentTimeMillis() - TIMEOUT).toString());

        String sql = "SELECT COUNT(*) FROM " + SESSIONS_TABLE_NAME;
        int size = jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);

        sql = "DELETE FROM " + SESSIONS_TABLE_NAME + " s WHERE s.last_activity < ? and s.logged_in = false";
        //jdbcTemplate.update(sql, time);
        ServerApplication.log.info("close inactive sessions...");

        sql = "SELECT COUNT(*) FROM " + SESSIONS_TABLE_NAME;
        size -= jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
        ServerApplication.log.info(size + "  sessions closed");
    }
}
