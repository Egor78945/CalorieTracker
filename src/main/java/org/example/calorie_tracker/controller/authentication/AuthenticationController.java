package org.example.calorie_tracker.controller.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.calorie_tracker.controller.advice.handler.GlobalControllerExceptionHandler;
import org.example.calorie_tracker.model.user.dto.UserDTO;
import org.example.calorie_tracker.model.user.entity.User;
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
@Tag(name = "Authentication controller", description = "Контроллер, проводящий аутентификацию пользователя")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserAuthenticationService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Operation(description = "Регистрация нового пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегестрирован, так же получена модель зарегестрированного пользователя", content = {@Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = User.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Произошла ошибка во время регистрации пользователя")
    })
    public ResponseEntity<User> registration(@Valid @RequestBody @Parameter(name = "User DTO", description = "DTO с регистрационной информацией о пользователе") UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(UserMapper.mapTo(userDTO)));
    }
}
