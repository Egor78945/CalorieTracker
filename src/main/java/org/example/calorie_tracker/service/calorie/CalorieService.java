package org.example.calorie_tracker.service.calorie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;

@Tag(name = "Calorie service", description = "Сервис учёта и манипуляции информации о калориях")
public abstract class CalorieService {
    protected final UserRepository userRepository;
    protected final DishRecorderService dishRecorderService;

    public CalorieService(UserRepository userRepository, DishRecorderService dishRecorderService) {
        this.userRepository = userRepository;
        this.dishRecorderService = dishRecorderService;
    }

    @Operation(description = "Получить рекомендованное количество калорий в день для пользователя по email")
    public abstract int getRecommendedCaloriePerDayForUserByEmail(String email);

    @Operation(description = "Получить базовую информацию о метаболизме пользователя")
    protected double getBaseBMR(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user not found"));

        return (10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5;
    }

    @Operation(description = "Проверить, находится ли количество калорий, которое пользователь употребил сегодня в рекомендованных пределах")
    public boolean calorieIsInRecommendedLimitByEmail(String email){
        int currentCalorieSum = dishRecorderService.getCurrentCalorieSumPerLastDayByEmail(email);
        int recommendedCalorieSum = getRecommendedCaloriePerDayForUserByEmail(email);
        return (recommendedCalorieSum - currentCalorieSum >= 0 && recommendedCalorieSum - currentCalorieSum <= 100) || (currentCalorieSum - recommendedCalorieSum >= 0 && currentCalorieSum - recommendedCalorieSum <= 100);
    }
}
