package org.example.calorie_tracker.controller.calorie;

import org.example.calorie_tracker.service.calorie.router.CalorieServiceRouter;
import org.example.calorie_tracker.service.user.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calorie")
public class CalorieController {
    private final CalorieServiceRouter calorieServiceRouter;
    private final UserDetailsService userDetailsService;

    public CalorieController(CalorieServiceRouter calorieServiceRouter, UserDetailsService userDetailsService) {
        this.calorieServiceRouter = calorieServiceRouter;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/recommended")
    public ResponseEntity<String> getRecommendedCaloriePerDay(@RequestParam("email") String email){
        return ResponseEntity.ok(Integer.toString(calorieServiceRouter.getCalorieServiceByCalorieGoal(userDetailsService.getCalorieGoalByUserEmail(email)).getCaloriePerDayForUserByEmail(email)));
    }
}
