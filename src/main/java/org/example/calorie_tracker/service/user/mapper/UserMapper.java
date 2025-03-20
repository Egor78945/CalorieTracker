package org.example.calorie_tracker.service.user.mapper;

import jakarta.validation.constraints.NotNull;
import org.example.calorie_tracker.model.user.dto.UserDTO;
import org.example.calorie_tracker.model.user.entity.User;

public class UserMapper {
    public static User mapTo(@NotNull UserDTO userDTO){
        return User.builder()
                .setEmail(userDTO.email().toLowerCase())
                .setName(userDTO.name().toLowerCase())
                .setAge(userDTO.age())
                .setWeight(userDTO.weight())
                .setHeight(userDTO.height())
                .setGender(userDTO.gender())
                .setGoalId(GoalMapper.mapTo(userDTO.goal()).getId())
                .build();
    }
}
