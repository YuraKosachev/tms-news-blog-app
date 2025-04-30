package com.tms.dataprovider.controllers;

import com.tms.dataprovider.core.constants.ApiConstants;
import com.tms.dataprovider.core.constants.DateTimeConstants;
import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.interfaces.services.TaskService;
import com.tms.dataprovider.core.models.dtos.TaskDto;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(ApiConstants.API_PREFIX_V1)
@ResponseBody
@AllArgsConstructor
@Tag(name="Task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping(ApiConstants.Task.API_LIST_TASK)
    @Operation(description = "Task endpoint to get all tasks", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
    public ResponseEntity<List<TaskDto>> listTask() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping(ApiConstants.Task.API_LIST_TASK_BY_DATE)
    @Operation(description = "Task endpoint to get all tasks by date", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
    public ResponseEntity<List<TaskDto>> listTask(@PathVariable @DateTimeFormat(pattern = DateTimeConstants.DATE_FORMAT) LocalDate date) {
           return ResponseEntity.ok(taskService.getAllByDate(date));
    }

    @GetMapping(ApiConstants.Task.API_LIST_TASK_BY_STATUS)
    @Operation(description = "Task endpoint to get all tasks by status", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto[].class))})
    public ResponseEntity<List<TaskDto>> listTaskByStatus(@PathVariable ProcessingStatus status) {
        return ResponseEntity.ok(taskService.getAllByStatus(status));
    }
}
