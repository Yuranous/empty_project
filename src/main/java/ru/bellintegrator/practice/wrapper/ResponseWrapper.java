package ru.bellintegrator.practice.wrapper;

import ru.bellintegrator.practice.view.ErrorView;

public class ResponseWrapper {

    public static <T> SuccessResponseWrapper<T> getSuccessResponse(T data) {
        return new SuccessResponseWrapper<T>(data);
    }

    public static ErrorResponseWrapper getErrorResponse(String code, String message) {
        ErrorView error = new ErrorView(code, message);
        return new ErrorResponseWrapper(error);
    }
}
