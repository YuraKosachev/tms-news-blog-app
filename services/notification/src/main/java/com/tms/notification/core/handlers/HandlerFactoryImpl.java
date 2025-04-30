package com.tms.notification.core.handlers;

import com.google.gson.Gson;
import com.tms.notification.core.constants.ApiConstants;
import com.tms.notification.core.enums.NotificationType;
import com.tms.notification.core.exceptions.HandlerNotFoundException;
import com.tms.notification.core.interfaces.handlers.Handler;
import com.tms.notification.core.interfaces.handlers.HandlerFactory;
import com.tms.notification.core.telegram.TelegramProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerFactoryImpl implements HandlerFactory {
    private Map<NotificationType, Handler> handlers = new HashMap<NotificationType, Handler>();

    public HandlerFactoryImpl(Gson gson, TelegramProvider telegramProvider) {
        handlers.put(NotificationType.ARTICLE, new ArticleHandler(gson, telegramProvider));
        handlers.put(NotificationType.APPLICATION, new ApplicationHandler(gson));
    }

    @Override
    public Handler getHandler(NotificationType type) {
        if(!handlers.containsKey(type)) {
            throw new HandlerNotFoundException("No handler for type " + type);
        }
        return handlers.get(type);
    }
}
