package org.example.calorie_tracker.service.dish;

import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.repository.DishRepository;

import java.util.List;

public abstract class DishService {
    protected final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public abstract Dish save(Dish dish);

    public abstract List<Dish> saveAll(List<Dish> dishList);

    protected Dish doSave(Dish dish){
        return dishRepository.save(dish);
    }
}
