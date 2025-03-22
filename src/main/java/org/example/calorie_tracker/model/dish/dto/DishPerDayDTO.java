package org.example.calorie_tracker.model.dish.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.calorie_tracker.model.dish.entity.Dish;

import java.util.List;
import java.util.Objects;

@Schema(name = "Dish per day DTO", description = "DTO, представляющий сумму калорий всех блюд из списка")
public class DishPerDayDTO {
    private int calorie;
    private List<Dish> dishes;

    public DishPerDayDTO(int calorie, List<Dish> dishes) {
        this.calorie = calorie;
        this.dishes = dishes;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishPerDayDTO that = (DishPerDayDTO) o;
        return calorie == that.calorie && Objects.equals(dishes, that.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calorie, dishes);
    }
}
