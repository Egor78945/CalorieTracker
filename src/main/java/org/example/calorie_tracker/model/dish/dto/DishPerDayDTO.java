package org.example.calorie_tracker.model.dish.dto;

import org.example.calorie_tracker.model.dish.entity.Dish;

import java.util.List;

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
}
