package com.leo.shoppr.config;

import com.leo.shoppr.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.TEST_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
