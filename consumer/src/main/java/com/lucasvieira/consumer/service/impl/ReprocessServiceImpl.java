package com.lucasvieira.consumer.service.impl;

import com.lucasvieira.consumer.amqp.AmqpReprocess;
import com.lucasvieira.consumer.service.ReprocessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReprocessServiceImpl implements ReprocessService {

    @Autowired
    private AmqpReprocess reprocess;

    @Override
    public void reprocess() {
        reprocess.reprocess();
    }

}
