package com.example.greatestdiarybackend.controllers;

import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.entities.statuses.Status;
import com.example.greatestdiarybackend.forms.TargetForm;
import com.example.greatestdiarybackend.models.TargetModel;
import com.example.greatestdiarybackend.services.authentication.AuthenticatedUserService;
import com.example.greatestdiarybackend.services.model.ModelService;
import com.example.greatestdiarybackend.services.target.TargetService;
import com.example.greatestdiarybackend.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/target")
public class TargetController {
    private final AuthenticatedUserService authenticatedUserService;
    private final TargetService targetService;
    private final ModelService modelService;

    @Autowired
    public TargetController(AuthenticatedUserService authenticatedUserService, TargetService targetService, ModelService modelService) {
        this.authenticatedUserService = authenticatedUserService;
        this.targetService = targetService;
        this.modelService = modelService;
    }

    @GetMapping
    public ModelAndView targets() {
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        List<TargetModel> targetModels = modelService.getListTargetModel(authenticatedUser.getId());

        return new ModelAndView("my-targets")
                .addObject("targets", targetModels);
    }

    @GetMapping("/create")
    public ModelAndView targetForm() {
        return new ModelAndView("create-target")
                .addObject("targetForm", new TargetForm());
    }

    @PostMapping("/create")
    public ModelAndView targetAction(
            @ModelAttribute @Valid TargetForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("create-target")
                    .addObject("error", result.getAllErrors())
                    .addObject("targetForm", new TargetForm());
        }
        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();
        Target newTarget = targetService.createTarget(form, authenticatedUser);
        targetService.save(newTarget);

        return RedirectUtil.redirect("/target");
    }

    @GetMapping("/{uuid}")
    public ModelAndView targetList(@PathVariable String uuid) {
        Target target = targetService.findTargetByUuid(uuid);
        TargetModel targetModel = modelService.createTargetModel(target);

        return new ModelAndView("target-details")
                .addObject("status", Status.values())
                .addObject("target", targetModel);
    }

    @PutMapping("/{uuid}/status")
    public ModelAndView updateStatusToCompleted(@PathVariable String uuid, @RequestParam(name = "status", required = false) String status) {
        Target target = targetService.findTargetByUuid(uuid)
                .setStatus(Status.valueOf(status));
        targetService.save(target);

        return RedirectUtil.redirect("/target/{uuid}");
    }
}
