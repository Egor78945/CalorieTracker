package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

public abstract class CalorieService {
    protected final UserRepository userRepository;
    protected final DishRecorderService dishRecorderService;
    public CalorieService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        this.userRepository = userRepository;
        this.dishRecorderService = dishRecorderService;
    }

    public abstract int getRecommendedCaloriePerDayForUserByEmail(String email);

    protected double getBaseBMR(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5;
    }

    public boolean calorieIsInRecommendedLimitByEmail(String email){
        return dishRecorderService.getCurrentCalorieSumPerLastDayByEmail(email) < getRecommendedCaloriePerDayForUserByEmail(email);
    }
}
