package com.example.greatestdiarybackend.services;

import com.example.greatestdiarybackend.entities.User;
import com.example.greatestdiarybackend.models.UserModel;
import org.springframework.stereotype.Controller;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ModelServiceImpl implements ModelService {
    private static final Logger logger = Logger.getLogger(ModelServiceImpl.class.getName());

    @Override
    public UserModel getUserModel(User user) {
        UserModel userModel = new UserModel()
                .setName(user.getName())
                .setEmail(user.getEmail());
        logger.log(Level.INFO, "Модель пользователя {0} успешно создана", userModel.getName());

        return userModel;
    }
}
