package ru.bellintegrator.practice.view.wrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class  SuccessResponseWrapper<T> extends ResponseWrapper {
    public T data;
}
