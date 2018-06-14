package com.malevich.server.controller.rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.UsersRepository;
import com.malevich.server.view.Views;
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

    private final SessionsRepository sessionsRepository;

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
        response.addCookie(createCookie(sid));
    }

    @GetMapping(value = "/session/end")
    public void endSession(@CookieValue(SID) String sid) {
        validateSid(sessionsRepository, sid);
        this.sessionsRepository.delete(sid);
    }

    @JsonView(Views.Public.class)
    @PostMapping(value = "/session/login")
    public User login(@RequestBody User user, @CookieValue(SID) String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        Cookie sidCookie = openSession(user, sid);
        response.addCookie(sidCookie);
        return usersRepository.findUserByLogin(user.getLogin()).get();
    }

    @JsonView(Views.Public.class)
    @PostMapping(value = "/login")
    public User login(@RequestBody User user, HttpServletResponse response) {
        Cookie sid = openSession(user, null);
        response.addCookie(sid);
        return usersRepository.findUserByLogin(user.getLogin()).get();
    }

    @GetMapping(value = "/logout")
    public void logout(@CookieValue(SID) String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        sessionsRepository.update(false, new Time(System.currentTimeMillis()), sid);
        response.addCookie(new Cookie(SID, sid));
    }

    private Cookie openSession(User user, String sid) {
        user.setId(getUserIdByCredentials(user.getLogin(), user.getPassword(), user.getPhone()));
        Session session = this.sessionsRepository.findSessionByUserId(user.getId())
                .orElse(this.sessionsRepository.findSessionBySid(sid)
                        .orElse(null));
        return (session == null) ? createNewSession(user) : updateExistingSession(user, session);
    }

    private Cookie updateExistingSession(User user, Session session) {
        String sid = session.getSid();
        String newSid = sid;/*generateSID();*/
        sessionsRepository.update(user, true, new Time(System.currentTimeMillis()), newSid, sid);
        return createCookie(newSid);
    }

    private Cookie createNewSession(User user) {
        String sid = generateSID();
        sessionsRepository.save(new Session(user, sid, true));
        return createCookie(sid);
    }

    private Cookie createCookie(String sid) {
        Cookie sidCookie = new Cookie(SID, sid);
        sidCookie.setPath("/");
        return sidCookie;
    }

    private int getUserIdByCredentials(String login, String password, String phone) {
        validateCredentials(usersRepository, login, password, phone);
        return usersRepository.findUserByLogin(login).get().getId();
    }

}
