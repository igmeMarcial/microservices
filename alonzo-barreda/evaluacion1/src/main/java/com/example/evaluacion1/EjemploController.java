package com.example.evaluacion1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EjemploController {
    @Autowired
    private EjemploProducer rabbitMQProducer;

    @GetMapping("/sendToRabbit")
    public String sendToRabbitMQ(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return "Mensaje enviado a RabbitMQ: " + message;
    }
}
