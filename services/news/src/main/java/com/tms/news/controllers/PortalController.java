package com.tms.news.controllers;

import com.tms.news.core.constants.ApiConstants;
import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.PortalService;
import com.tms.news.core.models.dtos.CategoryDto;
import com.tms.news.core.models.dtos.PortalCreateDto;
import com.tms.news.core.models.dtos.PortalDto;
import com.tms.news.core.models.dtos.PortalUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_V1)
@Tag(name = "Portals")
public class PortalController {

    private final PortalService portalService;

    @GetMapping(ApiConstants.Portal.API_PORTAL_LIST)
    @Operation(description = "Portal endpoint to get all",
            summary = "This is a summary for portal get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto[].class))})
    @ResponseBody
    public ResponseEntity<List<PortalDto>> getAll() {
        return ResponseEntity.ok(portalService.getPortals());
    }

    @GetMapping(ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    @Operation(description = "Portal endpoint to get item",
            summary = "This is a summary for portal get item endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    public ResponseEntity<PortalDto> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(portalService.getPortalById(id).orElseThrow(()-> new ItemNotFoundException("portal not found")));
    }

    @DeleteMapping(ApiConstants.Portal.API_PORTAL_GET_AND_DELETE)
    @Operation(description = "Portal endpoint to get all",
            summary = "This is a summary for portal get all endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        portalService.deletePortalById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping(ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    @Operation(description = "Portal endpoint to create portal",
            summary = "This is a summary for portal create endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    public ResponseEntity<PortalDto> create(@RequestBody @Valid PortalCreateDto portalDto) {
        return ResponseEntity.ok(portalService.createPortal(portalDto));
    }

    @PutMapping(ApiConstants.Portal.API_PORTAL_CREATE_UPDATE)
    @Operation(description = "Portal endpoint to update portal",
            summary = "This is a summary for portal update endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PortalDto.class))})
    @ResponseBody
    public ResponseEntity<PortalDto> update(@RequestBody @Valid PortalUpdateDto portalDto) {
        return ResponseEntity.ok(portalService.updatePortal(portalDto));
    }

}
