package org.example.calorie_tracker.controller.details;

import org.example.calorie_tracker.controller.details.advice.handler.DetailsControllerExceptionHandler;
import org.example.calorie_tracker.service.user.UserDetailsService;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
@DetailsControllerExceptionHandler
public class DetailsController {
    private final UserDetailsService userDetailsService;

    public DetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/goal")
    public ResponseEntity<String> setUserGoal(@RequestParam("email") String email, @RequestParam("goal") String goal) {
        userDetailsService.setGoalByEmail(email, GoalMapper.mapTo(goal));
        return ResponseEntity.ok(goal);
    }
}
