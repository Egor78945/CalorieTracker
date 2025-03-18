package org.example.calorie_tracker.service.user.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.calorie_tracker.service.user.validation.validator.UniqueEmailValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "email is busy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
