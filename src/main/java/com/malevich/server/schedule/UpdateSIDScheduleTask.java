package com.malevich.server.schedule;

import com.malevich.server.ServerApplication;
import com.malevich.server.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;

import static com.malevich.server.schedule.DeleteInactiveSessionsScheduleTask.PERIOD;
import static com.malevich.server.schedule.DeleteInactiveSessionsScheduleTask.TIMEOUT;
import static com.malevich.server.util.SessionUtil.generateSID;

@Component
public class UpdateSIDScheduleTask {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = PERIOD)
    public void scheduledTask() {
//        String sql = "SELECT * FROM " + Session.TABLE_NAME + " s WHERE (? - s.start_time) % ?";
//        List<Session> sessions = jdbcTemplate.queryForList(sql, new Object[]{new Time(System.currentTimeMillis()), TIMEOUT}, Session.class);
//
//        sql = "UPDATE " + Session.TABLE_NAME + " SET s.sid = ? WHERE s.sid LIKE ?";
//        for (Session session : sessions) {
//            jdbcTemplate.update(sql, generateSID(), session.getSid());
//        }
//        ServerApplication.log.info("Sessions sid's has been updated");
    }
}
