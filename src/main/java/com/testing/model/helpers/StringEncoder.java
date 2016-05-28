package com.testing.model.helpers;

import java.security.MessageDigest;

/**
 * Created by Study on 25.05.2016.
 */
public class StringEncoder {

    private StringEncoder(){}

    public static String HashString(String str) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
    }
}
