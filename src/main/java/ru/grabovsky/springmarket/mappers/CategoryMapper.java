package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grabovsky.market.api.dto.category.CategoryDto;
import ru.grabovsky.springmarket.entity.category.Category;
import ru.grabovsky.springmarket.exceptions.categories.CategoryNotFoundException;
import ru.grabovsky.springmarket.repositories.CategoryRepository;

/**
 * CategoryMapper
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 16:25
 */
@Mapper
public abstract class CategoryMapper {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Mappings(value = {
            @Mapping(target = "categoryName", source = "name")
    })
    public abstract CategoryDto mapToDto(Category category);

    @Mappings(value = {
            @Mapping(target = "name", source = "categoryName")
    })
    public abstract Category mapFromDto(CategoryDto category);

    public Category map(String name) {
        return categoryRepository.findByName(name).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Категория %s не найдена", name)));
    }
}
