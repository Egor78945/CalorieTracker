package org.example.calorie_tracker.service.user.validation.annotation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.calorie_tracker.service.user.validation.validator.LettersValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LettersValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Schema(name = "Letters", description = "Аннотация валидации текста на содержание только букв и пробелов")
public @interface Letters {
    String message() default "string not contains only letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
