package org.example.frag.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.example.frag.domain.entity.MovieEntity;

@Builder
public record MovieFormDTO(
        @NotBlank @Size(max = 16) String title,
        @Positive @Max(20000) @NotNull Integer price
) {
    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .title(title)
                .price(price)
                .build();
    }

    public MovieEntity toEntity(long id) {
        MovieEntity entity = toEntity();
        entity.setId(id);
        return entity;
    }
}
