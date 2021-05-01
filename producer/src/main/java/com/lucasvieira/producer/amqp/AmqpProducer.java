package com.lucasvieira.producer.amqp;

public interface AmqpProducer<T> {
    void producer(T t);
}
