package org.example.calorie_tracker.service.dish;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.repository.DishRepository;

import java.util.List;

@Tag(name = "Dish service", description = "Сервис, проводящий манипуляцию блюдами")
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
