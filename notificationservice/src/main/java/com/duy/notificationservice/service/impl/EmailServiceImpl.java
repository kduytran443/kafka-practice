package com.duy.notificationservice.service.impl;

import com.duy.notificationservice.dto.MessageDTO;
import com.duy.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    @Async
    public void sendMail(MessageDTO messageDTO) {
        try {
            logger.info("START... Sending email");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper (message, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("name", messageDTO.getToName()); context.setVariable("content", messageDTO.getContent());
            String html = templateEngine.process("welcome-email", context);

            helper.setTo(messageDTO.getTo());
            helper.setText(html, true);
            helper.setSubject(messageDTO.getSubject());
            helper.setFrom(from); javaMailSender.send(message);
            logger.info("END... Email sent success");
        } catch (MessagingException e) {
            logger.error(String.format("Email sent with error: %s", e.getMessage()));
        }
    }
}
