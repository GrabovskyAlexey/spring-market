package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.category.CategoryDto;
import ru.grabovsky.springmarket.entity.category.Category;
import ru.grabovsky.springmarket.exceptions.IllegalJsonFieldValueException;
import ru.grabovsky.springmarket.exceptions.categories.CategoryNotFoundException;
import ru.grabovsky.springmarket.mappers.CategoryMapper;
import ru.grabovsky.springmarket.repositories.CategoryRepository;
import ru.grabovsky.springmarket.services.interfaces.CategoryService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Имплементация CategoryService
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 16:15
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    /**
     * Получение всех категорий
     *
     * @return Список DTO категорий
     */
    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Получение категорий по идентификатору
     *
     * @param id Идентификатор категории
     * @return DTO категории
     */
    @Override
    public CategoryDto getCategoryById(Long id) {
        return null;
    }

    /**
     * Добавление категории
     *
     * @param category DTO категории для добавления
     * @return Добавленная сущность категории
     */
    @Override
    public Category addCategory(CategoryDto category) {
        return categoryRepository.save(categoryMapper.mapFromDto(category));
    }

    /**
     * Обновление категории
     *
     * @param id Идентификатор категории для обновления
     * @param categoryDto DTO обновленной категории
     * @return Обновленная сущность категории
     */
    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        if(categoryDto.getId() == null){
            throw new IllegalJsonFieldValueException("id", "При обновлении категории идентификатор не может быть пустым");
        }
        if(!Objects.equals(categoryDto.getId(), id)){
            throw new IllegalJsonFieldValueException("id", "Идентификатор в теле запроса не совпадает с идентификатором в адресе запроса");
        }
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Категория с идентификатором %s не найдена", categoryDto.getId())
                ));
        category.setName(categoryDto.getCategoryName());
        return categoryRepository.save(category);
    }

    /**
     * Удаление категории
     *
     * @param id Идентификатор категории для удаления
     */
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Категория с идентификатором %s не найдена", id)
                ));
        categoryRepository.delete(category);
    }
}
