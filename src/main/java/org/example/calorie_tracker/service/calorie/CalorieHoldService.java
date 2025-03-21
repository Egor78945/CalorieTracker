package org.example.calorie_tracker.service.calorie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

@Tag(name = "Calorie Hold Service", description = "Реализация CalorieService для пользователей с целью удержания текущего веса")
public class CalorieHoldService extends CalorieService {
    public CalorieHoldService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        super(userRepository, dishRecorderService);
    }

    @Override
    @Operation(description = "Получить рекомендуемое количество калорий в день для пользователя с целью удержания веса")
    public int getRecommendedCaloriePerDayForUserByEmail(String email) {
        return (int) getBaseBMR(email);
    }
}
