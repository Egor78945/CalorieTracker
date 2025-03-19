package org.example.calorie_tracker.service.user.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.example.calorie_tracker.service.user.validation.annotation.Goal;

public class GoalValidator implements ConstraintValidator<Goal, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return GoalMapper.mapTo(s.toUpperCase()) != null;
    }
}
