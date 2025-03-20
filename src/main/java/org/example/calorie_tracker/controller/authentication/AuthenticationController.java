package org.example.calorie_tracker.controller.authentication;

import jakarta.validation.Valid;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.model.user.dto.UserDTO;
import org.example.calorie_tracker.service.user.UserAuthenticationService;
import org.example.calorie_tracker.service.user.UserService;
import org.example.calorie_tracker.service.user.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
@GlobalControllerExceptionHandler
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserAuthenticationService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(Long.toString(userService.save(UserMapper.mapTo(userDTO)).getId()));
    }
}
