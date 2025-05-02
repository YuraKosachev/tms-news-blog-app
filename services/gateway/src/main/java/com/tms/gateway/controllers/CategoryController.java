package com.tms.gateway.controllers;

import com.tms.gateway.core.clients.ArticleClient;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.constants.SecurityConstants;
import com.tms.gateway.core.models.articles.CategoryCreateDto;
import com.tms.gateway.core.models.articles.CategoryDto;
import com.tms.gateway.core.models.articles.CategoryUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_NEWS_GATEWAY)
@Tag(name = "Categories")
public class CategoryController {

    private final ArticleClient articleClient;

    @PostMapping(ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryCreateDto dto){
        return articleClient.createCategory(dto);
    }

    @DeleteMapping(ApiConstants.Category.GATEWAY_CATEGORY_DELETE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        articleClient.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiConstants.Category.API_CATEGORY_DETAIL_DELETE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    @ResponseBody
    @PermitAll
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") UUID id){
        return articleClient.getCategoryById(id);
    }

    @GetMapping(ApiConstants.Category.API_CATEGORY_LIST)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto[].class))})
    @ResponseBody
    @PermitAll
    public ResponseEntity<List<CategoryDto>> findAll(){
        return articleClient.findAll();
    }

    @PutMapping(ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    @Operation(description = "Category endpoint to update category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    @ResponseBody
    @SecurityRequirement(name= SecurityConstants.OAUTH2_NAME)
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryUpdateDto dto){
        return articleClient.updateCategory(dto);
    }
}
