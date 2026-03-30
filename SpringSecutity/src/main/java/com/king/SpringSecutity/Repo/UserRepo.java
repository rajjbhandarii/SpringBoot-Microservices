package com.king.SpringSecutity.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.SpringSecutity.Model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
