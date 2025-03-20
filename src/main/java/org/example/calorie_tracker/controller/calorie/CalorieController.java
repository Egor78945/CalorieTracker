package org.example.calorie_tracker.controller.calorie;

import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.service.calorie.router.CalorieServiceRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calorie")
@GlobalControllerExceptionHandler
public class CalorieController {
    private final CalorieServiceRouter calorieServiceRouter;

    public CalorieController(CalorieServiceRouter calorieServiceRouter) {
        this.calorieServiceRouter = calorieServiceRouter;
    }

    @GetMapping("/recommended/{email}")
    public ResponseEntity<String> getRecommendedCaloriePerDay(@PathVariable("email") String email){
        return ResponseEntity.ok(Integer.toString(calorieServiceRouter.getCalorieServiceByUserEmail(email).getRecommendedCaloriePerDayForUserByEmail(email)));
    }

    @GetMapping("/check/{email}")
    public ResponseEntity<Boolean> caloriesIsInLimits(@PathVariable("email") String email){
        return ResponseEntity.ok(calorieServiceRouter.getCalorieServiceByUserEmail(email).calorieIsInRecommendedLimitByEmail(email));
    }
}
