package com.common.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("bussinessEventDispatcher")
public class AsyncEventDispatcher {
    // 附加事件/过程事件
    // 注册 登陆 充值 第一次充值 限制投注流水 提现 投注 有效投注
    // 开奖 中奖 邀请 转账 分红 返点
    @Autowired
    private Map<String, AsyncEventHandler> handlerMap;

    //数据库分页扫描所有处理中的intention
    //遍历每一条intention进行处理
    public void asyncScan() {
        AsyncEventIntention intention = new AsyncEventIntention();
        AsyncEventHandler handler = handlerMap.get(intention.getTag());
        handler.asyncProcess(intention);
    }
    //创建事件追踪
    public boolean create(){
        AsyncEventIntention intention = new AsyncEventIntention();
        AsyncEventHandler handler = handlerMap.get(intention.getTag());
        return handler.create(intention);
    }
    //清除当前该过期的结束的intention
    public void clear() {
        //delete All deadline < LocalDateTimeUtil.getNowMsTimestamp() AND (state=)
    }
}
