package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

public class CalorieLossService extends CalorieService {
    public CalorieLossService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        super(userRepository, dishRecorderService);
    }

    @Override
    public int getRecommendedCaloriePerDayForUserByEmail(String email) {
        double baseBMR = getBaseBMR(email);
        return (int)(baseBMR - (baseBMR * 1.55 * 0.17));
    }
}
