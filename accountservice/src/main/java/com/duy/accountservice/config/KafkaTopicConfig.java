package com.duy.accountservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    NewTopic notification() {
        return new NewTopic("notification", 2, (short) 1);
    }

    NewTopic statistic() {
        return new NewTopic("statistic", 1, (short) 1);
    }

}
