package org.example.calorie_tracker.service.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.enumeration.goal.CalorieGoal;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.springframework.stereotype.Service;

@Service
@Tag(name = "User details service", description = "Сервис, манипулирующий информацией о пользователях")
public class UserDetailsService extends UserService {
    public UserDetailsService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User save(User user) {
        return doSave(user);
    }

    @Operation(description = "Установить цель для пользователя по email")
    public void setGoalByEmail(String email, CalorieGoal goal) {
        if (!userRepository.existsUserByEmail(email)) {
            throw new RuntimeException("user not found");
        } else if (goal == null) {
            throw new RuntimeException("goal is unknown");
        }
        userRepository.changeUserGoalIdByUserIdAndGoalId(email, goal.getId());
    }

    @Operation(description = "Получить цель пользователя по email")
    public CalorieGoal getCalorieGoalByUserEmail(String email) {
        return GoalMapper.mapTo(userRepository.findUserGoalIdByUserEmail(email).orElseThrow(() -> new RuntimeException("user not found")));
    }
}
