package com.common.event.example;

import com.common.event.AsyncEventIntention;
import com.common.utils.LocalDateTimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExampleAsyncEventIntention extends AsyncEventIntention {
    //事件唯一标志 完成时间  完成后保留时间（秒）（流水创建后清除时间加上这里）   最大重试次数
    private String tag;
    // 创建时间
    private Long createTime = LocalDateTimeUtil.timestamp13();
    // 当前状态（开始 结束）
    private Integer state;
    // 意图创建人
    private Long userId;
    // 清除时间
    private Long deadline = LocalDateTimeUtil.timestamp13();
}
