package com.lucasvieira.producer.service;

import com.lucasvieira.producer.dto.MessageQueue;

public interface AmqpService {
    void sendToConsumer(MessageQueue message);
}
