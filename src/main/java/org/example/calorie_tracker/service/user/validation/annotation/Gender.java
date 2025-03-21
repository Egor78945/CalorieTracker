package org.example.calorie_tracker.service.user.validation.annotation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.calorie_tracker.service.user.validation.validator.GenderValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Schema(name = "Gender", description = "Аннотация валидации гендера")
public @interface Gender {
    String message() default "unknown gender";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
