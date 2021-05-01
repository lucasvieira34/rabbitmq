package com.lucasvieira.consumer.service;

import com.lucasvieira.consumer.dto.Message;

public interface ConsumerService {
    void action(Message message);
}
