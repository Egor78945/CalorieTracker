package org.example.calorie_tracker.model.user.dto;

import jakarta.validation.constraints.*;
import org.example.calorie_tracker.service.user.validation.annotation.Gender;
import org.example.calorie_tracker.service.user.validation.annotation.Goal;

public record UserRegistrationDTO(@NotNull(message = "email is null") @Email(message = "email is invalid") String email,
                                  @NotNull(message = "name is null") @NotBlank(message = "name is blank") @Size(min = 2, max = 20, message = "name is too long or too short") String name,
                                  @Min(value = 14, message = "age is too low") @Max(value = 150, message = "age is to high") int age,
                                  @Min(value = 20, message = "weight is too low") @Max(value = 250, message = "weight is to high") int weight,
                                  @Min(value = 130, message = "height is too low") @Max(value = 250, message = "height is to high") int height,
                                  @Gender char gender,
                                  @NotNull(message = "goal is null") @NotBlank(message = "goal is blank") @Goal String goal) {
}
