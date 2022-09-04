package com.kadirirpik.repository;

import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(ERole roleName);
}
