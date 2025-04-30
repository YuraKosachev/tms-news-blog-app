package com.tms.news.core.mappers;

import com.tms.news.core.models.dtos.CategoryCreateDto;
import com.tms.news.core.models.dtos.CategoryDto;
import com.tms.news.core.models.dtos.CategoryUpdateDto;
import com.tms.news.core.models.entities.Category;
import com.tms.news.core.models.kafka.NewsPublisher;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public void dtoToEntity(CategoryUpdateDto categoryDto, Category category) {
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
    }

    public CategoryDto entityToDto(Category category) {
        if(category == null)
            return null;
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category kafkaMessageToEntity(NewsPublisher publisher) {
        if(publisher == null)
            return null;

        return Category.builder()
                .name(publisher.portal())
                .build();
    }

    public Category kafkaMessageToEntity(String category) {
        if(category == null)
            return null;

        return Category.builder()
                .name(category)
                .build();
    }

    public Category dtoToEntity(CategoryCreateDto dto) {
        if(dto == null)
            return null;
        return Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

}
