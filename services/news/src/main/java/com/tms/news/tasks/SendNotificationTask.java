package com.tms.news.tasks;

import com.google.gson.Gson;
import com.tms.news.configurations.AppConfiguration;
import com.tms.news.configurations.BeanConfiguration;
import com.tms.news.core.enums.NotificationType;
import com.tms.news.core.mappers.ArticleMapper;
import com.tms.news.core.models.kafka.NotificationMessage;
import com.tms.news.kafka.NotificationNewsProducer;
import com.tms.news.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SendNotificationTask {

    private final ArticleRepository articleRepository;
    private final NotificationNewsProducer producer;
    private final ArticleMapper articleMapper;
    private final Gson gson;

    private final AppConfiguration config;


    @Scheduled(cron = "${task.notification.cron}")
    public void sendNotification() {
        var articles = articleRepository.getNotSent();

        if(articles.isEmpty())
            return;

        var json = gson.toJson(articles.stream()
                .map(a -> articleMapper.toShortDto(a))
                .toList());

        var message = new NotificationMessage(true, config.getApplicationName(), NotificationType.ARTICLE, json);

        producer.sendNotification(message);

        articles.forEach(article -> article.setSentAt(LocalDateTime.now()));
        articleRepository.saveAll(articles);
    }
}
