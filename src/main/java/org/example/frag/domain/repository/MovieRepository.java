package org.example.frag.domain.repository;

import org.example.frag.domain.entity.MovieEntity;

import java.util.List;

public interface MovieRepository {
    void insert(MovieEntity movieEntity);

    List<MovieEntity> findAll();

    MovieEntity findById(Long id);
}
