package org.example.frag.domain.service;

import org.example.frag.domain.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    void insert(MovieEntity movieEntity);

    List<MovieEntity> findAll();

    MovieEntity findById(Long id);
}
