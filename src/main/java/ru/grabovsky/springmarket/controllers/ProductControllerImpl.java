package ru.grabovsky.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.api.dto.MessageDto;
import ru.grabovsky.market.api.dto.PageDto;
import ru.grabovsky.market.api.dto.ProductDto;
import ru.grabovsky.springmarket.controllers.interfaces.ProductController;
import ru.grabovsky.springmarket.entity.Product;
import ru.grabovsky.springmarket.services.interfaces.ProductService;

/**
 * /**
 * Имплементация ProductController
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 13:32
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${market.api.url}/products")
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<PageDto<ProductDto>> getPageProducts(Integer p, Integer limit) {
        return ResponseEntity.ok(productService.getPageProducts(p, limit));
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('addProduct')")
    public ResponseEntity<MessageDto> addProduct(ProductDto productDto) {
        Product product = productService.addProduct(productDto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Товар %s успешно добавлен в категорию %s с идентификатором %s",
                                product.getTitle(),
                                product.getId(),
                                product.getCategory().getName()
                        )
                )
        );

    }

    @Override
    @PreAuthorize("hasAnyAuthority('updateProduct')")
    public ResponseEntity<MessageDto> updateProduct(Long id, ProductDto productDto) {
        Product product = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Товар с идентификатором %s успешно обновлен", product.getId())
                )
        );
    }


    @Override
    @PreAuthorize("hasAnyAuthority('deleteProduct')")
    public ResponseEntity<MessageDto> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                new MessageDto(
                        String.format("Товар с идентификатором %s успешно удален", id)
                )
        );
    }
}
