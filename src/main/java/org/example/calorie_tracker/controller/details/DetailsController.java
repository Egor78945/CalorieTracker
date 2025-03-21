package org.example.calorie_tracker.controller.details;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.service.user.UserDetailsService;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
@GlobalControllerExceptionHandler
@Tag(name = "User details controller", description = "Контроллер, обрабатывающий запросы, касающиеся информации и деталей пользовательской информации")
public class DetailsController {
    private final UserDetailsService userDetailsService;

    public DetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/goal")
    @Operation(description = "Установить цель пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "Цель пользователя установлена", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время установки цели для пользователя")}
    )
    public ResponseEntity<String> setUserGoal(@RequestParam("email") String email, @RequestParam("goal") String goal) {
        userDetailsService.setGoalByEmail(email, GoalMapper.mapTo(goal));
        return ResponseEntity.ok(goal);
    }
}
