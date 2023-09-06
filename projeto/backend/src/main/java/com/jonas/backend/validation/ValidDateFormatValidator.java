package com.jonas.backend.validation;

import com.jonas.backend.services.exceptions.DateParsingException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidDateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dateStr, ConstraintValidatorContext context) {
        if (dateStr == null) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            throw new DateParsingException("Failed to parse the date: "
                    + "Invalid date format");
        }

    }

}
