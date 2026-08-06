package org.example.frag.application.service;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.entity.MovieEntity;
import org.example.frag.domain.repository.MovieRepository;
import org.example.frag.domain.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    @Override
    public void insert(MovieEntity movieEntity) {
        movieRepository.insert(movieEntity);
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieRepository.findAll();
    }
}
