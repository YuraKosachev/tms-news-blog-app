package com.tms.notification.core.exceptions;

public class TelegramSendException extends RuntimeException {
    public TelegramSendException(String message) {
        super(message);
    }
}
