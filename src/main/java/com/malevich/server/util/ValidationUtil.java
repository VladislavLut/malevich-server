package com.malevich.server.util;

import com.malevich.server.entity.Session;
import com.malevich.server.entity.User;
import com.malevich.server.enums.UserType;
import com.malevich.server.exception.AccessDeniedException;
import com.malevich.server.exception.EntityNotFoundException;
import com.malevich.server.exception.UnauthorizedAccessException;
import com.malevich.server.exception.WrongPasswordException;
import com.malevich.server.repository.SessionsRepository;
import com.malevich.server.repository.UsersRepository;

import java.sql.Time;

import static com.malevich.server.controller.rest.UserController.SPACE_QUOTE;
import static com.malevich.server.enums.UserType.ADMINISTRATOR;
import static com.malevich.server.util.EncodeUtil.encodePassword;
import static com.malevich.server.util.Strings.USERS_LOGIN_COLUMN;
import static org.apache.logging.log4j.util.Chars.QUOTE;

public class ValidationUtil {

    public static void validateSid(SessionsRepository repository, String sid) {
        getSession(repository, sid);
    }

    private static Session getSession(SessionsRepository repository, String sid) {
        return repository.findSessionBySid(sid).orElseThrow(UnauthorizedAccessException::new);
    }

    public static void validateCredentials(UsersRepository usersRepository, String login, String password, String phone) {
        User user = usersRepository
                .findUserByLoginOrPhoneAndPhoneIsNotNull(login, phone)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getName(), USERS_LOGIN_COLUMN + SPACE_QUOTE + login + QUOTE));
        if (!user.getPassword().equals(encodePassword(user.getLogin(), password))) {
            throw new WrongPasswordException();
        }
    }

    public static void validateAccess(SessionsRepository sessionsRepository, String sid, UserType... types) {
        validateAccess(sessionsRepository, sid, false, types);
    }

    public static void validateAccess(SessionsRepository sessionsRepository, String sid, boolean freeAccess, UserType... types) {

        Session session = getSession(sessionsRepository, sid);
        updateSession(sessionsRepository, sid, session);
        if (freeAccess) {
            return;
        }
        for (UserType type : types) {
            if (isUserTypeEquals(session, type)) {
                return;
            }
        }
        throw new AccessDeniedException();
    }

    private static void updateSession(SessionsRepository sessionsRepository, String sid, Session session) {
        Time currentTime = new Time(System.currentTimeMillis());
//        String newSid = getTimePeriod(session.getStartTime(), currentTime) > TIMEOUT
//                ? generateSID()
//                : sid;
        String newSid = sid;
        sessionsRepository.update(currentTime, newSid, sid);
    }

    private static boolean isUserTypeEquals(Session session, UserType type) {
        return session.getUser().getType() == type
                || session.getUser().getType() == ADMINISTRATOR;
    }
}
