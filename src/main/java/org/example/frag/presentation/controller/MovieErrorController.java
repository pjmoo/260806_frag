package org.example.frag.presentation.controller;

import org.example.frag.infra.exception.NoMovieException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice(basePackages = "org.example.frag.presentation.controller")
//@ControllerAdvice(basePackages = "org.example.frag.presentation.controller.movie")
//@ControllerAdvice(basePackages = "org.example.frag.presentation.controller.user")
//@ControllerAdvice(basePackages = {...})
@ControllerAdvice
public class MovieErrorController {
    @ExceptionHandler(NoMovieException.class)
    public String handleNoMovie(NoMovieException e, Model model) {
        model.addAttribute("errorMessage", "없는 영화를 뽑았네요");
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", "내가 다 처리해버림");
        return "error/500";
    }
}
