package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.forms.TargetForm;
import com.example.greatestdiarybackend.utils.valid.TargetUtil;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TargetValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return TargetForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        TargetForm form = (TargetForm) target;

        if (!TargetUtil.validateTitile(form.getTitle())) {
            errors.rejectValue(
                    "title",
                    "error.target.title.valid"
            );
        }

        if (!TargetUtil.validatePriority(form.getPriority())) {
            errors.rejectValue(
                    "title",
                    "error.target.priority.valid"
            );
        }

        if (!TargetUtil.validateDescription(form.getDescription())) {
            errors.rejectValue(
                    "title",
                    "error.target.description.valid"
            );
        }
    }
}
