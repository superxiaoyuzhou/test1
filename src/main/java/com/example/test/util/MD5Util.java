package com.example.test.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * md5加密产生，产生128位（bit）的mac
     * 将128bit Mac转换成16进制代码
     * @param strSrc
     * @param key
     * @return
     */
    public static String MD5Encode(String strSrc, String key) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String result = "";
            byte[] temp;
            temp = md5.digest((strSrc + key).getBytes("UTF8"));

//            System.out.println("temp--------->temp:"+temp.length);
            for (int i = 0; i < temp.length; i++) {
                result += Integer.toHexString(
                        (0x000000ff & temp[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String  json_data = "";   //待签名 字符串
        String  securekey = "123456";  //秘钥
        String  sign = MD5Encode(json_data,securekey).toUpperCase();  //签名
        System.out.println(sign);
    }
}
