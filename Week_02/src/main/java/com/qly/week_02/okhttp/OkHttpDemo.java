package com.qly.week_02.okhttp;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpDemo {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, HashMap<String, String> hashMap) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (!hashMap.isEmpty()) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                return "";
            }
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        OkHttpDemo demo = new OkHttpDemo();
        System.out.println("This is okhttp test");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "zhangsan");
        hashMap.put("age", "24");
        String response = demo.post("http://localhost:8801/post", hashMap);
        System.out.println(response);
    }
}
