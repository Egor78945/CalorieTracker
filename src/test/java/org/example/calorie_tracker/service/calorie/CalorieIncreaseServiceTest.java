package org.example.calorie_tracker.service.calorie;

import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.dish.DishRecorderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalorieIncreaseServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private DishRecorderService dishRecorderService;

    @InjectMocks
    private CalorieIncreaseService calorieIncreaseService;

    private static User user;
    private static String email;

    @BeforeAll
    public static void initUser() {
        user = new User();
        user.setWeight(1);
        user.setHeight(1);
        user.setAge(1);
    }

    @BeforeAll
    public static void initEmail() {
        email = "test@gmail.com";
    }

    @Test
    public void getRecommendedCaloriePerDayForUserByEmail_Successful_Returns20(){
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));

        assertEquals(20, calorieIncreaseService.getRecommendedCaloriePerDayForUserByEmail(email));
    }

    @Test
    public void getRecommendedCaloriePerDayForUserByEmail_UserNotFound_ThrowsRuntimeException(){
        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calorieIncreaseService.getRecommendedCaloriePerDayForUserByEmail(email));
    }
}
