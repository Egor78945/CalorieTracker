package org.example.calorie_tracker.service.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.calorie_tracker.model.user.entity.User;
import org.example.calorie_tracker.repository.UserRepository;
import org.example.calorie_tracker.service.user.validation.UserValidationService;
import org.example.calorie_tracker.service.validation.ValidationService;
import org.springframework.stereotype.Service;

@Service
@Tag(name = "User authentication service", description = "Сервис, проводящий аутентификацию пользователей")
public class UserAuthenticationService extends UserService {
    private final ValidationService<User> userValidationService;

    public UserAuthenticationService(UserRepository userRepository, UserValidationService userValidationService) {
        super(userRepository);
        this.userValidationService = userValidationService;
    }

    @Override
    public User save(User user) {
        if(!userValidationService.isValid(user)){
            throw new RuntimeException("user data is invalid");
        } else if(userRepository.existsUserByEmail(user.getEmail())){
            throw new RuntimeException("email is busy");
        }
        return doSave(user);
    }
}
