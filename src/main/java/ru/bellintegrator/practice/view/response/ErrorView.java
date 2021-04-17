package ru.bellintegrator.practice.view.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorView {
    public String error;
    public String httpStatus;
}
