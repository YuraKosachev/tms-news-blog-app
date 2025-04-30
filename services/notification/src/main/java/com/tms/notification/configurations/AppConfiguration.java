package com.tms.notification.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tms.notification.core.adapters.LocalDateTimeAdapter;
import com.tms.notification.core.adapters.NotificationTypeAdapter;
import com.tms.notification.core.enums.NotificationType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.LocalDateTime;

@Configuration
public class AppConfiguration {

    @Bean
    public Gson getGson(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(NotificationType.class, new NotificationTypeAdapter())
                .create();
    }

    @Bean
    public HttpClient getHttpClient(){
        return HttpClient.newHttpClient();
    }
}
