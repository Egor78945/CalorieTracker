package org.example.calorie_tracker.service.user.validation.validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.validation.annotation.Gender;

@Tag(name = "Gender validator", description = "Валидатор гендера")
public class GenderValidator implements ConstraintValidator<Gender, Character> {
    @Override
    @Operation(description = "Проверка, является ли гендер существующим")
    public boolean isValid(Character g, ConstraintValidatorContext constraintValidatorContext) {
        return g == 'm' || g == 'f';
    }
}
