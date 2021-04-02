package ru.bellintegrator.practice.controller.handlers;

import java.lang.reflect.Type;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.view.response.DataView;
import ru.bellintegrator.practice.view.response.ErrorView;


@RestControllerAdvice
public class DataResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Type cls = methodParameter.getGenericParameterType();
        return !cls.equals(ErrorView.class) && !cls.equals(void.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse response) {
        return new DataView(body);
    }
}
