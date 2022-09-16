package ru.grabovsky.springmarket.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.api.dto.AuthRequestDto;
import ru.grabovsky.market.api.dto.AuthResponseDto;
import ru.grabovsky.market.api.dto.MessageDto;

import javax.validation.Valid;

/**
 * Интерфейс AuthController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 */

@Validated
@Tag(name = "auth", description = "Authenticate api")
public interface AuthController {


    /**
     * POST /auth : Authenticate
     *
     * @param request Authenticate Request (required)
     * @return Successfully authenticate (status code 200)
     *         or Bad Request (status code 400)
     */
    @Operation(
            operationId = "authenticate",
            summary = "Authenticate",
            tags = { "auth" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully authenticate", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponseDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @PostMapping(
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<AuthResponseDto> authenticate(
            @Parameter(name = "AuthRequestDto", description = "Authenticate request", required = true) @Valid @RequestBody AuthRequestDto request
    );
}
