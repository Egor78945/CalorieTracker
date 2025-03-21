package org.example.calorie_tracker.service.dish.mapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.dish.dto.DishListDTO;
import org.example.calorie_tracker.model.dish.entity.Dish;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Dish mapper", description = "Преобразует блюда из одной формы в другую")
public class DishMapper {
    @Operation(description = "Преобразовывает DishListDTO в список блюд")
    public static List<Dish> mapTo(DishListDTO dishListDTO) {
        return dishListDTO
                .dishes()
                .stream()
                .map(dishDTO -> Dish.builder()
                        .setName(dishDTO.name())
                        .setCalorie(dishDTO.calorie())
                        .setProtein(dishDTO.protein())
                        .setFat(dishDTO.fat())
                        .setCarbohydrate(dishDTO.carbohydrate())
                        .setUserEmail(dishListDTO.email())
                        .setDate(Date.valueOf(LocalDate.now()))
                        .build())
                .toList();
    }
}
