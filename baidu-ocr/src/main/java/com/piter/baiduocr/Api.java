package com.piter.baiduocr;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.util.Base64Util;
import com.sun.deploy.net.URLEncoder;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 百度OCR,API方式请求
 */
public class Api {


    public static void main(String[] args) throws IOException {
        String image = "C:\\Users\\Piter\\Desktop\\新建文件夹\\正面1.jpg";
        image = Base64Util.encode(readFileByBytes(image));
//        image = URLEncoder.encode(image, "UTF-8");
//        image = getImageStrFromPath("C:\\Users\\Piter\\Desktop\\新建文件夹\\正面1.jpg");
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("image", image);
        postParameters.add("id_card_side", "back");

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(postParameters, headers);
        //token有效期为30天
        String accessToken = "24.49c76387ac3a75d4b8c5af50eca60b48.2592000.1564194377.282335-16485927";
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + accessToken;

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, request, JSONObject.class);
        JSONObject body = responseEntity.getBody();
        System.out.println(body);
    }

    /**
     * 将图片转换为字节数组
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                int bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                boolean var6 = false;

                int len;
                while (-1 != (len = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }

    public static String getImageStrFromPath(String imgPath) {
        InputStream in;
        byte[] data = null;
// 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
// 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
// 返回Base64编码过再URLEncode的字节数组字符串
        try {
            return URLEncoder.encode(encoder.encode(data), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}


