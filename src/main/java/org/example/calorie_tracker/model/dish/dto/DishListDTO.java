package org.example.calorie_tracker.model.dish.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(name = "Dish list DTO", description = "DTO, для регистрации списка блюд")
public record DishListDTO(@NotNull(message = "email is null") @Email String email,
                          @Valid List<DishDTO> dishes) {

}
