package com.qly.week_02.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HttpClientDemo {

    private void doPost() throws UnsupportedEncodingException {
        CloseableHttpClient client = HttpClientFactory.getFactory().getHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8801/post");
        ArrayList<NameValuePair> list = new ArrayList<>();
        BasicNameValuePair b1 = new BasicNameValuePair("name", "zhangsan");
        BasicNameValuePair b2 = new BasicNameValuePair("age", "24");
        //BasicNameValuePair b2 = new BasicNameValuePair("age", "25");
        list.add(b1);
        list.add(b2);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(entity.getContentLength());
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        HttpClientDemo demo = new HttpClientDemo();
        demo.doPost();
    }
}
