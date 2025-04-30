package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.NotificationClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.constants.DateTimeConstants;
import com.tms.gateway.core.models.notification.NotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PREFIX_NOTIFICATION_GATEWAY)
@RequiredArgsConstructor
@Tag(name = "Notifications")
public class NotificationController {

    private final NotificationClient notificationClient;

    @GetMapping(ApiConstants.Notification.API_GET_LIST_BY_DATE)
    @Operation(description = "Notification endpoint to get all notification by date", summary = "This is a summary for notifications get endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDto[].class))})
    public ResponseEntity<List<NotificationDto>> listTask(
            @PathVariable @DateTimeFormat(pattern = DateTimeConstants.DATE_FORMAT) LocalDate date
    ) {
        return notificationClient.listTask(date);
    }
}
