package com.base.event;

import com.base.utils.LocalDateTimeUtil;
import lombok.Data;

@Data
public class AsyncEventIntention {
    public static final Integer FINISHED = 1;
    public static final Integer WAITTING = 0;

    //事件唯一标志 完成时间  完成后保留时间（秒）（流水创建后清除时间加上这里）   最大重试次数
    private String tag;
    // 创建时间
    private Long createTime = LocalDateTimeUtil.timestamp13();
    // 当前流程 统一定义（开始0 结束1）
    private Integer state;
    // 当前状态/流程  *自定义
    private Integer flow;
    // 意图创建人
    private Long userId;
    // 清除时间
    private Long deadline = LocalDateTimeUtil.timestamp13();
    // 尝试次数
    private Integer tryNum;
    // 当前处理响应请，或者异常情况
    private String nowResult;
}
