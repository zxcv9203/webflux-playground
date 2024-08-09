package org.example.webfluxplayground.controller.v3.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookCustomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String book = (String) target;
        if (book.length() < 5) {
            errors.rejectValue("book", "book.length", "Book name must be at least 5 characters long");
        }
    }
}
