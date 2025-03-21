package org.example.calorie_tracker.controller.calorie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.service.calorie.router.CalorieServiceRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calorie")
@GlobalControllerExceptionHandler
@Tag(name = "Calorie controller", description = "Контроллер, обрабатывающий запросы, касающиеся информации о калориях")
public class CalorieController {
    private final CalorieServiceRouter calorieServiceRouter;

    public CalorieController(CalorieServiceRouter calorieServiceRouter) {
        this.calorieServiceRouter = calorieServiceRouter;
    }

    @GetMapping("/recommended/{email}")
    @Operation(description = "Получить рекомендуемое количество калорий в день для человка", responses = {
            @ApiResponse(responseCode = "200", description = "Рекомендованное количество калорий получено", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время получения количества калорий")}
    )
    public ResponseEntity<String> getRecommendedCaloriePerDay(@PathVariable("email") @Parameter(name = "email", description = "email пользователя") String email) {
        return ResponseEntity.ok(Integer.toString(calorieServiceRouter.getCalorieServiceByUserEmail(email).getRecommendedCaloriePerDayForUserByEmail(email)));
    }

    @GetMapping("/check/{email}")
    @Operation(description = "Проверить, находится ли текущее количество калорий пользователя в рекомендованных границах", responses = {
            @ApiResponse(responseCode = "200", description = "Получен boolean, сигнализирующий о том, находятся ли калории пользователя в рекомендованных границах или нет", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Boolean.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время получения информации о состоянии калорий")}
    )
    public ResponseEntity<Boolean> caloriesIsInLimits(@PathVariable("email") @Parameter(name = "email", description = "email пользователя") String email) {
        return ResponseEntity.ok(calorieServiceRouter.getCalorieServiceByUserEmail(email).calorieIsInRecommendedLimitByEmail(email));
    }
}
