package com.duy.notificationservice.comsumer;

import com.duy.notificationservice.dto.MessageDTO;
import com.duy.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmailService emailService;

    @Autowired
    public NotificationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(id = "notificationGroup", topics = "notification")
    public void listen(MessageDTO messageDTO) {
        logger.info("Received: ", messageDTO.getTo());
        emailService.sendMail(messageDTO);
    }

}
