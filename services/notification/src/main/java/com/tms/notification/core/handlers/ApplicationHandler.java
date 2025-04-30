package com.tms.notification.core.handlers;

import com.google.gson.Gson;
import com.tms.notification.core.interfaces.handlers.Handler;
import com.tms.notification.core.models.entities.Notification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApplicationHandler implements Handler {

    private final Gson gson;
    @Override
    public void handle(Notification notification) {
        System.out.println(notification);
    }
}
