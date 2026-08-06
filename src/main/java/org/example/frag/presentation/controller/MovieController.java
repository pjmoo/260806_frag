package org.example.frag.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String index() {
        return "movies/list";
    }

    @GetMapping("/new")
    public String newMovie() {
        return "movies/new";
    }
}
