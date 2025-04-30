package com.tms.news.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;
}
