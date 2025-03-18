package org.example.calorie_tracker.service.user.authentication;

import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.UserService;
import org.example.calorie_tracker.service.user.validation.UserValidationService;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends UserService {
    private final ValidationService<User> userValidationService;

    public AuthenticationService(UserRepository userRepository, UserValidationService userValidationService) {
        super(userRepository);
        this.userValidationService = userValidationService;
    }

    @Override
    public long saveUser(User user) {
        if(!userValidationService.isValid(user)){
            throw new RuntimeException("user data is invalid");
        } else if(userRepository.existsUserByEmail(user.getEmail())){
            throw new RuntimeException("email is busy");
        }
        return userRepository.save(user).getId();
    }
}
