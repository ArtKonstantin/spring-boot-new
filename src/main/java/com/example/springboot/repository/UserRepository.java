package com.example.springboot.repository;

import com.example.springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT e FROM UserEntity e WHERE e.login = :login")
    Optional<UserEntity> findByLogin(String login);
}
