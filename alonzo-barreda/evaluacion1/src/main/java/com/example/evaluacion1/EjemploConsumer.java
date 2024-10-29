package com.example.evaluacion1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemploConsumer {

    @Autowired
    private EjemploRepository ejemploRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)

    public void consumerMessage(String message) {
        Ejemplo nuevoEjemplo = new Ejemplo(message);
        ejemploRepository.save(nuevoEjemplo);
        System.out.println("Mensaje recibido: " + message);
        System.out.println("Mensaje recibido y guardado en MySQL: " + message);

    }
}
