package org.example.calorie_tracker.service.dish;

import org.assertj.core.util.Arrays;
import org.example.calorie_tracker.model.dish.dto.DishPerDayDTO;
import org.example.calorie_tracker.model.dish.entity.Dish;
import org.example.calorie_tracker.repository.DishRepository;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.validation.DishValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishRecorderServiceTest {
    @Mock
    private DishValidationService dishValidationService;
    @Mock
    private DishRepository dishRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private DishRecorderService dishRecorderService;

    private static Dish dish;
    private static String email;

    @BeforeAll
    public static void initDish() {
        dish = Dish.builder()
                .setId(1)
                .setCalorie(1)
                .setProtein(1)
                .setFat(1)
                .setCarbohydrate(1)
                .setDate(Date.valueOf(LocalDate.now()))
                .setUserEmail(email)
                .build();
    }

    @BeforeAll
    public static void initEmail() {
        email = "test@gmail.com";
    }

    @Test
    public void save_Successful_ReturnsDish() {
        when(dishValidationService.isValid(dish)).thenReturn(true);
        when(userRepository.existsUserByEmail(email)).thenReturn(true);
        when(dishRepository.save(dish)).thenReturn(dish);

        assertEquals(dish, dishRecorderService.save(dish));
    }

    @Test
    public void save_InvalidDish_ThrowsRuntimeException() {
        when(dishValidationService.isValid(dish)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dishRecorderService.save(dish));
    }

    @Test
    public void save_UserIsNotExists_ThrowsRuntimeException() {
        when(dishValidationService.isValid(dish)).thenReturn(true);
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dishRecorderService.save(dish));
    }

    @Test
    public void saveAll_Successful_ReturnsDishList(){
        List<Dish> list = List.of(dish);
        when(dishValidationService.isValid(dish)).thenReturn(true);
        when(userRepository.existsUserByEmail(email)).thenReturn(true);
        when(dishRepository.save(dish)).thenReturn(dish);
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        assertEquals(list, dishRecorderService.saveAll(list));
    }

    @Test
    public void saveAll_UserNotFind_ReturnsEmptyList(){
        List<Dish> list = List.of(dish);
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertEquals(new ArrayList<Dish>(), dishRecorderService.saveAll(list));
    }

    @Test
    public void getDishesSumCalorie_Successful_Returns1(){
        List<Dish> list = List.of(dish);
        assertEquals(1, dishRecorderService.getDishesSumCalorie(list));
    }

    @Test
    public void getAllDishesGroupingByDayByEmail_Successful_ReturnMap(){
        List<Dish> list = List.of(dish);

        Map<Date, List<Dish>> map = Map.of(dish.getDate(), list);

        when(userRepository.existsUserByEmail(email)).thenReturn(true);
        when(dishRepository.findAllByUserEmail(email)).thenReturn(list);

        assertEquals(map, dishRecorderService.getAllDishesGroupingByDayByEmail(email));
    }

    @Test
    public void getAllDishesGroupingByDayByEmail_UserNotFound_ThrowsRuntimeException(){
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dishRecorderService.getAllDishesGroupingByDayByEmail(email));
    }

    @Test
    public void getDishPerDayByEmail_Successful_ReturnsDishPerDayDTO(){
        List<Dish> list = List.of(dish);
        LocalDate localDate = LocalDate.now();
        DishPerDayDTO dishPerDayDTO = new DishPerDayDTO(1, list);

        when(userRepository.existsUserByEmail(email)).thenReturn(true);
        when(dishRepository.findAllByUserEmailPerLastDay(email, localDate)).thenReturn(Optional.of(list));

        assertEquals(dishPerDayDTO, dishRecorderService.getDishPerDayByEmail(email, localDate));
    }

    @Test
    public void getDishPerDayByEmail_UserNotFound_ThrowsRuntimeException(){
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dishRecorderService.getDishPerDayByEmail(email, LocalDate.now()));
    }

    @Test
    public void getCurrentCalorieSumPerDayByEmail_Successful_ReturnsCalorieSum(){
        List<Dish> list = List.of(dish);
        LocalDate date = LocalDate.now();

        when(userRepository.existsUserByEmail(email)).thenReturn(true);
        when(dishRepository.findAllByUserEmailPerLastDay(email, date)).thenReturn(Optional.of(list));

        assertEquals(1, dishRecorderService.getCurrentCalorieSumPerDayByEmail(email, date));
    }

    @Test
    public void getCurrentCalorieSumPerDayByEmail_UserNotFound_ThrowsRuntimeException(){
        LocalDate date = LocalDate.now();

        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dishRecorderService.getCurrentCalorieSumPerDayByEmail(email, date));
    }
}
