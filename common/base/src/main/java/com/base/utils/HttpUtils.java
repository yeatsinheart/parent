package com.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class HttpUtils {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final long timeout = 5000;

    public static void main(String[] args) {
        System.out.println(get("http://www.baidu.com", null));
        System.out.println(postByForm("https://www.baidu.com", null, null));
    }

    public static String get(String url, Map<String, Object> headers) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url));
        return response(headers, builder);
    }

    public static String getParams(Map<String, Object> params) {
        String requestBody = CollectionUtils.isEmpty(params) ? "" : params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
        return requestBody;
    }

    public static String postByJson(String url, Map<String, Object> params, Map<String, Object> headers) {
        String requestBody = JsonUtil.toJsonStr(params);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(url))
                .header("Content-Type", "application/json");// 设置头部参数，内容类型为json;
        return response(headers, builder);
    }

    public static String postByForm(String url, Map<String, Object> params, Map<String, Object> headers) {
        String requestBody = CollectionUtils.isEmpty(params) ? null : params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");// 设置头部参数，内容类型为json;
        return response(headers, builder);
    }

    private static String response(Map<String, Object> headers, HttpRequest.Builder builder) {
        String[] h = CollectionUtils.isEmpty(headers) ? new String[0] : headers.entrySet().stream().flatMap(e -> Stream.of(e.getKey(), e.getValue())).collect(Collectors.toList()).toArray(String[]::new);
        if (h.length != 0 && h.length % 2 != 0) {
            builder.headers(h);
        }
        HttpRequest request = builder
                .timeout(Duration.ofMillis(timeout))
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            log.error("请求发送失败", e);
            return null;
        }
        return response.body();
    }

}
