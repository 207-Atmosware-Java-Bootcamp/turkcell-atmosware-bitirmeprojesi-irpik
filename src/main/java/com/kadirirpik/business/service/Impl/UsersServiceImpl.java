package com.kadirirpik.business.service.Impl;

import com.kadirirpik.bean.ModelMapperBean;
import com.kadirirpik.bean.PasswordEncoderMyBean;
import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IRoleService;
import com.kadirirpik.business.service.IUserService;
import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Role;
import com.kadirirpik.entities.User;
import com.kadirirpik.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsersServiceImpl implements IUserService {
    private PasswordEncoderMyBean passwordEncoderMyBean;
    private ModelMapperBean modelMapper;
    private IUserRepository userRepository;
    private IRoleService roleService;

    @Autowired
    public UsersServiceImpl(PasswordEncoderMyBean passwordEncoderMyBean, IUserRepository userRepository, ModelMapperBean modelMapper,
                            IRoleService roleService) {
        this.passwordEncoderMyBean = passwordEncoderMyBean;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    //################ Configuration #################
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Invalid user email or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),roleAuthories(user.getRelationRoleRegisterEntities()));
    }

    private Collection<? extends GrantedAuthority> roleAuthories(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName().toString())).collect(Collectors.toList());
    }
    //#################################################

    @Override
    public UserDto userSave(UserDto userDto) {
        User user1 = modelMapper.modelMapperMethod().map(userDto, User.class);
        user1.setPassword(passwordEncoderMyBean.passwordEncoder().encode(user1.getPassword()));
        user1.setEnabled(true);
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findByRoleName(ERole.ROLE_USER));
        user1.setRelationRoleRegisterEntities(roleList);
        User user2 = userRepository.save(user1);
        return modelMapper.modelMapperMethod().map(user2, UserDto.class);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto findByEmail(String email) {
        return modelMapper.modelMapperMethod().map(userRepository.findByEmail(email), UserDto.class);
    }

}
