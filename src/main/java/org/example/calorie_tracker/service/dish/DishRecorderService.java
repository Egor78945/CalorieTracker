package org.example.calorie_tracker.service.dish;

import org.example.calorie_tracker.model.dish.dto.DishPerDayDTO;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.repository.DishRepository;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.calorie.router.CalorieServiceRouter;
import org.example.calorie_tracker.service.dish.validation.DishValidationService;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DishRecorderService extends DishService {
    private final ValidationService<Dish> validationService;
    private final UserRepository userRepository;

    public DishRecorderService(DishRepository dishRepository, DishValidationService dishValidationService, UserRepository userRepository) {
        super(dishRepository);
        this.validationService = dishValidationService;
        this.userRepository = userRepository;
    }

    @Override
    public Dish save(Dish dish) {
        if(!validationService.isValid(dish)){
            throw new RuntimeException("sum of protein, fat and carbohydrate must be equal to 100");
        } else if(!userRepository.existsUserByEmail(dish.getUserEmail())){
            throw new RuntimeException("user not found");
        }
        return dishRepository.save(dish);
    }

    @Override
    public List<Dish> saveAll(List<Dish> dishList) {
        return dishList.stream().map(this::save).toList();
    }

    public int getDishesSumCalorie(List<Dish> dishes){
        return dishes.stream().map(Dish::getCalorie).reduce(Integer::sum).get();
    }

    public Map<Date, List<Dish>> getAllDishesGroupingByDayByEmail(String email){
        List<Dish> dishes = dishRepository.findAllByUserEmail(email);
        return dishes.stream().collect(Collectors.groupingBy(Dish::getDate));
    }

    public DishPerDayDTO getDishPerDayByEmail(String email){
        List<Dish> dishes = dishRepository.findAllByUserEmailPerLastDay(email);
        int calorieSum = getDishesSumCalorie(dishes);
        return new DishPerDayDTO(calorieSum, dishes);
    }

    public int getCurrentCalorieSumPerLastDayByEmail(String email){
        List<Dish> dishes = dishRepository.findAllByUserEmailPerLastDay(email);
        return getDishesSumCalorie(dishes);
    }

}
