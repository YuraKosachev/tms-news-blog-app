package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.DataProviderClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.models.data_provider.SourceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping(ApiConstants.API_PREFIX_DATA_PROVIDER_GATEWAY)
@Tag(name="Sources")
@AllArgsConstructor
public class SourceController {

    private final DataProviderClient dataProviderClient;

    @GetMapping(ApiConstants.Source.API_LIST_SOURCES)
    @Operation(description = "Task endpoint to get all sources", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SourceDto[].class))})
    public ResponseEntity<List<SourceDto>> listSources() {
        return dataProviderClient.listSources();
    }

    @GetMapping(ApiConstants.Source.API_LIST_SOURCES_BY_TASK_ID)
    @Operation(description = "Task endpoint to get all sources by task id", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SourceDto[].class))})
    @SecurityRequirement(name="keycloak_security_auth")
    public ResponseEntity<List<SourceDto>> listSourcesByTaskId(@RequestParam("taskId") UUID taskId) {
        return dataProviderClient.listSourcesByTaskId(taskId);
    }

    @GetMapping(ApiConstants.Source.API_SOURCE_BY_ID)
    @Operation(description = "Task endpoint to get source by id", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SourceDto.class))})
    @SecurityRequirement(name="keycloak_security_auth") // set up in swaggerconfiguration
    public ResponseEntity<SourceDto> getSource(@RequestParam("id") UUID id) {
        return dataProviderClient.getSource(id);
    }
}
