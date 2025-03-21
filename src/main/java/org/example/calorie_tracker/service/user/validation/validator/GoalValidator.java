package org.example.calorie_tracker.service.user.validation.validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.example.calorie_tracker.service.user.validation.annotation.Goal;

@Tag(name = "Goal validator", description = "Валидатор целей")
public class GoalValidator implements ConstraintValidator<Goal, String> {
    @Override
    @Operation(description = "Проверка, является ли строка целью")
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return GoalMapper.mapTo(s.toUpperCase()) != null;
    }
}
