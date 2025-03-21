package org.example.calorie_tracker.service.user.validation.validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.validation.annotation.Letters;

@Tag(name = "Letters validator", description = "Валидатор, проверяющий наличие в строке символов кроме букв")
public class LettersValidator implements ConstraintValidator<Letters, String> {
    @Override
    @Operation(description = "Проверяет, содержит ли строка только буквы")
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for(char i: s.toCharArray()){
            if(!((i >= 97 && i <= 122) || (i >= 65 && i <= 90))){
                return false;
            }
        }
        return true;
    }
}
