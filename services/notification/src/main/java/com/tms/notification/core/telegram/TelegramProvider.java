package com.tms.notification.core.telegram;

import com.google.gson.Gson;
import com.tms.notification.configurations.TelegramConfiguration;
import com.tms.notification.core.enums.ParseMode;
import com.tms.notification.core.exceptions.TelegramSendException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TelegramProvider {
    private final HttpClient httpClient;
    private final TelegramConfiguration config;

    private final String API_TELEFGRAM = "https://api.telegram.org/bot%s/sendMessage";

    @SneakyThrows
    public void sendMessage(String message, ParseMode parseMode) {

        var url = "%s?%s".formatted(API_TELEFGRAM.formatted(config.getToken()),
                getQueryString(message, parseMode, config.getChannel()));

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        if(!(response.statusCode() >= 200 && response.statusCode() < 300)) {
            throw new TelegramSendException("Error sending telegram message");
        }
    }

    private String getQueryString(String message, ParseMode parseMode, String chatId ) {
        Map<String,String> params = new HashMap<>();

        params.put("chat_id", chatId);
        if(parseMode != null)
            params.put("parse_mode", parseMode.getType());
        params.put("text",URLEncoder.encode(message));

        return String.join("&", params.keySet().stream().map(x->"%s=%s".formatted(x, params.get(x))).toList());
    }


}
