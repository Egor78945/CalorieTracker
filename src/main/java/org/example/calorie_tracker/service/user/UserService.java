package org.example.calorie_tracker.service.user;

import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;

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
