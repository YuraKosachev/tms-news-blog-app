package com.tms.gateway.core.clients;

import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.enums.ProcessingStatus;
import com.tms.gateway.core.models.data_provider.SourceDto;
import com.tms.gateway.core.models.data_provider.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@FeignClient("data-provider")
public interface DataProviderClient {
    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 + ApiConstants.Source.API_LIST_SOURCES)
    ResponseEntity<List<SourceDto>> listSources();

    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 + ApiConstants.Source.API_LIST_SOURCES_BY_TASK_ID)
    ResponseEntity<List<SourceDto>> listSourcesByTaskId(@RequestParam("taskId") UUID taskId);

    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 + ApiConstants.Source.API_SOURCE_BY_ID)
    ResponseEntity<SourceDto> getSource(@RequestParam("id") UUID id);

    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 +ApiConstants.Task.API_LIST_TASK)
    ResponseEntity<List<TaskDto>> listTask();

    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 +ApiConstants.Task.API_LIST_TASK_BY_DATE)
    ResponseEntity<List<TaskDto>> listTask(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

    @GetMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_V1 + ApiConstants.Task.API_LIST_TASK_BY_STATUS)
    ResponseEntity<List<TaskDto>> listTaskByStatus(@PathVariable ProcessingStatus status);
}
