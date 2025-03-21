package org.example.calorie_tracker.service.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;

@Tag(name = "User service", description = "Сервис, манипулирующий пользователями")
public abstract class UserService {
    protected final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public abstract User save(User user);

    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    protected User doSave(User user) {
        return userRepository.save(user);
    }
}
