package org.example.calorie_tracker.controller.advice.handler;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Tag(name = "Global controller exception handler", description = "Аннотация для пометки контроллеров, ошибки которых будет отлавливать GlobalControllerAdvice")
public @interface GlobalControllerExceptionHandler {
}
