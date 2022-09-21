package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import ru.grabovsky.market.api.dto.product.ProductImageDto;
import ru.grabovsky.springmarket.entity.product.ProductImage;

/**
 * ProductImagesMapper
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 16:47
 */
@Mapper
public interface ProductImagesMapper {
    ProductImage mapFromDto(ProductImageDto dto);
    ProductImageDto mapToDto(ProductImage image);
}
