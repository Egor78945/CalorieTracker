package org.example.calorie_tracker.model.dish.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DishListDTO(@NotNull(message = "email is null") @Email String email,
                          @Valid List<DishDTO> dishes) {

}
