package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.category.CategoryDto;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.springmarket.controllers.interfaces.CategoryController;
import ru.grabovsky.springmarket.entity.category.Category;
import ru.grabovsky.springmarket.services.interfaces.CategoryService;

import java.util.List;

/**
 * Имплементация CategoryController
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 11:56
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${market.api.url}/categories")
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;
    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @Override
    public ResponseEntity<CategoryDto> getCategoryById(Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('addCategory')")
    public ResponseEntity<MessageDto> addCategory(CategoryDto categoryDto) {
        Category category= categoryService.addCategory(categoryDto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Категория %s добавлена с идентификатором %s",
                                category.getName(),
                                category.getId())
                )
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('updateCategory')")
    public ResponseEntity<MessageDto> updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Категория с идентификатором %s переименована в %s",
                                category.getId(),
                                category.getName())
                )
        );
    }

    @Override
    @PreAuthorize("hasAnyAuthority('deleteCategory')")
    public ResponseEntity<MessageDto> deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Категория с идентификатором %s удалена", id)
                )
        );
    }
}
