package com.lucasvieira.consumer.service.impl;

import com.lucasvieira.consumer.dto.MessageQueue;
import com.lucasvieira.consumer.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public void action(MessageQueue message) {
        if ("teste".equalsIgnoreCase(message.getText())) {
            //Simulando exception
            throw new AmqpRejectAndDontRequeueException("Erro");
        }
        System.out.println(message.getText());
    }
}
