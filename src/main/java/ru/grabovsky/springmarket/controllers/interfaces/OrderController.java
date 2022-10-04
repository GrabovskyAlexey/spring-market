package ru.grabovsky.springmarket.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.api.dto.order.OrderDto;
import ru.grabovsky.market.api.dto.util.MessageDto;
import ru.grabovsky.market.api.dto.util.PageDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

/**
 * Интерфейс OrderController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 * @created 20.09.2022 18:20
 */
@Validated
@Tag(name = "order", description = "Order api")
public interface OrderController {
    /**
     * GET /${market.api.url}/orders : Get all user orders
     *
     * @param principal UserDetails
     * @param p        Page index
     * @param limit    Limit orders per page
     * @return Page or user orders (status code 200)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "getAllOrdersByUsername",
            summary = "Get all user orders",
            tags = {"order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Page of user orders", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @GetMapping(
            produces = {"application/json"}
    )
    ResponseEntity<PageDto<OrderDto>> getAllOrdersByUsername(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "p", description = "Page index")
            @RequestParam(value = "p", defaultValue = "1", name = "p")
            @Positive(message = "Значение должно быть больше 0") Integer p,
            @Parameter(name = "limit", description = "Limit orders per page")
            @RequestParam(value = "limit", defaultValue = "25")
            @Positive(message = "Значение должно быть больше 0") Integer limit
    );

    /**
     * GET /${market.api.url}/orders/{id} : Get order by id
     *
     * @param principal UserDetails
     * @param id Order id
     * @return Order (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getOrderById",
            summary = "Get order by id",
            tags = {"order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<OrderDto> getOrderById(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "id", description = "Order id", required = true) @PathVariable("id") Long id
    );

    /**
     * POST /${market.api.url}/orders : Create order
     *
     * @param principal UserDetails
     * @param dto User order
     * @return Information about created order (status code 200)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "createOrder",
            summary = "Create order",
            tags = {"order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @PostMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<MessageDto> createOrder(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "order", description = "Order", required = true) @Valid @RequestBody OrderDto dto
    );

    /**
     * PUT /${market.api.url}/orders/{id} : Update order by id
     *
     * @param id Order id
     * @return Information about updated order (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "updateOrderById",
            summary = "Update order by id",
            tags = {"order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @PutMapping(
            value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<MessageDto> updateOrderById(
            @Parameter(name = "id", description = "Order id", required = true) @PathVariable("id") Long id,
            @Parameter(name = "order", description = "Order", required = true) @Valid @RequestBody OrderDto order
    );

    /**
     * DELETE /${market.api.url}/orders/{id} : Delete order by id
     *
     * @param id Order id
     * @return Message (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "deleteOrderById",
            summary = "Delete order by id",
            tags = {"order"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Message info", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Order not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<MessageDto> deleteOrderById(
            @Parameter(name = "id", description = "Order id", required = true) @PathVariable("id") Long id
    );
}
