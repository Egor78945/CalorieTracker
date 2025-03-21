package org.example.calorie_tracker.service.calorie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

@Tag(name = "Calorie Hold Service", description = "Реализация CalorieService для пользователей с целью уменьшения текущего веса")
public class CalorieLossService extends CalorieService {
    public CalorieLossService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        super(userRepository, dishRecorderService);
    }

    @Override
    @Operation(description = "Получить рекомендуемое количество калорий в день для пользователя с целью уменьшения веса")
    public int getRecommendedCaloriePerDayForUserByEmail(String email) {
        double baseBMR = getBaseBMR(email);
        return (int)(baseBMR - (baseBMR * 1.55 * 0.17));
    }
}
