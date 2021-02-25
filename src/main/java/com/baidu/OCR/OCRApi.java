package com.baidu.OCR;


import com.alibaba.fastjson.JSONObject;
import com.baidu.translate.demo.MD5;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class OCRApi {
    private static final String OCR_API_HOST = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
    private static final String ACCESS_TOKEN_API_HOST = "https://aip.baidubce.com/oauth/2.0/token";
    private OkHttpClient client = new OkHttpClient();

    @Value("${baidu.ocr.api_key}")
    private String api_key;
    @Value("${baidu.ocr.secret_key}")
    private String secret_key;

    public String getAccessToken() {
        String request_url = ACCESS_TOKEN_API_HOST + "?grant_type=client_credentials&client_id=" + this.api_key + "&client_secret=" + this.secret_key;
//        System.out.println("Get ACCESS_TOKEN: " + request_url);
        Request request = new Request.Builder()
                .url(request_url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("访问OCR服务器失败！");
            }
            JSONObject json = JSONObject.parseObject(response.body().string());
            return (String) json.get("access_token");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getOCRResult(String imgBase64, String langType) {
        RequestBody formBody = new FormBody.Builder()
                .add("image", imgBase64)
                .add("language_type", langType)
                .build();
        String accessToken = this.getAccessToken();
        String request_url = OCR_API_HOST + "?access_token=" + accessToken;
        Request request = new Request.Builder().url(request_url)
                .header("content-type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("访问OCR服务器失败！");
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }
}
