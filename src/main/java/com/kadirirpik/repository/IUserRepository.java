package com.kadirirpik.repository;

import com.kadirirpik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
