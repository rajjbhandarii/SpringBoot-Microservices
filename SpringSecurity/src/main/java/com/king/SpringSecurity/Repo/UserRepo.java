package com.king.SpringSecurity.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.SpringSecurity.Model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
