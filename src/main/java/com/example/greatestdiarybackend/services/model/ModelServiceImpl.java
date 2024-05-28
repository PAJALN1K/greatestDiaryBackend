package com.example.greatestdiarybackend.services.model;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.NoteModel;
import com.example.greatestdiarybackend.models.TargetModel;
import com.example.greatestdiarybackend.models.UserModel;
import com.example.greatestdiarybackend.services.note.NoteService;
import com.example.greatestdiarybackend.services.target.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ModelServiceImpl implements ModelService {

    private final NoteService noteService;
    private final TargetService targetService;

    @Autowired
    public ModelServiceImpl(NoteService noteService, TargetService targetService) {
        this.noteService = noteService;
        this.targetService = targetService;
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

    @Override
    public TargetModel createTargetModel(Target target) {
        return new TargetModel()
                .setUuid(target.getUuid())
                .setStartDate(target.getStartDate())
                .setPriority(target.getPriority())
                .setStatus(target.getStatus())
                .setTitle(target.getTitle())
                .setDescription(target.getDescription());
    }

    @Override
    public List<TargetModel> getListTargetModel(Long id) {
        List<Target> targets = targetService.findTargetByUserId(id);
        List<TargetModel> targetModels = new ArrayList<>();

        for (Target target : targets) {
            targetModels.add(createTargetModel(target));
        }

        return targetModels;
    }
}
