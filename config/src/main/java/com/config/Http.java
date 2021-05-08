package com.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Http {
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory(), x509TrustManager())
            .retryOnConnectionFailure(false)//是否开启缓存
            .connectionPool(pool())//连接池
            .connectTimeout(5L, TimeUnit.SECONDS)
            .readTimeout(5L, TimeUnit.SECONDS)
            .build();

    private static ConnectionPool pool() {
        return new ConnectionPool(200, 5, TimeUnit.MINUTES);
    }

    private static X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    private static SSLSocketFactory sslSocketFactory() {
        try {
            //信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //noinspection SpringConfigurationProxyMethods
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据map获取get请求参数
     *
     * @param queries
     * @return
     */
    static StringBuffer getQueryString(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return sb;
    }

    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    public static String execNewCall(Request request) {
        Response response = null;
        try {
            //response = okHttpClient.newCall(request).execute();
            return execNewCall(request, 60);
        } catch (Exception e) {
            log.error("okhttp3 put error >> ex = {}", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }


    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    public static String execNewCall(Request request, long timeOutSeconds) {
        Response response = null;
        try {
            OkHttpClient client = okHttpClient.newBuilder()
                    .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
                    .writeTimeout(timeOutSeconds, TimeUnit.SECONDS)
                    .readTimeout(timeOutSeconds, TimeUnit.SECONDS).build();

            response = client.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (SocketTimeoutException timeout) {

        } catch (Exception e) {
            log.error("okhttp3 put error {}>> ex = {}", request, e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }


    /**
     * get
     *
     * @param url 请求的url
     * @return
     */
    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return execNewCall(request);
    }

    /**
     * get
     *
     * @param url            请求的url
     * @param timeOutSeconds 超時秒數
     * @return
     */
    public static String get(String url, long timeOutSeconds) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return execNewCall(request, timeOutSeconds);
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        StringBuffer sb = getQueryString(url, queries);
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        return execNewCall(request);
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param headers 请求头参数
     * @return
     */
    public static String getWithHeaders(String url, Map<String, String> headers) {
        Request request = new Request.Builder()
                .headers(Headers.of(headers))
                .url(url)
                .build();
        return execNewCall(request);
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return execNewCall(request);
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params, long timeOutSeconds) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return execNewCall(request, timeOutSeconds);
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post2(String url, Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送表单数据带头部.....
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     *
     * @param url
     * @param headers
     * @param params
     */
    public static String postWithHeaders(String url, Map<String, String> headers, Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, String.valueOf(params.get(key)));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(builder.build())
                .build();
        return execNewCall(request);
    }


    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String postJSON(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送JSON数据带头部....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     *
     * @param url
     * @param headers
     * @param jsonParams
     */
    public static String postJSONWithHeaders(String url, Map<String, String> headers, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    public static String patchJSON(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .patch(requestBody)
                .build();
        return execNewCall(request);
    }

    public static String postText(String url, String text, Map<String, String> headers) throws Exception {

        Headers okHeaders = Headers.of(headers);
        RequestBody requestBody = RequestBody.create(text, MediaType.parse("text/plain"));
        Request request = new Request.Builder()
                .headers(okHeaders)
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String putJSON(String url, String jsonParams, String type) {

        Map<String, String> headers = new HashMap<>();
        if (type != null) {
            headers.put("User-Agent", type);
        }
        headers.put("Content-Type", "application/json");

        Headers okHeaders = Headers.of(headers);
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .headers(okHeaders)
                .url(url)
                .put(requestBody)
                .build();
        return execNewCall(request);
    }


    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String putJSON(String url, String jsonParams) {

        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        return execNewCall(request);
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postXml(String url, String xml) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("application/xml; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execNewCall(request);
    }

    public static void main(String[] args) {
        System.out.println(get("http://www.baidu.com"));
    }
}
