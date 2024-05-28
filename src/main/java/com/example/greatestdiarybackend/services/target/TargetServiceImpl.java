package com.example.greatestdiarybackend.services.target;

import com.example.greatestdiarybackend.entities.Target;
import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.entities.statuses.Status;
import com.example.greatestdiarybackend.forms.TargetForm;
import com.example.greatestdiarybackend.repositories.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TargetServiceImpl implements TargetService{
    private final TargetRepository targetRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public List<Target> findTargetByUserId(Long id) {
        return targetRepository.findTargetByUserId(id);
    }

    @Override
    public Target findTargetByUuid(String uuid) {
        return targetRepository.findTargetByUuid(uuid);
    }

    @Override
    public Target createTarget(TargetForm targetForm, User authenticatedUser) {
        Target newTarget = new Target()
                .setDescription(targetForm.getDescription())
                .setPriority(targetForm.getPriority())
                .setUser(authenticatedUser)
                .setStatus(Status.IN_PROGRESS)
                .setTitle(targetForm.getTitle())
                .setStartDate(LocalDateTime.now())
                .setUuid(generateUuid());

        return newTarget;
    }

    @Override
    public void save(Target target) {
        targetRepository.save(target);
    }

    @Override
    public Target changeStatus(String uuid, Status status) {
        Target target = targetRepository.findTargetByUuid(uuid)
                .setStatus(status);

        return target;
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
