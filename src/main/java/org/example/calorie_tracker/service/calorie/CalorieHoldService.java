package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

public class CalorieHoldService extends CalorieService {
    public CalorieHoldService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        super(userRepository, dishRecorderService);
    }

    @Override
    public int getRecommendedCaloriePerDayForUserByEmail(String email) {
        return (int) getBaseBMR(email);
    }
}
