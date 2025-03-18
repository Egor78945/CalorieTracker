package org.example.calorie_tracker.service.user.mapper;

import jakarta.validation.constraints.NotNull;
import org.example.calorie_tracker.model.user.dto.UserRegistrationDTO;
import org.example.calorie_tracker.model.user.entity.User;

public class UserMapper {
    public static User mapTo(@NotNull UserRegistrationDTO userRegistrationDTO){
        return User.builder()
                .setEmail(userRegistrationDTO.email().toLowerCase())
                .setName(userRegistrationDTO.name().toLowerCase())
                .setAge(userRegistrationDTO.age())
                .setWeight(userRegistrationDTO.weight())
                .setHeight(userRegistrationDTO.height())
                .setGender(userRegistrationDTO.gender())
                .build();
    }
}
