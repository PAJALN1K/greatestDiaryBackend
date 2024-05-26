package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.forms.PasswordSettingsForm;
import com.example.greatestdiarybackend.utils.PasswordSettingsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordSettingsValidator implements Validator {
    private final PasswordSettingsUtil passwordSettingsUtil;

    @Autowired
    public PasswordSettingsValidator(PasswordSettingsUtil passwordSettingsUtil) {
        this.passwordSettingsUtil = passwordSettingsUtil;
    }

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PasswordSettingsForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PasswordSettingsForm form = (PasswordSettingsForm) target;

        if (!passwordSettingsUtil.validateOldPassword(form.getOldPassword(), form.getNewPassword())) {
            errors.rejectValue(
                    "oldPassword",
                    "error.password-settings.old-password.valid"
            );

            return;
        }

        if (!passwordSettingsUtil.validateNewPassword(form.getNewPassword())) {
            errors.rejectValue(
                    "newPassword",
                    "error.password-settings.new-password.valid"
            );

            return;
        }

        if (!passwordSettingsUtil.validateConfirmPassword(form.getConfirmPassword(), form.getNewPassword())) {
            errors.rejectValue(
                    "confirmPassword",
                    "error.password-settings.confirm-password.valid"
            );
        }
    }
}
