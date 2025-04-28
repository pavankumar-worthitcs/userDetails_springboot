package com.wcs.userDetails.controller;

import com.wcs.userDetails.dto.UserDTO;
import com.wcs.userDetails.entities.UserEntity;
import com.wcs.userDetails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class userController {

    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public UserDTO saveUser(@RequestBody  UserEntity userEntity){
        return userService.saveUser(userEntity);
    }

    @GetMapping("/fetchUserById")
    public Optional<UserDTO> fetchUserById(@RequestParam Long userId){
        return userService.fetchUserById(userId);
    }

    @PatchMapping("/changePassword")
    public String changePassword(@RequestParam String userEmail,@RequestParam String oldPassword,@RequestParam String newPassword){
        return userService.changePassword(userEmail.trim(),oldPassword.trim(),newPassword.trim());
    }

    @PutMapping("/updateUserById")
    public UserDTO updateUserById(@RequestParam Long userId,@RequestBody UserEntity newUserEntity){
        return userService.updateUserById(userId,newUserEntity);
    }

}
