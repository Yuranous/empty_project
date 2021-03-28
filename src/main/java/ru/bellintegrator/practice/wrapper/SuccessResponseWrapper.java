package ru.bellintegrator.practice.wrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class  SuccessResponseWrapper<T> extends ResponseWrapper {
    public T data;
}
