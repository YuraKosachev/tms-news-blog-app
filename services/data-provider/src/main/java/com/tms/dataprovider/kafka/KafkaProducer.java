package com.tms.dataprovider.kafka;

import com.tms.dataprovider.core.constants.KafkaConstants;
import com.tms.dataprovider.core.models.kafka.NewsMessage;
import com.tms.dataprovider.core.models.kafka.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, NewsMessage> kafkaNewsTemplate;
    private final KafkaTemplate<String, NotificationMessage> kafkaNotificationTemplate;

    public void sendNews(NewsMessage message) {
        log.info("Sending processed news");
        kafkaNewsTemplate.send(getMessageBuilder(message, KafkaConstants.PUBLISHER_TOPIC));
        log.info("News sent");
    }

    public void sendNotification(NotificationMessage message) {
        log.info("Sending notification");
        kafkaNotificationTemplate.send(getMessageBuilder(message, KafkaConstants.NOTIFICATION_TOPIC));
        log.info("Notification sent");
    }

    private <T> Message<T> getMessageBuilder(T message, String topic) {
        return MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
    }
}
