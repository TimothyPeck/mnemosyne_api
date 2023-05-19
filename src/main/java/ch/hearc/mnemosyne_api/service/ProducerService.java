package ch.hearc.mnemosyne_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService implements ProducerService_I {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Override
    public void send(Object message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

}
