package org.example.calorie_tracker.controller.dish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.model.dish.dto.DishPerDayDTO;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.service.dish.DishRecorderService;
import org.example.calorie_tracker.model.dish.dto.DishListDTO;
import org.example.calorie_tracker.service.dish.mapper.DishMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dish")
@GlobalControllerExceptionHandler
@Tag(name = "Dish controller", description = "Контроллер обрабатывающй запросы, касающиеся информации или действия блюдами")
public class DishController {
    private final DishRecorderService dishService;

    public DishController(DishRecorderService dishRecorderService) {
        this.dishService = dishRecorderService;
    }

    @PostMapping("/add")
    @Operation(description = "Добавить список блюд, которые съел пользователь", responses = {
            @ApiResponse(responseCode = "200", description = "Получена сумма калорий всех съеденых блюд", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Integer.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время добавления блюд")}
    )
    public ResponseEntity<Integer> addDish(@Valid @RequestBody @Parameter(name = "Dish list DTO", description = "Список блюд") DishListDTO dishListDTO) {
        return ResponseEntity.ok(dishService.getDishesSumCalorie(dishService.saveAll(DishMapper.mapTo(dishListDTO))));
    }

    @GetMapping("/all/{email}")
    @Operation(description = "Получить список всех блюд, которые когда либо ел пользователь, сгрупированный по дням", responses = {
            @ApiResponse(responseCode = "200", description = "Получен список съеденых пользователем блюд", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Map.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время получения списка съеденых блюд")}
    )
    public ResponseEntity<Map<Date, List<Dish>>> getAllDishesByDay(@PathVariable("email") @Parameter(name = "email", description = "email пользователя") String email) {
        return ResponseEntity.ok(dishService.getAllDishesGroupingByDayByEmail(email));
    }

    @GetMapping("/today/{email}")
    @Operation(description = "Получить сумму калорий, а так же все съеденные за день блюда", responses = {
            @ApiResponse(responseCode = "200", description = "Сумма калорий и список съеденых блюд получен", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = DishPerDayDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время получения списка съеденых блюд")}
    )
    public ResponseEntity<DishPerDayDTO> getDishesListPerDay(@PathVariable("email") @Parameter(name = "email", description = "email пользователя") String email){
        return ResponseEntity.ok(dishService.getDishPerDayByEmail(email));
    }
}