package org.example.calorie_tracker.service.dish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.dish.dto.DishPerDayDTO;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.repository.DishRepository;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.validation.DishValidationService;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Tag(name = "Dish recorder service", description = "Сервис учёта блюд")
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
        return doSave(dish);
    }

    @Override
    public List<Dish> saveAll(List<Dish> dishList) {
        return dishList.stream().filter(d -> userRepository.existsUserByEmail(d.getUserEmail())).map(this::save).toList();
    }

    @Operation(description = "Получить сумму калорий списка блюд")
    public int getDishesSumCalorie(List<Dish> dishes){
        return dishes.stream().map(Dish::getCalorie).reduce(Integer::sum).get();
    }

    @Operation(description = "Получить список блюд, сгрупированных по дням")
    public Map<Date, List<Dish>> getAllDishesGroupingByDayByEmail(String email){
        throwIfUserNotFoundByEmail(email);
        List<Dish> dishes = dishRepository.findAllByUserEmail(email);
        return dishes.stream().collect(Collectors.groupingBy(Dish::getDate));
    }

    @Operation(description = "Получить сумму калорий и список блюд, которые пользователь съел в последний день")
    public DishPerDayDTO getDishPerDayByEmail(String email){
        throwIfUserNotFoundByEmail(email);
        List<Dish> dishes = dishRepository.findAllByUserEmailPerLastDay(email, LocalDate.now());
        int calorieSum = getDishesSumCalorie(dishes);
        return new DishPerDayDTO(calorieSum, dishes);
    }

    @Operation(description = "Получить сумму калорий всех блюд, съеденных за сегодня")
    public int getCurrentCalorieSumPerLastDayByEmail(String email){
        throwIfUserNotFoundByEmail(email);
        List<Dish> dishes = dishRepository.findAllByUserEmailPerLastDay(email, LocalDate.now());
        return getDishesSumCalorie(dishes);
    }

    @Operation(description = "Выкинуть исключение, если пользователь не найден по email")
    private void throwIfUserNotFoundByEmail(String email){
        if(!userRepository.existsUserByEmail(email)){
            throw new RuntimeException("user not found");
        }
    }
}
