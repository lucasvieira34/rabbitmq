package com.lucasvieira.producer.service;

import com.lucasvieira.producer.dto.Message;

public interface AmqpService {
    void sendToConsumer(Message message);
}
