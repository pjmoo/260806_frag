package org.example.frag.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.service.MovieService;
import org.example.frag.presentation.dto.MovieFormDTO;
import org.example.frag.presentation.dto.MovieViewDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("movies",
                movieService.findAll()
                        .stream().map(MovieViewDTO::fromDTO)
                        .toList());
        return "movies/list";
    }

    @GetMapping("/new")
    public String newMovie(Model model) {
        model.addAttribute("movie", new MovieFormDTO("", 1));
        return "movies/new";
    }

    @PostMapping("/new")
    public String createMovie(
            @Validated @ModelAttribute("movie") MovieFormDTO movieFormDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "movies/new";
        }
        movieService.insert(movieFormDTO.toEntity());
        return "redirect:/movies";
    }
}
