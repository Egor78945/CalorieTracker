package org.example.calorie_tracker.service.user.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.calorie_tracker.service.user.validation.validator.GenderValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
    String message() default "unknown gender";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
