package com.qly.week_02.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientFactory {

    private static HttpClientFactory factory = new HttpClientFactory();

    public static HttpClientFactory getFactory() {
        return factory;
    }

    public CloseableHttpClient getHttpClient() {
        return new HttpClientBuilder().getHttpClient();
    }

    private static class HttpClientBuilder {
        private CloseableHttpClient httpClient;

        private CloseableHttpClient getHttpClient() {
            if (httpClient == null) {
                RequestConfig rc = RequestConfig.custom()
                        .setConnectTimeout(3000)
                        .setSocketTimeout(10000)
                        .build();
                httpClient = HttpClients.custom().setDefaultRequestConfig(rc).build();
            }
            return httpClient;
        }
    }

}
