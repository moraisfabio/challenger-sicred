package com.challenger.services.rabbitmq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String rowName, Object message){
        this.rabbitTemplate.convertAndSend(rowName, message);
    }
}
