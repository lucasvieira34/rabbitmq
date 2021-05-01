package com.lucasvieira.producer.service.impl;

import com.lucasvieira.producer.amqp.AmqpProducer;
import com.lucasvieira.producer.dto.Message;
import com.lucasvieira.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<Message> amqp;

    @Override
    public void sendToConsumer(Message message) {
        amqp.producer(message);
    }
}
