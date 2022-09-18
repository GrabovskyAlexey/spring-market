package ru.grabovsky.springmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.api.dto.ProductDto;
import ru.grabovsky.springmarket.entity.Product;
import ru.grabovsky.springmarket.entity.ProductImage;

/**
 * ProductMapper
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 16:35
 */
@Mapper(uses = {CategoryMapper.class, ProductImagesMapper.class})
public interface ProductMapper {

    @Mappings(value = {
            @Mapping(target = "categoryName", source = "category.name"),
    })
    ProductDto mapToDto(Product product);
    @Mappings(value = {
            @Mapping(target = "category", source = "categoryName")
    })
    Product mapFromDto(ProductDto product);

    default String map(ProductImage image) {
        return image.getImageUrl();
    }
}
