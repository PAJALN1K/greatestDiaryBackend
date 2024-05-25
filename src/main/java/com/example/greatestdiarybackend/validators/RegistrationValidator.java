package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.utils.RegistrationUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RegistrationValidator implements Validator {
    private static final Logger logger = Logger.getLogger(RegistrationValidator.class.getName());

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RegistrationForm form = (RegistrationForm) target;

        if (!RegistrationUtil.validateName(form.getName())) {
            errors.rejectValue(
                    "name",
                    "error.registration.name.valid"
            );
            logger.log(Level.INFO, "Имя {0} не прошло валидацию", form.getName());
        }

        if (!RegistrationUtil.validateEmail(form.getEmail())) {
            errors.rejectValue(
                    "email",
                    "error.registration.name.valid"
            );
            logger.log(Level.INFO, "Почта {0} не прошла валидацию", form.getEmail());
        }

        if (!RegistrationUtil.validatePassword(form.getPassword(), form.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    "error.registration.password.valid"
            );
            logger.log(Level.INFO, "Пароль не прошел валидацию");
        }

        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    "error.registration.password."
            );
            logger.log(Level.INFO, "Пароли не совпадают");
        }
    }
}
