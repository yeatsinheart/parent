package com.common.desensitization;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogDesensitize extends MessageConverter {
    /**
     * 日志脱敏开关
     */
    private static final Boolean converterCanRun = Boolean.TRUE;

    public static void main(String[] args) {
        String str = "(\\w{4})\\w{7,10}(\\w{4})";
        String oriLogMsg = "===============userDTO.toString=UserDTO(identityNo=111111111111111111, name=dsf, realname=张三)";
        Matcher matcher = Pattern.compile(str).matcher(oriLogMsg);
        if (matcher.find()) {
            System.out.println("匹配上的内容" + matcher.group());
        } else {
            System.out.println("2222222222222222");
        }
    }

    /**
     * 日志脱敏关键字
     */
    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志
        String oriLogMsg = event.getFormattedMessage();
        if (!converterCanRun) {
            return oriLogMsg;
        }
        // 获取脱敏后的日志
        DesensitionType[] values = DesensitionType.values();
        for (DesensitionType value : values) {
            if (value.getRule() != null && value.getRule().length > 0 && oriLogMsg.contains(value.getType())) {
                Matcher matcher = Pattern.compile(value.getRule()[0]).matcher(oriLogMsg);
                oriLogMsg = matcher.replaceAll(value.getRule()[1]);
            }
        }
        return oriLogMsg;
    }
}

