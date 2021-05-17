package com.base.event.springevent;

import org.springframework.context.ApplicationEvent;

public class SpringExampleEvent extends ApplicationEvent {
    // 事件定义，参数定义
    // 事务只设计当前模块的表，别的模块的表通过bus-消息事件落库，来实现。
    // 订单号 扣款 还原，这个过程还是很难说的
    // 帐变 相同订单号 帐变与撤销（撤销已完成的，不能撤销执行中的）
    public SpringExampleEvent() {
        super(null);
    }

    public SpringExampleEvent(Object source) {
        super(source);
    }
}
