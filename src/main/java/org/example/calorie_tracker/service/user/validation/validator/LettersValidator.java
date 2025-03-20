package org.example.calorie_tracker.service.user.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.calorie_tracker.service.user.validation.annotation.Letters;

public class LettersValidator implements ConstraintValidator<Letters, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for(char i: s.toCharArray()){
            if(!((i >= 97 && i <= 122) || (i >= 65 && i <= 90) || i == 32)){
                return false;
            }
        }
        return true;
    }
}
