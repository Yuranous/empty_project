package ru.bellintegrator.practice.controller.handlers;

import java.util.StringJoiner;
import javax.servlet.http.HttpServletResponse;
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
    public ErrorView handle(Exception e, HttpServletResponse response) {
        if (e instanceof MethodArgumentNotValidException) {
            StringJoiner joiner = new StringJoiner("; ");
            for (ObjectError error : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
                joiner.add(error.getDefaultMessage());
            }
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return new ErrorView(joiner.toString());
        }
        if (e instanceof SaveException || e instanceof UpdateException || e instanceof DataNotFoundException) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new ErrorView(e.getMessage());
        }
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ErrorView("Something went wrong");
    }
}
