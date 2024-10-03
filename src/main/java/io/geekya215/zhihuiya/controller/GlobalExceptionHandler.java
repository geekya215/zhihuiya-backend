package io.geekya215.zhihuiya.controller;

import io.geekya215.zhihuiya.response.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handleGlobalException(Exception ex) {
        return CommonResponse.fail(70000000, ex.getMessage());
    }
}
