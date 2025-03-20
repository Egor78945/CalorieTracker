package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

public class CalorieIncreaseService extends CalorieService {
    public CalorieIncreaseService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        super(userRepository, dishRecorderService);
    }

    @Override
    public int getRecommendedCaloriePerDayForUserByEmail(String email) {
        double baseBMR = getBaseBMR(email);
        return (int)(baseBMR + (1.55 * baseBMR * 0.15));
    }
}
