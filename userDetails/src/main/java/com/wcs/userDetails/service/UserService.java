package com.wcs.userDetails.service;

import com.wcs.userDetails.UserDetailsApplication;
import com.wcs.userDetails.dto.UserDTO;
import com.wcs.userDetails.entities.UserEntity;
import com.wcs.userDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    UserDTO userDTO = new UserDTO();

    public UserDTO saveUser(UserEntity userEntity){
        if(userEntity.getCreatedBy() == null) {
            userEntity.setCreatedBy(userEntity.getUserName());
        }
        userEntity.setModifiedBy(userEntity.getUserName());
        UserEntity savedUser = userRepository.save(userEntity);
        userDTO.setUserId(savedUser.getUserId());
        userDTO.setUserName(savedUser.getUserName());
        userDTO.setUserEmail(savedUser.getUserEmail());
        return userDTO;
    }


    public Optional<UserDTO> fetchUserById(Long userId) {
        Optional<UserEntity> dbUser = userRepository.findById(userId);
        if(dbUser.isPresent()) {
            return dbUser.map((userEntity -> {
                userDTO.setUserId(userEntity.getUserId());
                userDTO.setUserName(userEntity.getUserName());
                userDTO.setUserEmail(userEntity.getUserEmail());
                return userDTO;
            }));
        }
        return Optional.empty();
    }

    public String  changePassword(String userEmail,String oldPassword,String newPassword) {
        UserEntity dbUserEntity = userRepository.findByUserEmailAndUserPassword(userEmail, oldPassword);
        if (dbUserEntity != null) {
            dbUserEntity.setUserPassword(newPassword);
            saveUser(dbUserEntity);
            return "Your password has successfully changed !";
        }
        return "please Enter valid Email and Password !";
    }

    public UserDTO updateUserById(Long userId, UserEntity newUserEntity) {
        UserEntity dbUserEntity=userRepository.findById(userId).orElse(null);
        if (dbUserEntity != null) {
            dbUserEntity.setUserId(newUserEntity.getUserId());
            dbUserEntity.setUserName(newUserEntity.getUserName());
            dbUserEntity.setModifiedOn(LocalDateTime.now());
            return saveUser(newUserEntity);
        }
        return null;
    }

}
