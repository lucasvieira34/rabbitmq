package com.lucasvieira.consumer.amqp.impl;

import com.lucasvieira.consumer.amqp.AmqpConsumer;
import com.lucasvieira.consumer.dto.Message;
import com.lucasvieira.consumer.service.ConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements AmqpConsumer<Message> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Message message) {
        consumerService.action(message);
    }
}
