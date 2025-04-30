package com.tms.notification.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TelegramConfiguration {
    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.channel}")
    private String channel;
}
