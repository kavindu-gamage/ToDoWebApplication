package com.example.todo.validation;

import org.springframework.stereotype.Component;

@Component
public class IdValidator {
    public void validateId(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }

            if (id <= 0) {
                throw new IllegalArgumentException("ID must be a positive value");
            }

        } catch (IllegalArgumentException e) {
            // Handle the specific validation exception
            throw new IllegalArgumentException("ID validation failed: " + e.getMessage());
        } catch (Exception e) {
            // Handle other types of exceptions
            throw new RuntimeException("Unexpected error during ID validation", e);
        }
    }
}
