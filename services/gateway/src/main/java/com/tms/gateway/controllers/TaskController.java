package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.DataProviderClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.constants.DateTimeConstants;
import com.tms.gateway.core.enums.ProcessingStatus;
import com.tms.gateway.core.models.data_provider.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_GATEWAY)
@ResponseBody
@AllArgsConstructor
@Tag(name="Task")
public class TaskController {

    private final DataProviderClient dataProviderClient;


    @GetMapping(ApiConstants.Task.API_LIST_TASK)
    @Operation(description = "Task endpoint to get all tasks", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
//    @SecurityRequirement(name="keycloak_security_auth")
    public ResponseEntity<List<TaskDto>> listTask() {
        return dataProviderClient.listTask();
    }

    @GetMapping(ApiConstants.Task.API_LIST_TASK_BY_DATE)
    @Operation(description = "Task endpoint to get all tasks by date", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
    @Parameter(name = "date", required = true)
    public ResponseEntity<List<TaskDto>> listTask(@PathVariable @DateTimeFormat(pattern = DateTimeConstants.DATE_FORMAT) LocalDate date) {
           return dataProviderClient.listTask(date);
    }

    @GetMapping(ApiConstants.Task.API_LIST_TASK_BY_STATUS)
    @Operation(description = "Task endpoint to get all tasks by status", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
    public ResponseEntity<List<TaskDto>> listTaskByStatus(@PathVariable ProcessingStatus status) {
        return dataProviderClient.listTaskByStatus(status);
    }
}
