package org.example.calorie_tracker.controller.authentication;

import jakarta.validation.Valid;
import org.example.calorie_tracker.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import org.example.calorie_tracker.model.user.dto.UserRegistrationDTO;
import org.example.calorie_tracker.service.user.UserService;
import org.example.calorie_tracker.service.user.UserAuthenticationService;
import org.example.calorie_tracker.service.user.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserAuthenticationService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return ResponseEntity.ok(Long.toString(userService.save(UserMapper.mapTo(userRegistrationDTO)).getId()));
    }
}
