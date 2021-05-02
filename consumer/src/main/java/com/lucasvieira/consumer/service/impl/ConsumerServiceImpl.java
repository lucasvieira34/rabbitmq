package com.lucasvieira.consumer.service.impl;

import com.lucasvieira.consumer.dto.MessageQueue;
import com.lucasvieira.consumer.service.ConsumerService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public void action(MessageQueue message) throws Exception {
        System.out.println(message.getText());
    }
}
