package com.example.greatestdiarybackend.services.note;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.NoteForm;
import com.example.greatestdiarybackend.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NoteServiceImpl implements NoteService {
    private static final Logger logger = Logger.getLogger(NoteServiceImpl.class.getName());

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findNoteByUserId(Long id) {
        return noteRepository.findNotesByUserId(id);
    }

    @Override
    public Note findNoteByUuid(String uuid) {
        return noteRepository.findNoteByUuid(uuid);
    }

    @Override
    public Note createNote(NoteForm form, User authenticatedUser) {
        Note createdNote = new Note()
                .setUuid(generateUuid())
                .setDescription(form.getDescription())
                .setDate(form.getDate())
                .setEmojiName(form.getEmojiName())
                .setLocation(form.getLocation())
                .setUser(authenticatedUser);
        logger.log(Level.INFO, "Заметка успешно создана");

        return createdNote;
    }

    @Override
    public void save(Note note) {
        logger.log(Level.INFO, "Заметка успешно сохранена");
        noteRepository.save(note);
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
