package ru.grabovsky.springmarket.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.api.dto.MessageDto;
import ru.grabovsky.market.api.dto.PageDto;
import ru.grabovsky.market.api.dto.ProductDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

/**
 * Интерфейс ProductController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 11:37
 */
@Validated
@Tag(name = "product", description = "Product api")
public interface ProductController {
    /**
     * GET /${market.api.url}/products : Get page of product
     *
     * @param p Page index
     * @param limit Limit products per page
     * @return Page of products (status code 200)
     * or Bad Request (status code 400)
     */
    @Operation(
            operationId = "getPageProducts",
            summary = "Get page of product",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Page of products", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @GetMapping(
            produces = {"application/json"}
    )
    ResponseEntity<PageDto<ProductDto>> getPageProducts(
            @Parameter(name = "p", description = "Page index")
                @RequestParam(value = "p", defaultValue = "1", name = "p")
                @Positive(message = "Значение должно быть больше 0") Integer p,
            @Parameter(name = "limit", description = "Limit products per page")
                @RequestParam(value = "limit", defaultValue = "25")
                @Positive(message = "Значение должно быть больше 0")  Integer limit
    );

    /**
     * GET /${market.api.url}/products/{id} : Get product by id
     *
     * @param id product id (required)
     * @return Get one product (status code 200)
     * or Bad Request (status code 400)
     * or Not found product (status code 404)
     */
    @Operation(
            operationId = "getProductById",
            summary = "Get product by id",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get one product", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<ProductDto> getProductById(
            @Parameter(name = "id", description = "product id", required = true) @PathVariable("id") Long id
    );

    /**
     * POST /products : Add product
     *
     * @param products Product Item (required)
     * @return Successfully add products (status code 201)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
            operationId = "addProduct",
            summary = "Add product",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully add product", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
            }
    )
    @PostMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<MessageDto> addProduct(
            @Parameter(name = "Product", description = "Product Item", required = true) @Valid @RequestBody ProductDto products
    );

    /**
     * PUT /${market.api.url}/products/{id} : Update product by id
     *
     * @param product product item (required)
     * @return Successfully update product (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found product (status code 404)
     */
    @Operation(
            operationId = "updateProduct",
            summary = "Update product",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully update product", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @PutMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<MessageDto> updateProduct(
            @Parameter(name = "id", description = "product id", required = true) @PathVariable("id") Long id,
            @Parameter(name = "Product", description = "Product Item", required = true) @Valid @RequestBody ProductDto product
    );

    /**
     * DELETE /${market.api.url}/products/{id} : Delete product by id
     *
     * @param id       product id (required)
     * @return Successfully delete product (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found product (status code 404)
     */
    @Operation(
            operationId = "deleteProduct",
            summary = "Delete product",
            tags = {"product"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully delete product", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Product not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<MessageDto> deleteProduct(
            @Parameter(name = "id", description = "product id", required = true) @PathVariable("id") Long id)
    ;
}
