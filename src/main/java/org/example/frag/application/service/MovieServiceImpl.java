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

    @Override
    public MovieEntity findById(Long id) {
        return movieRepository.findById(id);
    }

    @Transactional
    @Override
    public void update(MovieEntity entity) {
        findById(entity.getId()); // 해당 id로 존재하는지 검사용
        movieRepository.update(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        findById(id); // 해당 id로 존재하는지 검사용
        movieRepository.deleteById(id);
    }
}
