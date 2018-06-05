package com.malevich.server.controller;


import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import com.malevich.server.exception.SessionIsAlreadyOpened;
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
    public void login(@RequestBody User user, @CookieValue(SID) String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        int userId = validateNewSession(user);
        sessionsRepository.update(userId, true, new Time(System.currentTimeMillis()), sid);
        response.addCookie(new Cookie(SID, sid));
    }

    @PostMapping(value = "/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        int userId = validateNewSession(user);
        String sid = generateSID();
        sessionsRepository.save(new Session(new User(userId), sid, true));
        response.addCookie(new Cookie(SID, sid));
    }

    @GetMapping(value = "/logout")
    public void logout(@CookieValue("sid") String sid, HttpServletResponse response) {
        validateSid(sessionsRepository, sid);
        sessionsRepository.update(false, new Time(System.currentTimeMillis()), sid);
        response.addCookie(new Cookie(SID, sid));
    }

    private int validateNewSession(User user) {
        int userId = validateUserCredentials(user.getLogin(), user.getPassword());
        if (this.sessionsRepository.findSessionByUserId(userId).isPresent()) {
            throw new SessionIsAlreadyOpened();
        }
        return userId;
    }

    private int validateUserCredentials(String login, String password) {
        return validateCredentials(usersRepository, login, password);
    }

}
