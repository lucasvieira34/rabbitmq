package com.lucasvieira.consumer.amqp.impl;

import com.lucasvieira.consumer.amqp.AmqpReprocess;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReprocessRabbit implements AmqpReprocess {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.dead-letter.producer}")
    private String deadLetter;

    @Value("${spring.rabbitmq.request.parking-lot.producer}")
    private String parkingLot;

    private static final String X_RETRIES_HEADER = "x-retries";

    @Override
    @Scheduled(cron = "${spring.rabbitmq.listener.time-retry}")
    public void reprocess() {
        List<Message> messages = getMessages();

        messages.forEach(message -> {
            Map<String, Object> header = message.getMessageProperties().getHeaders();
            Integer retriesHeader = (Integer) header.get(X_RETRIES_HEADER);

            if (retriesHeader == null) {
                retriesHeader = 0;
            }

            if (retriesHeader < 3) {
                header.put(X_RETRIES_HEADER, retriesHeader + 1);
                rabbitTemplate.send(exchange, queue, message);
            } else {
                rabbitTemplate.send(parkingLot, message);
            }

        });
    }

    private List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        Boolean isNull;
        Message message;

        do {
            message = rabbitTemplate.receive(deadLetter);
            isNull = message != null;

            if (Boolean.TRUE.equals(isNull)) {
                messages.add(message);
            }

        } while (Boolean.TRUE.equals(isNull));

        return messages;
    }

}
