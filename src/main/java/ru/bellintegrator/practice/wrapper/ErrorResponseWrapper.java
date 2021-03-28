package ru.bellintegrator.practice.wrapper;

import lombok.AllArgsConstructor;
import ru.bellintegrator.practice.view.ErrorView;

@AllArgsConstructor
public class ErrorResponseWrapper extends ResponseWrapper {
    public ErrorView error;
}
