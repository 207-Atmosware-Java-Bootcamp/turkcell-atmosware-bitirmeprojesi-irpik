package com.kadirirpik.business.service;

import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Role;

public interface IRoleService {
    Role saveRole(Role role);
    Role findByRoleName(ERole role);
}
