package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.Note;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.NoteForm;
import com.example.greatestdiarybackend.models.NoteModel;
import com.example.greatestdiarybackend.services.authentication.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.model.ModelService;
import com.example.greatestdiarybackend.services.note.NoteService;
import com.example.greatestdiarybackend.utils.RedirectUtil;
import com.example.greatestdiarybackend.validators.NoteValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final ModelService modelService;
    private final AuthenticatedUserService authenticatedUserService;
    private final NoteValidator noteValidator;

    @Autowired
    public NoteController(
            NoteService noteService,
            ModelService modelService,
            AuthenticatedUserService authenticatedUserService,
            NoteValidator noteValidator
    ) {
        this.noteService = noteService;
        this.modelService = modelService;
        this.authenticatedUserService = authenticatedUserService;
        this.noteValidator = noteValidator;
    }

    @InitBinder("noteForm")
    public void setNoteForm(WebDataBinder binder) {
        binder.addValidators(noteValidator);
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
                    .addObject("error", result.getAllErrors())
                    .addObject("noteForm", new NoteForm());
        }
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        Note newNote = noteService.createNote(form, authenticatedUser);
        noteService.save(newNote);

        return RedirectUtil.redirect("/note");
    }

    @DeleteMapping("/{uuid}/delete")
    public ModelAndView noteDeleteAction(@PathVariable String uuid) {
        noteService.deleteByUuid(uuid);

        return RedirectUtil.redirect("/note");
    }

    @GetMapping("/{uuid}/change")
    public ModelAndView noteChangeForm(@PathVariable String uuid) {
        Note note = noteService.findNoteByUuid(uuid);
        NoteModel noteModel = modelService.createNoteModel(note);

        return new ModelAndView("note-change")
                .addObject("noteForm", new NoteForm())
                .addObject("note", noteModel);
    }

    @PutMapping("/{uuid}/change")
    public ModelAndView noteChangeAction(
            @PathVariable String uuid,
            @ModelAttribute @Valid NoteForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("note-change")
                    .addObject("error", result.getAllErrors())
                    .addObject("noteForm", new NoteForm());
        }
        Note note = noteService.findNoteByUuid(uuid);
        Note changedNote = noteService.changeNote(form, note);
        noteService.save(changedNote);

        return RedirectUtil.redirect("/note/{uuid}");
    }
}
