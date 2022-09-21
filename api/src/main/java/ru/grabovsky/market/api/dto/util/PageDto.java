package ru.grabovsky.market.api.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import ru.grabovsky.market.api.dto.interfaces.PageableDto;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * DTO для пагинации
 *
 * @author grabovsky.alexey
 * @created 16.09.2022 13:55
 */
@Data
@Builder
@Schema(description = "Page for pagination", name = "Page")
public class PageDto <T extends PageableDto>{
    @Schema(description = "Page elements")
    @JsonProperty("pageItems")
    private List<T> pageItems;

    @Schema(description = "Page index")
    @Positive
    @JsonProperty("pageIndex")
    private Integer pageIndex;

    @Schema(description = "First page?")
    @JsonProperty("first")
    private Boolean first;

    @Schema(description = "Last page?")
    @JsonProperty("last")
    private Boolean last;

    @Schema(description = "Page size")
    @JsonProperty("pageSize")
    private Integer pageSize;

    @Schema(description = "Total pages")
    @JsonProperty("totalPages")
    private Integer totalPages;

    @Schema(description = "Total Elements")
    @JsonProperty("totalElements")
    private Long totalElements;
}
