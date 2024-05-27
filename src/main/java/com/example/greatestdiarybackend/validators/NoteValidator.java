package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.forms.NoteForm;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NoteValidator implements Validator {
    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return NoteForm.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        NoteForm form = (NoteForm) target;
    }
}
