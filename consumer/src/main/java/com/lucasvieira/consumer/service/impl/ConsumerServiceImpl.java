package com.lucasvieira.consumer.service.impl;

import com.lucasvieira.consumer.dto.Message;
import com.lucasvieira.consumer.service.ConsumerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public void action(Message message) {
        System.out.println(message.getText());
    }
}
