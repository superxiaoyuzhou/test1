package com.piter.mybatisplus.utils;

import java.util.Base64;

public class AES {
    public static String encrypt(String src) {
        try {
            String result = Base64.getEncoder().encodeToString(src.getBytes("UTF-8"));
            return result;
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    public static String decrypt(String src) {
        try {
            byte[] asBytes = Base64.getDecoder().decode(src);
            String result = new String(asBytes,"UTF-8");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }
}

