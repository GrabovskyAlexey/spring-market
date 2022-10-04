package ru.grabovsky.market.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grabovsky.market.api.dto.interfaces.PageableDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDto
 *
 * @author grabovsky.alexey
 * @created 22.09.2022 15:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Order information", name = "Order")
public class OrderDto implements PageableDto {
    @Schema(description = "Order id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Order status")
    @JsonProperty("status")
    private OrderStatus status;

    @Schema(description = "User id")
    @JsonProperty("userId")
    private Long userId;

    @Schema(description = "Delivery Address")
    @JsonProperty("address")
    private DeliveryAddressDto address;

    @Schema(description = "Order items")
    @JsonProperty("items")
    private List<OrderItemDto> items;

    @Schema(description = "Total sum")
    @JsonProperty("totalSum")
    private BigDecimal totalSum;
}
