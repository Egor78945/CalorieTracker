package org.example.calorie_tracker.controller.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calorie_tracker.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import org.example.calorie_tracker.model.user.dto.UserRegistrationDTO;
import org.example.calorie_tracker.service.user.UserService;
import org.example.calorie_tracker.service.user.authentication.AuthenticationService;
import org.example.calorie_tracker.service.user.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(AuthenticationService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Long> registration(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(userService.saveUser(UserMapper.mapTo(userRegistrationDTO)));
    }
}
