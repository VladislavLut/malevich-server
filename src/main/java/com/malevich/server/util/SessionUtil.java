package com.malevich.server.util;

import java.util.Calendar;
import java.util.UUID;

import static com.malevich.server.util.EncodeUtil.sha256;

public class SessionUtil {
    public static String generateSID() {
        return sha256(UUID.randomUUID().toString() + Calendar.getInstance().getTimeInMillis());
    }

}
