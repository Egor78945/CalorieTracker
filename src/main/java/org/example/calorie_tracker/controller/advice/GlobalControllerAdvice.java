package org.example.calorie_tracker.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = GlobalControllerExceptionHandler.class)
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationExceptionHandler(MethodArgumentNotValidException e) {
        var map = new HashMap<String, String>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(Map.of("runtime error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
