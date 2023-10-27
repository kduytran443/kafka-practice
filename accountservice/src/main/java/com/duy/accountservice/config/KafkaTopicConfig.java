package com.duy.accountservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.notification}")
    private String notificationTopic;

    @Value("${spring.kafka.topic.statistic}")
    private String statisticTopic;

    NewTopic notification() {
        return new NewTopic(notificationTopic, 2, (short) 1);
    }

    NewTopic statistic() {
        return new NewTopic(statisticTopic, 1, (short) 1);
    }

}
