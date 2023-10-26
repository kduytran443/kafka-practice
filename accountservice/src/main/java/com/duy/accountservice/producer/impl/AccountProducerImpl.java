package com.duy.accountservice.producer.impl;

import com.duy.accountservice.dto.AccountDTO;
import com.duy.accountservice.dto.MessageDTO;
import com.duy.accountservice.dto.StatisticDTO;
import com.duy.accountservice.producer.AccountProducer;
import com.duy.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountProducerImpl implements AccountProducer {

    @Value("${spring.kafka.topic.notification}")
    private String notificationTopic;

    @Value("${spring.kafka.topic.statistic}")
    private String statisticTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AccountService accountService;

    @Autowired
    public AccountProducerImpl(KafkaTemplate<String, Object> kafkaTemplate, AccountService accountService) {
        this.kafkaTemplate = kafkaTemplate;
        this.accountService = accountService;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountDTO createdAccount = accountService.createAccount(accountDTO);
        StatisticDTO statisticDTO = new StatisticDTO(String.format("Account %s is created", accountDTO.getEmail()), LocalDateTime.now());
        MessageDTO messageDTO = new MessageDTO(accountDTO, "Welcome to Kafka practice project", "Hope I can understand this more!");

        kafkaTemplate.send(notificationTopic, messageDTO);
        kafkaTemplate.send(statisticTopic, statisticDTO);

        return createdAccount;
    }

}
