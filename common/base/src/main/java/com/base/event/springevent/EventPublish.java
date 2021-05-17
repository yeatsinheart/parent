package com.base.event.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventPublish {
    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent(String emailAddress) {
        SpringExampleEvent event = new SpringExampleEvent();
        applicationContext.publishEvent(event);
    }
}
