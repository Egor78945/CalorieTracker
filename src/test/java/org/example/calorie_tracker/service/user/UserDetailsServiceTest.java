package org.example.calorie_tracker.service.user;

import org.example.calorie_tracker.enumeration.goal.CalorieGoal;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
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
public class UserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDetailsService userDetailsService;

    private static User user;
    private static String email;

    @BeforeAll
    public static void initUser() {
        user = User.builder()
                .setId(1)
                .setEmail(email)
                .setName("name")
                .setGender('m')
                .setHeight(190)
                .setWeight(70)
                .setAge(19)
                .setGoalId(1)
                .build();
    }

    @BeforeAll
    public static void initEmail() {
        email = "test@gmail.com";
    }

    @Test
    public void save_Success_ReturnsUser() {
        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userDetailsService.save(user));
    }

    @Test
    public void setGoal_Success_MethodEnds() {
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        assertDoesNotThrow(() -> userDetailsService.setGoalByEmail(email, CalorieGoal.LOSS));
    }

    @Test
    public void setGoal_UserNotFound_ThrowsRuntimeException() {
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userDetailsService.setGoalByEmail(email, CalorieGoal.LOSS));
    }

    @Test
    public void setGoal_GoalIsNull_ThrowsRuntimeException() {
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userDetailsService.setGoalByEmail(email, null));
    }

    @Test
    public void getCalorieGoalByUserEmail_Successful_ReturnsLossGoal(){
        when(userRepository.findUserGoalIdByUserEmail(email)).thenReturn(Optional.of(1L));

        assertEquals(CalorieGoal.LOSS, userDetailsService.getCalorieGoalByUserEmail(email));
    }

    @Test
    public void getCalorieGoalByUserEmail_UserNotFound_ThrowsRuntimeException(){
        when(userRepository.findUserGoalIdByUserEmail(email)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userDetailsService.getCalorieGoalByUserEmail(email));
    }
}