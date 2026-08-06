package org.example.frag.presentation.dto;

import lombok.Builder;
import org.example.frag.domain.entity.MovieEntity;

import java.time.Instant;

@Builder
public record MovieViewDTO(
        long id,
        String title,
        int price,
        Instant createdAt,
        Instant updatedAt
) {
    public static MovieViewDTO fromDTO(MovieEntity movieEntity) {
        return MovieViewDTO.builder()
                .id(movieEntity.getId())
                .title(movieEntity.getTitle())
                .price(movieEntity.getPrice())
                .createdAt(movieEntity.getCreatedAt())
                .updatedAt(movieEntity.getUpdatedAt())
                .build();
    }
}
