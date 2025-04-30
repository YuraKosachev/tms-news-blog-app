package com.tms.dataprovider.configurations;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {

    @Value("${task.cnn.url}")
    private String cnnUrl;

    @Value("${task.cnn.world.url}")
    private String cnnWorldUrl;

    public String getCnnUrl() {
        return cnnUrl;
    }

    public String getCnnWorldUrl() {
        return cnnWorldUrl;
    }
}
