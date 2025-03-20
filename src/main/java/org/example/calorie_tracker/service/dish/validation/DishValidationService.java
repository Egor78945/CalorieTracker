package org.example.calorie_tracker.service.dish.validation;

import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class DishValidationService implements ValidationService<Dish> {

    @Override
    public boolean isValid(Dish dish) {
        return dish.getProtein() + dish.getFat() + dish.getCarbohydrate() == 100;
    }
}
