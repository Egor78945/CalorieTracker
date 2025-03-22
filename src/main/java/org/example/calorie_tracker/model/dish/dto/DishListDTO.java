package org.example.calorie_tracker.model.dish.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Schema(name = "Dish list DTO", description = "DTO, для регистрации списка блюд")
public record DishListDTO(@NotNull(message = "email is null") @Email String email,
                          @Valid List<DishDTO> dishes) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishListDTO that = (DishListDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(dishes, that.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, dishes);
    }
}
