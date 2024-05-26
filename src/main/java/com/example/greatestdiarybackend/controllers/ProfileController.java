package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.UserModel;
import com.example.greatestdiarybackend.services.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.ModelService;
import com.example.greatestdiarybackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    private final AuthenticatedUserService authenticatedUserService;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    public ProfileController(AuthenticatedUserService authenticatedUserService, ModelService modelService, UserService userService) {
        this.authenticatedUserService = authenticatedUserService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @GetMapping("/profile/{name}")
    public ModelAndView userProfile(@PathVariable String name, Authentication authentication) {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        UserModel authenticatedUserModel = modelService.getUserModel(authenticatedUser);

        User otherUser = userService.getUserByName(name);
        UserModel otherUserModel = modelService.getUserModel(otherUser);

        if (!(authentication.isAuthenticated() && authenticatedUser.getName().equals(otherUser.getName()))) {
            return new ModelAndView("other-profile")
                    .addObject("otherUser", otherUserModel);
        }

        return new ModelAndView("auth-profile")
                .addObject("authenticatedUser", authenticatedUserModel);
    }
}
