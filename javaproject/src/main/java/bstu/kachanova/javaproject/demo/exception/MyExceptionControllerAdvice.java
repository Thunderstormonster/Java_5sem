package bstu.kachanova.javaproject.demo.exception;

import bstu.kachanova.javaproject.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//глобальный обработчик исключений

@ControllerAdvice
public class MyExceptionControllerAdvice {
    //список обрабатываемых исключений
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Object> handleControllerException(
            ControllerException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

