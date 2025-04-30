package com.tms.news.services;

import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.CategoryService;
import com.tms.news.core.mappers.CategoryMapper;
import com.tms.news.core.models.dtos.CategoryCreateDto;
import com.tms.news.core.models.dtos.CategoryDto;
import com.tms.news.core.models.dtos.CategoryUpdateDto;
import com.tms.news.core.models.entities.Category;
import com.tms.news.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category->categoryMapper.entityToDto(category))
                .toList();
    }

    @Override
    public Optional<CategoryDto> getCategoryById(UUID id) {
        return categoryRepository.findById(id).map(categoryMapper::entityToDto);
    }

    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryDto) {
        Category entity = categoryMapper.dtoToEntity(categoryDto);
        return categoryMapper.entityToDto(categoryRepository.save(entity));
    }

    @Override
    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto updateCategory(CategoryUpdateDto dto) {
        Category entity = categoryRepository.findById(dto.id())
                .orElseThrow(()-> new ItemNotFoundException("category not found"));
        categoryMapper.dtoToEntity(dto, entity);
        return categoryMapper.entityToDto(categoryRepository.save(entity));
    }
}
