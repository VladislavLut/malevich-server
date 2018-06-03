package com.malevich.server.util;

import com.malevich.server.entity.Session;

import java.sql.Time;
import java.util.Calendar;
import java.util.UUID;

import static com.malevich.server.util.Encode.sha256;

public class SessionUtil {
    public static String generateSID() {
        return sha256(UUID.randomUUID().toString() + Calendar.getInstance().getTimeInMillis());
    }

}
