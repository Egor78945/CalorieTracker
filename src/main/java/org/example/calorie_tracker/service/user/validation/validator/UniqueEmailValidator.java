package org.example.calorie_tracker.service.user.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.validation.annotation.UniqueEmail;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.existsUserByEmail(email);
    }
}
