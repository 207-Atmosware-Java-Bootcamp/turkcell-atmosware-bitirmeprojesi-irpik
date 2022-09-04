package com.kadirirpik.business.service;

import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    UserDto userSave(UserDto userDto);
    Boolean existsByEmail(String email);
    UserDto findByEmail(String email);
}
