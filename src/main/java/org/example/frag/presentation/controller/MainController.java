package org.example.frag.presentation.controller;

import org.example.frag.domain.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping
public class MainController {
    private final MovieService movieService;

    public MainController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String index() {
        Random random = new Random();
        // try catch?
        // 따로 exception Handler?
        // 404? (white label? BasicErrorController?)
        movieService.findById(random.nextLong(100));
        return "index";
    }
}
