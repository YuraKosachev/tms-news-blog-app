package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.ArticleClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.constants.SecurityConstants;
import com.tms.gateway.core.models.articles.PortalCreateDto;
import com.tms.gateway.core.models.articles.PortalDto;
import com.tms.gateway.core.models.articles.PortalUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_NEWS_GATEWAY)
@Tag(name = "Portals")
public class PortalController {

    private final ArticleClient articleClient;

    @GetMapping(ApiConstants.Portal.API_PORTAL_LIST)
    @Operation(description = "Portal endpoint to get all",
            summary = "This is a summary for portal get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto[].class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<List<PortalDto>> getAll() {
        return articleClient.getAll();
    }

    @GetMapping(ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    @Operation(description = "Portal endpoint to get item",
            summary = "This is a summary for portal get item endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<PortalDto> getById(@PathVariable("id") UUID id) {
        return articleClient.getPortalById(id);
    }

    @DeleteMapping(ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    @Operation(description = "Portal endpoint to get all",
            summary = "This is a summary for portal get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        articleClient.deletePortalById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    @Operation(description = "Portal endpoint to create portal",
            summary = "This is a summary for portal create endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<PortalDto> create(@RequestBody PortalCreateDto portalDto) {
        return articleClient.createPortal(portalDto);
    }

    @PutMapping(ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    @Operation(description = "Portal endpoint to update portal",
            summary = "This is a summary for portal update endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<PortalDto> update(@RequestBody PortalUpdateDto portalDto) {
        return articleClient.updatePortal(portalDto);
    }
}
