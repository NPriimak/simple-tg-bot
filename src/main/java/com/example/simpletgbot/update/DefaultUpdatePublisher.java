package com.example.simpletgbot.update;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultUpdatePublisher implements UpdatePublisher {

    public DefaultUpdatePublisher(List<UpdateSubscriber> subscribers) {
        subscribers.forEach(this::subscribe);
    }

}
