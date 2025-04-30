package com.tms.notification.core.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tms.notification.core.enums.ParseMode;
import com.tms.notification.core.interfaces.handlers.Handler;
import com.tms.notification.core.models.dtos.ArticleDto;
import com.tms.notification.core.models.entities.Notification;
import com.tms.notification.core.telegram.TelegramProvider;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ArticleHandler implements Handler {

    private final Gson gson;
    private final TelegramProvider telegramProvider;

    @Override
    public void handle(Notification notification) {

        var json = notification
                .getContent()
                .replaceAll("\\\\", "");        //удаляем backslash

        var articles = (List<ArticleDto>) gson.fromJson(json.substring(1, json.length() - 1), new TypeToken<ArrayList<ArticleDto>>() {
        }.getType());

        if (articles.isEmpty()) return;
        var article = articles.stream().findFirst().orElseThrow();
        var message = getStringFromJson(article, articles.size() - 1);
        telegramProvider.sendMessage(message, ParseMode.MARKDOWNV2);
    }

    private String cleanString(String source) {
        return source
                .replace(".", "\\.")
                .replace("-", "\\-");
    }

    private String getStringFromJson(ArticleDto dto, Integer count) {

        StringBuilder builder = new StringBuilder();
        if (dto.title() != null)
            builder.append("*%s* ".formatted(cleanString(dto.title())));
        if (dto.description() != null)
            builder.append("%s ".formatted(cleanString(dto.description())));

        if(dto.author() != null)
            builder.append("@ %s ".formatted(cleanString(dto.author())));

        if (dto.link() != null)
            builder.append("[Ссылка на оригинал](%s) ".formatted(dto.link()));
        if (count != null && count > 1)
            builder.append("and the other %d new articles ".formatted(count));

        return builder.toString();
    }
}
