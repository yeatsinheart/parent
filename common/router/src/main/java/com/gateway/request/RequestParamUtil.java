package com.gateway.request;

import com.base.utils.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RequestParamUtil {

    /**
     * 获取请求参数
     */
    public static Map<String, Object> getRequestParams(FullHttpRequest request) {
        Map<String, Object> requestParams = new HashMap<>();
        // 处理get请求
        if (request.method() == HttpMethod.GET) {
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            Map<String, List<String>> params = decoder.parameters();
            for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                requestParams.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        // 处理POST请求
        if (request.method() == HttpMethod.POST) {
            String strContentType = request.headers().get("Content-Type").trim();
            //解析from表单数据（Content-Type = x-www-form-urlencoded）
            if (strContentType.contains("x-www-form-urlencoded")) {
                HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
                List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
                for (InterfaceHttpData data : postData) {
                    if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                        MemoryAttribute attribute = (MemoryAttribute) data;
                        requestParams.put(attribute.getName(), attribute.getValue());
                    }
                }
            } else {
                //解析json数据（Content-Type = application/json）
                ByteBuf content = request.content();
                byte[] reqContent = new byte[content.readableBytes()];
                content.readBytes(reqContent);
                String strContent = new String(reqContent, StandardCharsets.UTF_8);
                requestParams = JsonUtil.toMap(strContent);
            }
        }
        return requestParams;
    }

    /**
     * 获取文件上传参数
     */
    public static FileUpload getFileUpload(HttpRequest request) {
        // 处理POST请求
        if (request.method() == HttpMethod.POST) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
            List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
            for (InterfaceHttpData data : postData) {
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
                    FileUpload fileUpload = (FileUpload) data;
                    return fileUpload;
                }
            }
        }
        return null;
    }

    /**
     * 获取请求Uri
     */
    public static String getUri(HttpRequest request) {
        return new QueryStringDecoder(request.uri()).path();
    }

}
