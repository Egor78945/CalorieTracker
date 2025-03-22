package org.example.calorie_tracker.service.user;

import org.aspectj.lang.annotation.Before;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.validation.UserValidationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAuthenticationServiceTest {
    @Mock
    private UserValidationService userValidationService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserAuthenticationService userAuthenticationService;

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
    public void save_Successful_ReturnsUser() {
        when(userValidationService.isValid(user)).thenReturn(true);
        when(userRepository.existsUserByEmail(email)).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user, userAuthenticationService.save(user));
    }

    @Test
    public void save_UserIsInvalid_ThrowsRuntimeException() {
        when(userValidationService.isValid(user)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userAuthenticationService.save(user));
    }
    @Test
    public void save_UserIsAlreadyExists_ThrowsRuntimeException() {
        when(userValidationService.isValid(user)).thenReturn(true);
        when(userRepository.existsUserByEmail(email)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userAuthenticationService.save(user));
    }
}
