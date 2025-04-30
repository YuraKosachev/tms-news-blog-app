package com.tms.news.core.interfaces.services;

import com.tms.news.core.models.dtos.CategoryCreateDto;
import com.tms.news.core.models.dtos.CategoryDto;
import com.tms.news.core.models.dtos.CategoryUpdateDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    List<CategoryDto> getCategories();
    Optional<CategoryDto> getCategoryById(UUID id);
    CategoryDto createCategory(CategoryCreateDto categoryDto);
    void deleteCategoryById(UUID id);
    CategoryDto updateCategory(CategoryUpdateDto dto);
}
