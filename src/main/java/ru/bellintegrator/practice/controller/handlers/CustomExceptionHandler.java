package ru.bellintegrator.practice.controller.handlers;

import java.util.StringJoiner;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.view.response.ErrorView;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ErrorView handle(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            StringJoiner joiner = new StringJoiner("; ");
            for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
                joiner.add(error.getDefaultMessage());
            }
            return new ErrorView(joiner.toString(), HttpStatus.NOT_ACCEPTABLE.toString());
        }
        if (e instanceof SaveException || e instanceof UpdateException || e instanceof DataNotFoundException) {
            return new ErrorView(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
        }
        return new ErrorView("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
}
