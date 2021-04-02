package ru.bellintegrator.practice.controller.handlers;

import java.util.StringJoiner;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.view.response.ErrorView;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {
            SaveException.class,
            UpdateException.class,
            MissingServletRequestParameterException.class,
            DataNotFoundException.class
    })
    public ErrorView handleException(Exception e) {
        return new ErrorView(e.getMessage());
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class
    })
    public ErrorView handleValidationException(Exception e) {
        StringJoiner joiner = new StringJoiner("; ");
        for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
            joiner.add(error.getDefaultMessage());
        }
        return new ErrorView(joiner.toString());
    }
}
