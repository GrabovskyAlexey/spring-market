package ru.grabovsky.springmarket.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.grabovsky.market.api.dto.CartDto;
import ru.grabovsky.market.api.dto.CartItemDto;
import ru.grabovsky.market.api.dto.MessageDto;

import javax.validation.Valid;

/**
 * Интерфейс CartController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 * @created 18.09.2022 16:19
 */
@Validated
@Tag(name = "cart", description = "Cart api")
public interface CartController {

    /**
     * GET /${market.api.url}/cart : Get new empty cart
     *
     * @return New empty cart (status code 200)
     */
    @Operation(
            operationId = "getEmptyCart",
            summary = "Get new empty cart",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get new empty cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            produces = {"application/json"}
    )
    CartDto getEmptyCart();

    /**
     * GET /${market.api.url}/cart/{cartId} : Get cart
     *
     * @return Cart (status code 200)
     */
    @Operation(
            operationId = "getCart",
            summary = "Get cart",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Current cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{cartId}",
            produces = {"application/json"}
    )
    CartDto getCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId
    );

    /**
     * PUT /${market.api.url}/cart/{cartId} : Add product to cart
     *
     * @return Cart (status code 200)
     * or Bad Request (status code 400)
     */
    @Operation(
            operationId = "addProductToCart",
            summary = "Add product to cart",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @PutMapping(
            value = "/{cartId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    CartDto addProductToCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
            @Parameter(name = "cartItem", description = "Cart Item", required = true) @Valid @RequestBody CartItemDto cartItem
            );

    /**
     * GET /${market.api.url}/cart/{cartId}/remove/{productId} : Remove product from cart
     *
     * @return Cart (status code 200)
     */
    @Operation(
            operationId = "deleteProductFromCart",
            summary = "Delete product from cart",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{cartId}/remove/{productId}",
            produces = {"application/json"}
    )
    CartDto deleteProductFromCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
            @Parameter(name = "productId", description = "Product id", required = true) @PathVariable("productId") Long productId
    );

    /**
     * GET /${market.api.url}/cart/{cartId}/clear : Clear cart
     *
     * @return Cart (status code 200)
     */
    @Operation(
            operationId = "clearCart",
            summary = "Clear cart",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empty cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{cartId}/clear",
            produces = {"application/json"}
    )
    CartDto clearCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId
    );

    /**
     * GET /${market.api.url}/cart/{cartId}/increase/{productId} : Increase product quantity in cart by one
     *
     * @return Cart (status code 200)
     */
    @Operation(
            operationId = "increaseProductQuantityInCart",
            summary = "Increase product quantity in cart by one",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{cartId}/increase/{productId}",
            produces = {"application/json"}
    )
    CartDto increaseProductQuantityInCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
            @Parameter(name = "productId", description = "Product id", required = true) @PathVariable("productId") Long productId
    );

    /**
     * GET /${market.api.url}/cart/{cartId}/decrease/{productId} : Increase product quantity in cart by one
     *
     * @return Cart (status code 200)
     */
    @Operation(
            operationId = "decreaseProductQuantityInCart",
            summary = "Decrease product quantity in cart by one",
            tags = {"cart"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated cart", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CartDto.class))
                    })
            }
    )
    @GetMapping(
            value = "/{cartId}/decrease/{productId}",
            produces = {"application/json"}
    )
    CartDto decreaseProductQuantityInCart(
            @Parameter(name = "cartId", description = "Cart id", required = true) @PathVariable("cartId") String cartId,
            @Parameter(name = "productId", description = "Product id", required = true) @PathVariable("productId") Long productId
    );
}
