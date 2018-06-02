package com.malevich.server.controller;


import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import com.malevich.server.exception.SessionAlreadyOpened;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.UUID;

import static com.malevich.server.util.Encode.sha256;
import static com.malevich.server.util.SessionUtil.generateSID;
import static com.malevich.server.util.ValidationUtil.validateCredentials;
import static com.malevich.server.util.ValidationUtil.validateSid;

@RestController
public class SessionController {

    @Autowired
    private final SessionsRepository sessionsRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    public SessionController(SessionsRepository sessionsRepository, UsersRepository usersRepository) {
        this.sessionsRepository = sessionsRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping(value = "/users/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        int userId = validateUserCredentials(user.getLogin(), user.getPassword());
        validateNewSession(userId);
        String sid = generateSID(userId);
        sessionsRepository.save(new Session(new User(userId), sid));
        response.addCookie(new Cookie("sid", sid));
    }

    @GetMapping(value = "/users/logout")
    public void logout(@CookieValue("sid") String sid) {
        validateSid(sessionsRepository, sid);
        this.sessionsRepository.deleteSession(sid);
    }

    private void validateNewSession(int userId) {
        if (this.sessionsRepository.findSessionByUserId(userId).isPresent()) {
            throw new SessionAlreadyOpened();
        }
    }

    private int validateUserCredentials(String login, String password) {
        return validateCredentials(usersRepository, login, password);
    }

}
