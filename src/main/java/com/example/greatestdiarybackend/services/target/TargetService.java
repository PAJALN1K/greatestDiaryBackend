package com.example.greatestdiarybackend.services.target;

import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.entities.statuses.Status;
import com.example.greatestdiarybackend.forms.TargetForm;

import java.util.List;

public interface TargetService {
    List<Target> findTargetByUserId(Long id);
    Target findTargetByUuid(String uuid);
    Target createTarget(TargetForm targetForm, User authenticatedUser);
    void save(Target target);
    Target changeStatus(String uuid, Status status);
}
