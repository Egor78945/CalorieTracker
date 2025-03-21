package org.example.calorie_tracker.service.validation;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Validation service", description = "Сервис, проводящий валидацию")
public interface ValidationService<T> {
    boolean isValid(T object);
}
