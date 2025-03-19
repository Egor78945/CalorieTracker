package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;

public abstract class CalorieService {
    protected final UserRepository userRepository;

    public CalorieService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public abstract int getCaloriePerDayForUserByEmail(String email);

    protected double getBaseBMR(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5;
    }
}
