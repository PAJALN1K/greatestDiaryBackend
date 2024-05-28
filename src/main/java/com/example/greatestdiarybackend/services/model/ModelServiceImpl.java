package com.example.greatestdiarybackend.services.model;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.NoteModel;
import com.example.greatestdiarybackend.models.UserModel;
import com.example.greatestdiarybackend.services.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ModelServiceImpl implements ModelService {

    private final NoteService noteService;

    @Autowired
    public ModelServiceImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public UserModel getUserModel(User user) {
        return new UserModel()
                .setName(user.getName())
                .setEmail(user.getEmail());
    }

    @Override
    public NoteModel createNoteModel(Note note) {
        return new NoteModel()
                .setUuid(note.getUuid())
                .setDescription(note.getDescription())
                .setDate(note.getDate())
                .setEmojiName(note.getEmojiName())
                .setLocation(note.getLocation());
    }

    @Override
    public List<NoteModel> getListNoteModel(Long id) {
        List<Note> notes = noteService.findNoteByUserId(id);
        List<NoteModel> noteModels = new ArrayList<>();

        for (Note note : notes) {
            noteModels.add(createNoteModel(note));
        }

        return noteModels;
    }
}
