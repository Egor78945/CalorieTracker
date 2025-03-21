package org.example.calorie_tracker.service.calorie.router;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.enumeration.goal.CalorieGoal;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.calorie.CalorieHoldService;
import org.example.calorie_tracker.service.calorie.CalorieIncreaseService;
import org.example.calorie_tracker.service.calorie.CalorieLossService;
import org.example.calorie_tracker.service.calorie.CalorieService;
import org.example.calorie_tracker.service.dish.DishRecorderService;
import org.example.calorie_tracker.service.user.UserDetailsService;
import org.example.calorie_tracker.service.user.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Tag(name = "Calorie service router", description = "Маршрутизатор, который распределяет запросы пользователей по сервисам, в зависимости от их цели")
public class CalorieServiceRouter {
    private final Map<CalorieGoal, CalorieService> calorieServiceRouter;
    private final UserDetailsService userDetailsService;

    public CalorieServiceRouter(UserRepository userRepository, UserDetailsService userDetailsService, DishRecorderService dishRecorderService) {
        this.userDetailsService = userDetailsService;

        calorieServiceRouter = new HashMap<>();
        calorieServiceRouter.put(CalorieGoal.LOSS, new CalorieLossService(userRepository, dishRecorderService));
        calorieServiceRouter.put(CalorieGoal.INCREASE, new CalorieIncreaseService(userRepository, dishRecorderService));
        calorieServiceRouter.put(CalorieGoal.HOLD, new CalorieHoldService(userRepository, dishRecorderService));
    }

    private CalorieService getCalorieServiceByCalorieGoal(CalorieGoal calorieGoal) {
        return calorieServiceRouter.get(calorieGoal);
    }

    public CalorieService getCalorieServiceByUserEmail(String email){
        return getCalorieServiceByCalorieGoal(userDetailsService.getCalorieGoalByUserEmail(email));
    }
}
