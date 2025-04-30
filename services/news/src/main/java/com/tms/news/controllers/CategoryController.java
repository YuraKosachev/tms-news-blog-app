package com.tms.news.controllers;

import com.tms.news.core.constants.ApiConstants;
import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.CategoryService;
import com.tms.news.core.models.dtos.CategoryCreateDto;
import com.tms.news.core.models.dtos.CategoryDto;
import com.tms.news.core.models.dtos.CategoryUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.API_PREFIX_V1)
@Tag(name = "Categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    @ResponseBody
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryCreateDto dto){
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @DeleteMapping(ApiConstants.Category.API_CATEGORY_DETAIL_DELETE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiConstants.Category.API_CATEGORY_DETAIL_DELETE)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200")
    @ResponseBody
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(categoryService.getCategoryById(id).orElseThrow(()-> new ItemNotFoundException("category not found")));//("category not found")));
    }

    @GetMapping(ApiConstants.Category.API_CATEGORY_LIST)
    @Operation(description = "Category endpoint to create category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    @ResponseBody
    public ResponseEntity<List<CategoryDto>> findAll(){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PutMapping(ApiConstants.Category.API_CATEGORY_CREATE_UPDATE)
    @Operation(description = "Category endpoint to update category", summary = "This is a summary for account post endpoint")
    @ApiResponse(description = "Success", responseCode = "200",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))})
    @ResponseBody
    public ResponseEntity<CategoryDto> update(@RequestBody @Valid CategoryUpdateDto dto){
        return ResponseEntity.ok(categoryService.updateCategory(dto));
    }
}
