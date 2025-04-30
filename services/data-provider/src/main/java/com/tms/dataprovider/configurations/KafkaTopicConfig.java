package com.tms.dataprovider.configurations;

import com.tms.dataprovider.core.constants.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic publisherTopic() {
        return TopicBuilder
                .name(KafkaConstants.PUBLISHER_TOPIC)
                .build();
    }

    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder
                .name(KafkaConstants.NOTIFICATION_TOPIC)
                .build();
    }
}
