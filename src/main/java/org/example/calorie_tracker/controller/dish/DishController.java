package org.example.calorie_tracker.controller.dish;

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
public class DishController {
    private final DishRecorderService dishService;

    public DishController(DishRecorderService dishRecorderService) {
        this.dishService = dishRecorderService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDish(@Valid @RequestBody DishListDTO dishListDTO) {
        return ResponseEntity.ok(Integer.toString(dishService.getDishesSumCalorie(dishService.saveAll(DishMapper.mapTo(dishListDTO)))));
    }

    @GetMapping("/all/{email}")
    public ResponseEntity<Map<Date, List<Dish>>> getAllDishesByDay(@PathVariable("email") String email) {
        return ResponseEntity.ok(dishService.getAllDishesGroupingByDayByEmail(email));
    }

    @GetMapping("/last/{email}")
    public ResponseEntity<DishPerDayDTO> getAllDishesCaloriePerLastDay(@PathVariable("email") String email){
        return ResponseEntity.ok(dishService.getDishPerDayByEmail(email));
    }
}