package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.forms.PasswordSettingsForm;
import com.example.greatestdiarybackend.models.UserModel;
import com.example.greatestdiarybackend.services.authentication.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.model.ModelService;
import com.example.greatestdiarybackend.services.user.UserService;
import com.example.greatestdiarybackend.utils.RedirectUtil;
import com.example.greatestdiarybackend.validators.PasswordSettingsValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PasswordSettingsController {
    private final AuthenticatedUserService authenticatedUserService;
    private final ModelService modelService;
    private final PasswordSettingsValidator passwordSettingsValidator;
    private final UserService userService;

    @Autowired
    public PasswordSettingsController(
            AuthenticatedUserService authenticatedUserService,
            ModelService modelService,
            PasswordSettingsValidator passwordSettingsValidator,
            UserService userService
    ) {
        this.authenticatedUserService = authenticatedUserService;
        this.modelService = modelService;
        this.passwordSettingsValidator = passwordSettingsValidator;
        this.userService = userService;
    }

    @InitBinder("passwordSettingsForm")
    public void setRegistrationForm(WebDataBinder binder) {
        binder.addValidators(passwordSettingsValidator);
    }

    @GetMapping("/profile/{name}/password")
    public ModelAndView passwordSettingsForm(@PathVariable String name) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        UserModel authenticatedUserModel = modelService.getUserModel(authenticatedUser);

        return new ModelAndView("change-password")
                .addObject("authenticatedUser", authenticatedUserModel);
    }

    @PutMapping("/profile/{name}/password")
    public ModelAndView passwordSettingsAction(
            @PathVariable String name,
            @ModelAttribute @Valid PasswordSettingsForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("change-password")
                    .addObject("error", result.getAllErrors())
                    .addObject("passwordSettingsForm", new PasswordSettingsForm());
        }
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        userService.updatePassword(form.getNewPassword(), authenticatedUser);

        return RedirectUtil.redirect("/profile/{name}");
    }
}
