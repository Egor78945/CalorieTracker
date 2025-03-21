package org.example.calorie_tracker.model.dish.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.calorie_tracker.service.user.validation.annotation.Letters;

@Schema(name = "Dish DTO", description = "DTO для регистрации блюда")
public record DishDTO(
        @NotNull(message = "name is null") @NotBlank(message = "name is blank") @Letters(message = "name not contains only letters") String name,
        @Min(value = 0, message = "calorie is less than 0") @Max(value = 3000, message = "calorie is to high") int calorie,
        @Min(value = 0, message = "protein percentage is less than 0") @Max(value = 100, message = "protein percentage is more than 100") int protein,
        @Min(value = 0, message = "fat is less than 0") @Max(value = 100, message = "fat percentage is more than 100") int fat,
        @Min(value = 0, message = "carbohydrate is less than 0") @Max(value = 100, message = "carbohydrate percentage is more than 100") int carbohydrate) {
}
