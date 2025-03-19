package org.example.calorie_tracker.service.user.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.calorie_tracker.service.user.validation.validator.GoalValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GoalValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Goal {
    String message() default "goal is unknown";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
