package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.market.api.dto.category.CategoryDto;
import ru.grabovsky.springmarket.entity.category.Category;

import java.util.List;

/**
 * CategoryService
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 15:54
 */
public interface CategoryService {
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Long id);
    Category addCategory(CategoryDto category);
    Category updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
