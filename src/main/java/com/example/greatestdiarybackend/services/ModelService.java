package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.UserModel;

public interface ModelService {
    UserModel getUserModel(User user);
}
