package com.springjwt.springjwt.repository;
import com.springjwt.springjwt.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserModel,Long> {

    @Query("select u from UserModel u where u.username=:username")
    public UserModel getUser(@Param("username") String username);
    
}