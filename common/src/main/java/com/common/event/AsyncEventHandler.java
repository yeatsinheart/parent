package com.common.event;


public interface AsyncEventHandler {
    // 根据当前流程执行对应的步骤
    boolean asyncProcess(AsyncEventIntention intention);
    // 一次执行完所有流程
    boolean syncProcess(AsyncEventIntention intention);
    // 创建时，如果已经创建完成，就算成功，继续执行一次完成所有流程
    // 前端响应操作完成，具体状态处理中
    // 投注时，同时创建注单意图以及待出单状态的注单。
    boolean create(AsyncEventIntention intention);
}
