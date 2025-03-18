package org.example.calorie_tracker.service.user.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.validation.annotation.Gender;

public class GenderValidator implements ConstraintValidator<Gender, Character> {
    @Override
    public boolean isValid(Character g, ConstraintValidatorContext constraintValidatorContext) {
        return g == 'm' || g == 'f';
    }
}
