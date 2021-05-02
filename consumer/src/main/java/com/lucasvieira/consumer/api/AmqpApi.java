package com.lucasvieira.consumer.api;

import com.lucasvieira.consumer.service.ReprocessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpApi {

    @Autowired
    private ReprocessService reprocessService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/reprocess")
    public void sendToQueue() {
        reprocessService.reprocess();
    }

}
