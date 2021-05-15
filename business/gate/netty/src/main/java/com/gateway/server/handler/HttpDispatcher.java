package com.gateway.server.handler;

import com.gateway.server.parameter.WebSocketRequestDTO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@ChannelHandler.Sharable
@Service
public class HttpDispatcher extends AbstractRequestHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            if (isWebSocketHandShake(request)) {
                //websocket处理器
                WebSocketRequestDTO webSocketRequest = new WebSocketRequestDTO(request);
                ctx.fireChannelRead(webSocketRequest);
                webSocketRequest.release();
            } else if (isFileUpload(request)) {
                //文件上传处理器
                ctx.fireChannelRead(new FileUploadRequestVo(request));
            } else {
                //Http请求处理器
                ctx.fireChannelRead(request.retain());
            }
        } else if (msg instanceof WebSocketFrame) {
            WebSocketFrame frame = (WebSocketFrame) msg;
            WebSocketRequestDTO webSocketRequest = new WebSocketRequestDTO(frame);
            ctx.fireChannelRead(webSocketRequest);
            webSocketRequest.release();
        } else {
            log.error("什么鬼消息");
            //ctx.fireChannelRead((ByteBuf) msg);
        }
    }

    //判断是否为websocket握手请求
    private boolean isWebSocketHandShake(FullHttpRequest request) {
        //1、判断是否为get 2、判断Upgrade头是否包含websocket 3、Connection头是否包含upgrade
        String upgrade = request.headers().get(HttpHeaderNames.UPGRADE);
        String connection = request.headers().get(HttpHeaderNames.CONNECTION);
        return StringUtils.isNotEmpty(upgrade)
                && StringUtils.isNotEmpty(connection)
                && request.method().equals(HttpMethod.GET)
                && upgrade.toLowerCase().contains(HttpHeaderValues.WEBSOCKET)
                && connection.toLowerCase().contains(HttpHeaderValues.UPGRADE);
    }

    //判断是否为文件上传
    private boolean isFileUpload(FullHttpRequest request) {
        //1、判断是否为文件上传自定义URI 3、判断是否为POST方法 2、判断Content-Type头是否包含multipart/form-data
//        String uri = ParamUtil.getUri(request);
//        String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
//        if (contentType == null || contentType.isEmpty()) {
//            return false;
//        }
//        return "MyConfig.FILE_UPLOAD_URL".equals(uri)
//                && request.method() == HttpMethod.POST
//                && contentType.toLowerCase().contains(HttpHeaderValues.MULTIPART_FORM_DATA);
        return false;
    }


    @NoArgsConstructor
    @Data
    @EqualsAndHashCode(callSuper = false)
    public class FileUploadRequestVo {
        private FullHttpRequest request;

        public FileUploadRequestVo(FullHttpRequest request) {
            this.request = request;
        }
    }
}
