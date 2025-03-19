package org.example.calorie_tracker.service.user;

import org.example.calorie_tracker.enumeration.goal.CalorieGoal;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.mapper.GoalMapper;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService extends UserService {
    public UserDetailsService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User save(User user) {
        return doSave(user);
    }

    public void setGoalByEmail(String email, CalorieGoal goal) {
        if (!userRepository.existsUserByEmail(email)) {
            throw new RuntimeException("user not found");
        } else if (goal == null) {
            throw new RuntimeException("goal is unknown");
        }
        userRepository.changeUserGoalIdByUserIdAndGoalId(email, goal.getId());
    }

    public CalorieGoal getCalorieGoalByUserEmail(String email) {
        return GoalMapper.mapTo(userRepository.findUserGoalIdByUserEmail(email).orElseThrow(() -> new RuntimeException("user not found")));
    }
}
