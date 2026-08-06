package org.example.frag.infra.repository;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.entity.MovieEntity;
import org.example.frag.domain.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Primary
//@Profile()
@Repository
@RequiredArgsConstructor
public class JpaMovieRepository implements MovieRepository {
    private final MovieJpaRepository movieJpaRepository;

    @Override
    public void insert(MovieEntity movieEntity) {
        movieJpaRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findAll() {
        return movieJpaRepository.findAll();
    }

    @Override
    public MovieEntity findById(Long id) {
        return movieJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public void update(MovieEntity entity) {
        movieJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        movieJpaRepository.deleteById(id);
    }
}
