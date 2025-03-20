package org.example.calorie_tracker.model.dish.dto;

import org.example.calorie_tracker.model.dish.entity.Dish;

import java.sql.Date;
import java.util.List;

public class DishByDayDTO {
    private Date date;
    private List<Dish> dishes;

    public DishByDayDTO(Date date, List<Dish> dishes) {
        this.date = date;
        this.dishes = dishes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
