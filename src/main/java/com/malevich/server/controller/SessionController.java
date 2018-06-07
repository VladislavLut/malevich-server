package com.malevich.server.controller;


import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;

import static com.malevich.server.util.SessionUtil.generateSID;
import static com.malevich.server.util.ValidationUtil.validateCredentials;
import static com.malevich.server.util.ValidationUtil.validateSid;

@RestController
public class SessionController {

    public static final String SID = "sid";

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    public SessionController(SessionsRepository sessionsRepository, UsersRepository usersRepository) {
        this.sessionsRepository = sessionsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping(value = "/session/start")
    public void startSession(HttpServletResponse response) {
        String sid = generateSID();
        sessionsRepository.save(new Session(sid));
        response.addCookie(new Cookie(SID, sid));
    }

    @GetMapping(value = "/session/end")
    public void endSession(@CookieValue(SID) String sid) {
        validateSid(sessionsRepository, sid);
        this.sessionsRepository.delete(sid);
    }

    @PostMapping(value = "/session/login")
    public User login(@RequestBody User user, @CookieValue(SID) String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        sid = openSession(user, sid);
        response.addCookie(new Cookie(SID, sid));
        return usersRepository.findUserByLogin(user.getLogin()).get();
    }

    @PostMapping(value = "/login")
    public User login(@RequestBody User user, HttpServletResponse response) {
        String sid = openSession(user, null);
        response.addCookie(new Cookie(SID, sid));
        return usersRepository.findUserByLogin(user.getLogin()).get();
    }

    @GetMapping(value = "/logout")
    public void logout(@CookieValue("sid") String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        sessionsRepository.update(false, new Time(System.currentTimeMillis()), sid);
        response.addCookie(new Cookie(SID, sid));
    }

    private String openSession(User user, String sid) {
        user.setId(getUserIdByCredentials(user.getLogin(), user.getPassword()));
        Session session = this.sessionsRepository.findSessionByUserId(user.getId())
                .orElse(this.sessionsRepository.findSessionBySid(sid)
                        .orElse(null));
        if (session == null) {
            sid = generateSID();
            sessionsRepository.save(new Session(user, sid, true));
        } else {
            sid = session.getSid();
            sessionsRepository.update(user, true, new Time(System.currentTimeMillis()), sid);
        }
        return sid;
    }

    private int getUserIdByCredentials(String login, String password) {
        validateCredentials(usersRepository, login, password);
        return usersRepository.findUserByLogin(login).get().getId();
    }

}
