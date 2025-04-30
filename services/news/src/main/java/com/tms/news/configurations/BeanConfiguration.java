package com.tms.news.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tms.news.core.adapters.LocalDateTimeAdapter;
import com.tms.news.core.adapters.NotificationTypeAdapter;
import com.tms.news.core.enums.NotificationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class BeanConfiguration {

    @Bean
    public Gson getGson(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(NotificationType.class, new NotificationTypeAdapter())
                .create();
    }
}
