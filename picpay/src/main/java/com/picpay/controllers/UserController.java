package com.picpay.controllers;

import com.picpay.domain.user.User;
import com.picpay.dtos.UserDTO;
import com.picpay.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<User> createUser(UserDTO user){
        User newUser = userServices.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User>  users = this.userServices.getAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
