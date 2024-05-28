package com.example.greatestdiarybackend.validators;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.forms.NoteForm;
import com.example.greatestdiarybackend.utils.valid.NoteUtil;
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

        if (!NoteUtil.validateDescription(form.getDescription())) {
            errors.rejectValue(
                    "description",
                    "error.note.description.valid"
            );
        }

        if (!NoteUtil.validateLocation(form.getLocation())) {
            errors.rejectValue(
                    "location",
                    "error.note.location.valid"
            );
        }

        if (!NoteUtil.validateDate(form.getDate())) {
            errors.rejectValue(
                    "date",
                    "error.note.date.valid"
            );
        }

        if (!NoteUtil.validateEmojiName(form.getEmojiName())) {
            errors.rejectValue(
                    "emojiName",
                    "error.note.emojiName.valid"
            );
        }
    }
}
