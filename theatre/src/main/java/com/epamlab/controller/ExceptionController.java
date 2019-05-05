package com.epamlab.controller;

import com.epamlab.exception.CountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionController {
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";
    private static final String MESSAGE_COUNT_EXCEPTION = "Sorry, but you ordered tickets more than we have :(";

    @ExceptionHandler(CountException.class)
    public ModelAndView getError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR);
        modelAndView.addObject(MESSAGE, MESSAGE_COUNT_EXCEPTION);
        return modelAndView;
    }
}
