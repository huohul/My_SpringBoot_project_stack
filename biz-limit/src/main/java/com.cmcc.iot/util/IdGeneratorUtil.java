package com.cmcc.iot.util;

import java.util.UUID;

/**
 * @author Administrator
 */
public class IdGeneratorUtil {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

}