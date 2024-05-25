package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.UserModel;

public class ModelServiceImpl implements ModelService {
    @Override
    public UserModel getUserModel(User user) {
        return new UserModel()
                .setName(user.getName())
                .setEmail(user.getEmail());
    }
}
