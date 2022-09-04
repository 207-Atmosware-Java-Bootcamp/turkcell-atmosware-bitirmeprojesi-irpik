package com.kadirirpik.business.service.Impl;

import com.kadirirpik.business.service.IRoleService;
import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Role;
import com.kadirirpik.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private IRoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleName(ERole role) {
        return roleRepository.findByRoleName(role);
    }
}
