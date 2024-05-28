package com.example.greatestdiarybackend.services.model;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.NoteModel;
import com.example.greatestdiarybackend.models.TargetModel;
import com.example.greatestdiarybackend.models.UserModel;

import java.util.List;

public interface ModelService {
    UserModel getUserModel(User user);
    NoteModel createNoteModel(Note note);
    List<NoteModel> getListNoteModel(Long id);
    TargetModel createTargetModel(Target target);
    List<TargetModel> getListTargetModel(Long id);
}
