package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.RegistrationForm;
import com.example.greatestdiarybackend.services.UserService;
import com.example.greatestdiarybackend.utils.RedirectUtil;
import com.example.greatestdiarybackend.validators.RegistrationValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public RegistrationController(UserService userService, RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.registrationValidator = registrationValidator;
    }

    @InitBinder("registrationForm")
    public void setRegistrationForm(WebDataBinder binder) {
        binder.addValidators(registrationValidator);
    }

    @GetMapping
    public ModelAndView registrationForm() {
        return new ModelAndView("registration")
                .addObject("registrationForm", new RegistrationForm());
    }

    @PostMapping
    public ModelAndView registrationAction(
            @ModelAttribute @Valid RegistrationForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("registration")
                    .addObject("registrationForm", new RegistrationForm());
        }
        User createdUser = userService.create(form);
        userService.save(createdUser);

        return RedirectUtil.redirect("/login");
    }
}
