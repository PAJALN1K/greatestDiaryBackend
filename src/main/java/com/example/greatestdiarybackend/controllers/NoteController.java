package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.NoteForm;
import com.example.greatestdiarybackend.models.NoteModel;
import com.example.greatestdiarybackend.services.authentication.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.model.ModelService;
import com.example.greatestdiarybackend.services.note.NoteService;
import com.example.greatestdiarybackend.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final ModelService modelService;
    private final AuthenticatedUserService authenticatedUserService;

    @Autowired
    public NoteController(NoteService noteService, ModelService modelService, AuthenticatedUserService authenticatedUserService) {
        this.noteService = noteService;
        this.modelService = modelService;
        this.authenticatedUserService = authenticatedUserService;
    }


    @GetMapping
    public ModelAndView notes() {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        List<NoteModel> noteModels = modelService.getListNoteModel(authenticatedUser.getId());

        return new ModelAndView("my-notes")
                .addObject("notes", noteModels);
    }

    @GetMapping("/{uuid}")
    public ModelAndView noteDetails(@PathVariable String uuid) {
        Note uuidNote = noteService.findNoteByUuid(uuid);
        NoteModel noteModel = modelService.createNoteModel(uuidNote);

        return new ModelAndView("note-details")
                .addObject("note", noteModel);
    }

    @GetMapping("/create")
    public ModelAndView noteForm() {
        return new ModelAndView("create-note")
                .addObject("noteForm", new NoteForm());
    }

    @PostMapping("/create")
    public ModelAndView noteAction(
            @ModelAttribute @Valid NoteForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("create-note")
                    .addObject("noteForm", new NoteForm());
        }
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        Note newNote = noteService.createNote(form, authenticatedUser);
        noteService.save(newNote);

        return RedirectUtil.redirect("/note");
    }
}
