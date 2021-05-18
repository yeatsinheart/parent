package com.gateway.request;

import com.base.utils.JsonUtil;
import com.base.utils.StringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class RequestParamUtil {


    public static Map<String, Object> getRequestParams(ByteBuf reuqest) {
        Map<String, Object> requestParams = new HashMap<>();
        byte[] req = new byte[reuqest.readableBytes()];
        reuqest.readBytes(req);
        String strContent = new String(req, StandardCharsets.UTF_8);
        try {
            requestParams = JsonUtil.toMap(strContent);
        } catch (Exception e) {
            requestParams.put("body", strContent);
        }
        return requestParams;
    }

    /**
     * 获取请求参数
     */
    public static Map<String, Object> getRequestParams(FullHttpRequest request) {
        Map<String, Object> requestParams = new HashMap<>();
        // 处理请求,POST请求直接忽视URL的附带参数
        if (request.method() == HttpMethod.GET) {
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            Map<String, List<String>> params = decoder.parameters();
            for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                requestParams.put(entry.getKey(), entry.getValue().get(0));
            }
        } else if (request.method() == HttpMethod.POST) {
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
                // 解析json数据（Content-Type = application/json）
                // 解析纯文本数据 放入body中
                ByteBuf content = request.content();
                byte[] reqContent = new byte[content.readableBytes()];
                content.readBytes(reqContent);
                String strContent = new String(reqContent, StandardCharsets.UTF_8);
                try {
                    requestParams = JsonUtil.toMap(strContent);
                } catch (Exception e) {
                    requestParams.put("body", strContent);
                }
            }
        }
        return requestParams;
    }

    /**
     * 获取文件上传参数
     */
    public static FileUpload getFileUpload(FullHttpRequest request) {
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


    /**
     * 判断是否为websocket握手请求
     */
    public static boolean isWebSocketHandShake(FullHttpRequest request) {
        //1、判断是否为get
        if (!request.method().equals(HttpMethod.GET)) {
            return false;
        }
        //2、判断Upgrade头是否包含websocket
        String upgrade = request.headers().get(HttpHeaderNames.UPGRADE);
        if (StringUtils.isEmpty(upgrade) || !upgrade.toLowerCase().contains(HttpHeaderValues.WEBSOCKET)) {
            return false;
        }
        //3、Connection头是否包含upgrade
        String connection = request.headers().get(HttpHeaderNames.CONNECTION);
        return StringUtils.isNotEmpty(connection)
                && connection.toLowerCase().contains(HttpHeaderValues.UPGRADE);
    }

    /**
     * 判断是否为文件上传
     */
    public static boolean isFileUpload(FullHttpRequest request) {
        //1、判断是否为文件上传自定义URI 3、判断是否为POST方法 2、判断Content-Type头是否包含multipart/form-data
        String uri = getUri(request);
        String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
        if (StringUtil.isEmpty(contentType)) {
            return false;
        }
        return "上传路径".equals(uri)
                && request.method().equals(HttpMethod.POST)
                && contentType.toLowerCase().contains(HttpHeaderValues.MULTIPART_FORM_DATA);
    }

}
