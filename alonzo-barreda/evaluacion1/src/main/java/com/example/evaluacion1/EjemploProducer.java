package com.example.evaluacion1;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemploProducer {
    
      @Autowired

    private RabbitTemplate rabbitTemplate;



    public void sendMessage(String message) {

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);

    }
}
