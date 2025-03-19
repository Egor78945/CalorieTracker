package org.example.calorie_tracker.service.calorie.router;

import org.example.calorie_tracker.enumeration.goal.CalorieGoal;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.calorie.CalorieHoldService;
import org.example.calorie_tracker.service.calorie.CalorieIncreaseService;
import org.example.calorie_tracker.service.calorie.CalorieLossService;
import org.example.calorie_tracker.service.calorie.CalorieService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CalorieServiceRouter {
    private final Map<CalorieGoal, CalorieService> calorieServiceRouter;

    public CalorieServiceRouter(UserRepository userRepository){
        calorieServiceRouter = new HashMap<>();
        calorieServiceRouter.put(CalorieGoal.LOSS, new CalorieLossService(userRepository));
        calorieServiceRouter.put(CalorieGoal.INCREASE, new CalorieIncreaseService(userRepository));
        calorieServiceRouter.put(CalorieGoal.HOLD, new CalorieHoldService(userRepository));
    }

    public CalorieService getCalorieServiceByCalorieGoal(CalorieGoal calorieGoal){
        return calorieServiceRouter.get(calorieGoal);
    }
}
