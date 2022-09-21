package ru.grabovsky.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.api.dto.util.PageDto;
import ru.grabovsky.market.api.dto.product.ProductDto;
import ru.grabovsky.springmarket.entity.product.Product;
import ru.grabovsky.springmarket.exceptions.IllegalJsonFieldValueException;
import ru.grabovsky.springmarket.exceptions.products.ProductNotFoundException;
import ru.grabovsky.springmarket.mappers.CategoryMapper;
import ru.grabovsky.springmarket.mappers.ProductImagesMapper;
import ru.grabovsky.springmarket.mappers.ProductMapper;
import ru.grabovsky.springmarket.repositories.ProductRepository;
import ru.grabovsky.springmarket.services.interfaces.ProductService;

import java.util.stream.Collectors;

/**
 * Имплементация ProductService
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 19:19
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductImagesMapper productImagesMapper;
    private final CategoryMapper categoryMapper;

    /**
     * Получение страницы продуктов
     *
     * @param p Номер страницы
     * @param limit Элементов на страницу
     * @return Страницу с продуктами
     */
    @Override
    public PageDto<ProductDto> getPageProducts(Integer p, Integer limit) {
        Pageable page = PageRequest.of(p - 1, limit);
        Page<Product> products = productRepository.findAll(page);
        return PageDto.<ProductDto>builder()
                .first(products.isFirst())
                .last(products.isLast())
                .pageIndex(products.getNumber())
                .pageSize(products.getSize())
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .pageItems(
                        products.stream()
                                .map(productMapper::mapToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

    /**
     * Поучение продукта по идентификатору
     *
     * @param id Идентификатор продукта
     * @return DTO продукта
     */
    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.mapToDto(
                productRepository.findById(id).orElseThrow(
                        () -> new ProductNotFoundException(
                                String.format("Продукт с идентификатором %s не найден", id)
                        )
                )
        );
    }

    /**
     * Добавление продукта
     *
     * @param productDto Продукт для добавления
     * @return Добавленная сущность продукта
     */
    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = productMapper.mapFromDto(productDto);
        return productRepository.save(product);
    }

    /**
     * Обновление продукта
     *
     * @param id Идентификатор продукта для обновления
     * @param productDto DTO обновленного продукта
     * @return Обновленная сущность продукта
     */
    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new IllegalJsonFieldValueException("id", "При обновлении товара идентификатор не может быть пустым");
        }
        if (!id.equals(productDto.getId())) {
            throw new IllegalJsonFieldValueException("id", "Идентификатор в теле запроса не совпадает с идентификатором в адресе запроса");
        }
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(
                        String.format("Товар с идентификатором %s не найден", productDto.getId())
                )
        );
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setProductImages(
                productDto.getProductImages().stream()
                        .map(productImagesMapper::mapFromDto)
                        .collect(Collectors.toSet())
        );
        product.setCategory(categoryMapper.map(productDto.getCategoryName()));
        return productRepository.save(product);
    }

    /**
     * Удаление продукта
     *
     * @param id Идентификатор продукта для удаления
     */
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                String.format("Товар с идентификатором %s не найден", id)
                        )
                );
        productRepository.delete(product);
    }
}
