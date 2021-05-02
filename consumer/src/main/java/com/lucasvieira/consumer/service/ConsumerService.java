package com.lucasvieira.consumer.service;

import com.lucasvieira.consumer.dto.MessageQueue;

public interface ConsumerService {
    void action(MessageQueue message) throws Exception;
}
