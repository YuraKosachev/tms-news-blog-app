package com.tms.gateway.core.clients;

import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.models.notification.NotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;


@FeignClient("notification-service")
public interface NotificationClient {
    @GetMapping(ApiConstants.API_PREFIX_NOTIFICATION_V1 +
            ApiConstants.Notification.API_GET_LIST_BY_DATE)
    ResponseEntity<List<NotificationDto>> listTask(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);
}