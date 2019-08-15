package com.nextyu.pay.wx.util;

import java.util.UUID;

/**
 * created on 2017-04-18 13:59
 *
 * @author nextyu
 */
public class UUIDUtil {
    private UUIDUtil() {
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
