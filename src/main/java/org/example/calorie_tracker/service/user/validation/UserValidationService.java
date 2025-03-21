package org.example.calorie_tracker.service.user.validation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
@Tag(name = "User validation service", description = "Валидатор пользователей")
public class UserValidationService implements ValidationService<User> {
    @Override
    public boolean isValid(User user) {
        double dividedHeight = ((double) user.getHeight()) / 100;
        double imt = user.getWeight() / (dividedHeight * dividedHeight);
        return imt >= 10 && imt <= 50;
    }
}
