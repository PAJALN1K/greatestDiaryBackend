package com.example.greatestdiarybackend.services.note;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.NoteForm;

import java.util.List;

public interface NoteService {
    List<Note> findNoteByUserId(Long id);
    Note findNoteByUuid(String uuid);
    Note createNote(NoteForm form, User authenticatedUser);
    void save(Note note);
    void deleteByUuid(String uuid);
    Note changeNote(NoteForm form, Note note);
}
