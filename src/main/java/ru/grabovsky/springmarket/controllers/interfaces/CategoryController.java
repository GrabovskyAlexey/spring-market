package ru.grabovsky.springmarket.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.market.api.dto.category.CategoryDto;
import ru.grabovsky.market.api.dto.util.MessageDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Интерфейс CategoryController с аннотациями для генерации OpenApi документации
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 11:37
 */

@Validated
@Tag(name = "category", description = "Category api")
public interface CategoryController {

    /**
     * GET /${market.api.url}/categories : Get all categories
     *
     * @return List of category (status code 200)
     * or Bad Request (status code 400)
     */
    @Operation(
            operationId = "getAllCategories",
            summary = "Get all categories",
            tags = {"category"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get all categories", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(
                                    schema = @Schema(implementation = CategoryDto.class)))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @GetMapping(
            produces = {"application/json"}
    )
    ResponseEntity<List<CategoryDto>> getAllCategories();

    /**
     * GET /${market.api.url}/categories/{id} : Get category by id
     *
     * @param id category id (required)
     * @return Get one category (status code 200)
     * or Bad Request (status code 400)
     * or Not found category (status code 404)
     */
    @Operation(
            operationId = "getCategoryById",
            summary = "Get category by id",
            tags = {"category"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get one category", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
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
    ResponseEntity<CategoryDto> getCategoryById(
            @Parameter(name = "id", description = "category id", required = true) @PathVariable("id") Long id
    );

    /**
     * POST /categories : Add category
     *
     * @param category Category Item (required)
     * @return Successfully add category (status code 201)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
            operationId = "addCategory",
            summary = "Add category",
            tags = {"category"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully add category", content = {
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
    ResponseEntity<MessageDto> addCategory(
            @Parameter(name = "Category", description = "Category Item", required = true) @Valid @RequestBody CategoryDto category
    );

    /**
     * PUT /${market.api.url}/categories/{id} : Update category by id
     *
     * @param category category item (required)
     * @return Successfully update category (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found category (status code 404)
     */
    @Operation(
            operationId = "updateCategory",
            summary = "Update category",
            tags = {"category"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully update category", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @PutMapping(
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<MessageDto> updateCategory(
            @Parameter(name = "id", description = "category id", required = true) @PathVariable("id") Long id,
            @Parameter(name = "Category", description = "Category Item", required = true) @Valid @RequestBody CategoryDto category
    );

    /**
     * DELETE /${market.api.url}/categories/{id} : Delete category by id
     *
     * @param id       category id (required)
     * @return Successfully delete category (status code 200)
     * or Bad Request (status code 400)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not found category (status code 404)
     */
    @Operation(
            operationId = "deleteCategory",
            summary = "Delete category",
            tags = {"category"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully delete category", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto.class))
                    })
            }
    )
    @DeleteMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<MessageDto> deleteCategory(
            @Parameter(name = "id", description = "category id", required = true) @PathVariable("id") Long id
    );
}
