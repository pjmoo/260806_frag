package org.example.frag.presentation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.example.frag.domain.entity.MovieEntity;

@Builder
public record MovieFormDTO(
        @NotBlank @Size(max = 16) String title,
        @Positive @Max(20000) int price
) {
    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .title(title)
                .price(price)
                .build();
    }
}
