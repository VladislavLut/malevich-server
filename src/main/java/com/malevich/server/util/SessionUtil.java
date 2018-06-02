package com.malevich.server.util;

import java.util.Calendar;
import java.util.UUID;

import static com.malevich.server.util.Encode.sha256;

public class SessionUtil {
    public static String generateSID(Integer id) {
        return sha256(UUID.randomUUID().toString() + id + Calendar.getInstance().getTimeInMillis());
    }
}
