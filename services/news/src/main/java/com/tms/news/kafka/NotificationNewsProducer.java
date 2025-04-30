package com.tms.news.kafka;

import com.tms.news.core.constants.KafkaConstants;
import com.tms.news.core.models.kafka.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationNewsProducer {
    private final KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    public void sendNotification(NotificationMessage message) {
        log.info("Sending notification");
        var messageToSend = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, KafkaConstants.NOTIFICATION_TOPIC)
                .build();
        kafkaTemplate.send(messageToSend);
        log.info("Notification sent successfully");
    }
}