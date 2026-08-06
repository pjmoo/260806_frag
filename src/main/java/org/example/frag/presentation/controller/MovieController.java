package org.example.frag.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.example.frag.domain.service.MovieService;
import org.example.frag.infra.exception.NoMovieException;
import org.example.frag.presentation.dto.MovieFormDTO;
import org.example.frag.presentation.dto.MovieViewDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        // 검증
        if (bindingResult.hasErrors()) {
            return "movies/new";
        }
        // 예외
//        try {
        movieService.insert(movieFormDTO.toEntity());
//        } catch (DataIntegrityViolationException e) {
//        } catch (Exception e) {
        // org.springframework.dao.DataIntegrityViolationException
//            System.out.println(e.getClass().getName());
//            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//        }
        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String findMovie(@PathVariable Long id, Model model) {
        model.addAttribute("movie", MovieViewDTO.fromDTO(movieService.findById(id)));
        return "movies/detail";
    }

    @GetMapping("/{id}/edit")
    public String editMovie(@PathVariable Long id, Model model) {
        // 결과적으로 어차피 title, price가 있기 때문에 굳이 FormDTO로 안해도 ViewDTO로 파싱 가능
        model.addAttribute("movie", MovieViewDTO.fromDTO(movieService.findById(id)));
        model.addAttribute("movieId", id);
        return "movies/new";
    }

    @PostMapping("/{id}/edit")
    public String updateMovie(
            @PathVariable Long id,
            @Validated @ModelAttribute("movie") MovieFormDTO movieFormDTO,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movieId", id);
            return "movies/new";
        }
        movieService.update(movieFormDTO.toEntity(id));
//        return "redirect:/movies";
        return "redirect:/movies/%d".formatted(id);
    }

    @PostMapping("/{id}/delete")
    public String deleteMovie(
            @PathVariable Long id
    ) {
        movieService.delete(id);
        return "redirect:/movies";
    }

    //    @ExceptionHandler
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT) // 409
    public String handleUnique(
            DataIntegrityViolationException e,
            RedirectAttributes redirectAttributes,
            Model model) {
//        redirectAttributes.addFlashAttribute(
//                "errorMessage",
//                "다시 입력할 수 없는 데이터를 포함했습니다 (%s)".formatted(e.getMessage()));
//        return "redirect:/movies";
        model.addAttribute("errorMessage",
                "다시 입력할 수 없는 데이터를 포함했습니다 (%s)".formatted(e.getMessage()));
        return "error/unique";
    }

    @ExceptionHandler(NoMovieException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND) // 404
    // @ExceptionHandler({NoMovieException.class, DataIntegrityViolationException.class})
    // -> Handler가 아니라 Exception 자체에 표시할 수 있다 (status code를)
    public String handleNoMovie(
            NoMovieException e,
            Model model) {
        model.addAttribute("errorMessage",
                "없는 영화를 호출했습니다 (%s)".formatted(e.getMessage()));
        return "error/404";
    }
}
