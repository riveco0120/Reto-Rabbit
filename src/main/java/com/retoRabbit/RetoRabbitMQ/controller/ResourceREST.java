package com.retoRabbit.RetoRabbitMQ.controller;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/broker/topic/")
public class ResourceREST {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/apartment")
    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
                           @RequestParam("messageData") String messageData) {

        amqpTemplate.convertAndSend(exchange, routingKey, messageData);

        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }
}