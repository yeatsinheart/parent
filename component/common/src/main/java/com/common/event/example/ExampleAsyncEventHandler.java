package com.common.event.example;


import com.common.event.AsyncEventHandler;
import com.common.event.AsyncEventIntention;
import org.springframework.stereotype.Component;

@Component
public class ExampleAsyncEventHandler implements AsyncEventHandler {

    @Override
    public boolean asyncProcess(AsyncEventIntention intention) {
        ExampleAsyncEventIntention example = (ExampleAsyncEventIntention)intention;
        return false;
    }

    @Override
    public boolean syncProcess(AsyncEventIntention intention) {
        return false;
    }

    @Override
    public boolean create(AsyncEventIntention intention) {
        ExampleAsyncEventIntention example = (ExampleAsyncEventIntention)intention;
        // insert into db example
        int insertNum = 0;
        syncProcess(example);
        return insertNum>0;
    }
    //自定义每个流程该执行的方法

}
