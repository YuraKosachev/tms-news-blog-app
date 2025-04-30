package com.tms.notification.core.adapters;

import com.google.gson.*;
import com.tms.notification.core.enums.NotificationType;

import java.lang.reflect.Type;

public class NotificationTypeAdapter implements JsonSerializer<NotificationType>, JsonDeserializer<NotificationType> {
    @Override
    public NotificationType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return NotificationType.getStatusByString(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(NotificationType notificationType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(notificationType.getType());
    }

}
