package org.example.calorie_tracker.service.user.validation;

import lombok.RequiredArgsConstructor;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService implements ValidationService<User> {
    @Override
    public boolean isValid(User user) {
        double dividedHeight = ((double) user.getHeight()) / 100;
        double imt = user.getWeight() / (dividedHeight * dividedHeight);
        return imt >= 10 && imt <= 50;
    }
}
