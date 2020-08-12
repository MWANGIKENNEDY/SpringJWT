package com.springjwt.springjwt.services;

import com.springjwt.springjwt.models.UserModel;
import com.springjwt.springjwt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public void registerUser(UserModel userModel) {
        userRepo.save(userModel);
    }
      
}