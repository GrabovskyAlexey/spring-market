package ru.grabovsky.springmarket.services.interfaces;

import ru.grabovsky.market.api.dto.PageDto;
import ru.grabovsky.market.api.dto.ProductDto;
import ru.grabovsky.springmarket.entity.Product;

/**
 * Интерфейс ProductService
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 15:55
 */
public interface ProductService {
    PageDto<ProductDto> getPageProducts(Integer p, Integer limit);

    ProductDto getProductById(Long id);

    Product addProduct(ProductDto products);

    Product updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
