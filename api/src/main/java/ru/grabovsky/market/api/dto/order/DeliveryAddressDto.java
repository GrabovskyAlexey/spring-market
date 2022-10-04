package ru.grabovsky.market.api.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DeliveryAddressDto
 *
 * @author grabovsky.alexey
 * @created 22.09.2022 15:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Delivery Address", name = "DeliveryAddress")
public class DeliveryAddressDto {
    @Schema(description = "Address id")
    @JsonProperty("id")
    private Long id;

    @Schema(description = "Country")
    @JsonProperty("country")
    private String country;

    @Schema(description = "City")
    @JsonProperty("city")
    private String city;

    @Schema(description = "Region")
    @JsonProperty("region")
    private String region;

    @Schema(description = "Street")
    @JsonProperty("street")
    private String street;

    @Schema(description = "House")
    @JsonProperty("house")
    private String house;

    @Schema(description = "Flat")
    @JsonProperty("flat")
    private Integer flat;
}

