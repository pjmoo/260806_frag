package org.example.frag.infra.repository;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.entity.MovieEntity;
import org.example.frag.domain.repository.MovieRepository;
import org.springframework.stereotype.Repository;

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
}
