package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.utils.valid.RegistrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    private final RegistrationUtil registrationUtil;

    @Autowired
    public RegistrationValidator(RegistrationUtil registrationUtil) {
        this.registrationUtil = registrationUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return RegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        RegistrationForm form = (RegistrationForm) target;

        if (!registrationUtil.validateName(form.getName())) {
            errors.rejectValue(
                    "name",
                    "error.registration.name.valid"
            );
        }

        if (!registrationUtil.validateEmail(form.getEmail())) {
            errors.rejectValue(
                    "email",
                    "error.registration.email.valid"
            );
        }

        if (!registrationUtil.validatePassword(form.getPassword(), form.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    "error.registration.password.valid"
            );
        }
    }
}
