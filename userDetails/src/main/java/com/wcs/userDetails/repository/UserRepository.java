package com.wcs.userDetails.repository;

import com.wcs.userDetails.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserEmailAndUserPassword(String email,String password);

}
