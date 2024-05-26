package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.UserModel;
import com.example.greatestdiarybackend.services.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final AuthenticatedUserService authenticatedUserService;
    private final ModelService modelService;

    @Autowired
    public HomeController(AuthenticatedUserService authenticatedUserService, ModelService modelService) {
        this.authenticatedUserService = authenticatedUserService;
        this.modelService = modelService;
    }

    @GetMapping("/")
    public ModelAndView homePageBeforeLogin() {
        return new ModelAndView("home-before");
    }

    @GetMapping("/home")
    public ModelAndView homePageAfterLogin() {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        UserModel authenticatedUserModel = modelService.getUserModel(authenticatedUser);

        authenticatedUserService.setUserAuthentication(authenticatedUser.getName());

        return new ModelAndView("home-after")
                .addObject("authenticatedUser", authenticatedUserModel);
    }
}
