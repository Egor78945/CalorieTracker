package org.example.calorie_tracker.service.validation;

public interface ValidationService<T> {
    boolean isValid(T object);
}
