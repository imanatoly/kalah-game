package net.kalah.util;

import java.util.UUID;

/**
 * @author ahmety on 13.09.2017.
 */
public class IdUtil {

    public static String createId() {
        return UUID.randomUUID().toString();
    }
}
