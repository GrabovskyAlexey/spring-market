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
import ru.grabovsky.market.api.dto.order.DeliveryAddressDto;
import ru.grabovsky.market.api.dto.util.MessageDto;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Интерфейс AddressController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 * @created 23.09.2022 16:13
 */
@Validated
@Tag(name = "address", description = "User delivery address api")
public interface AddressController {
    /**
     * GET /${market.api.url}/addresses : Get all user addresses
     *
     * @param principal UserDetails
     * @return List of user addresses (status code 200)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "getAllAddressesByUsername",
            summary = "Get all user addresses",
            tags = {"address"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of user addresses", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @GetMapping(
            produces = {"application/json"}
    )
    ResponseEntity<List<DeliveryAddressDto>> getAllAddressesByUsername(
            @Parameter(hidden = true) Principal principal
    );

    /**
     * GET /${market.api.url}/addresses/{id} : Get address by id
     *
     * @param principal UserDetails
     * @param id Address id
     * @return Address (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getaAddressById",
            summary = "Get Address by id",
            tags = {"address"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delivery address", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = DeliveryAddressDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Address not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<DeliveryAddressDto> getaAddressById(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "id", description = "Address id", required = true) @PathVariable("id") Long id
    );

    /**
     * POST /${market.api.url}/addresses : Create address
     *
     * @param principal UserDetails
     * @param dto User address
     * @return Information about created address (status code 200)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "createAddress",
            summary = "Create address",
            tags = {"address"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delivery address", content = {
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
    ResponseEntity<MessageDto> createAddress(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "address", description = "Delivery Address", required = true) @Valid @RequestBody DeliveryAddressDto dto
    );

    /**
     * PUT /${market.api.url}/addresses/{id} : Update address by id
     *
     * @param principal UserDetails
     * @param id Address id
     * @param dto Delivery address dto
     * @return Information about updated address (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "updateAddressById",
            summary = "Update address by id",
            tags = {"address"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Delivery address", content = {
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
    ResponseEntity<MessageDto> updateAddressById(
            @Parameter(hidden = true) Principal principal,
            @Parameter(name = "id", description = "Address id", required = true) @PathVariable("id") Long id,
            @Parameter(name = "address", description = "Delivery Address", required = true) @Valid @RequestBody DeliveryAddressDto dto
    );

    /**
     * DELETE /${market.api.url}/addresses/{id} : Delete address by id
     *
     * @param id Order id
     * @return Message (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "deleteAddressById",
            summary = "Delete address by id",
            tags = {"address"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Message info", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Address not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            },
            security = @SecurityRequirement(name = "bearer")
    )
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<MessageDto> deleteAddressById(
            @Parameter(name = "id", description = "Address id", required = true) @PathVariable("id") Long id
    );
}
