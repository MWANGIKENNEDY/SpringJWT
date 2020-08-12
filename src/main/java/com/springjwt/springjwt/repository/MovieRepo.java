package com.springjwt.springjwt.repository;

import com.springjwt.springjwt.models.MoviesModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<MoviesModel,Long> {
    
}