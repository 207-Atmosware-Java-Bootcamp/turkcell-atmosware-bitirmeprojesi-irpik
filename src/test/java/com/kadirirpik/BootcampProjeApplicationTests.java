package com.kadirirpik;

import com.kadirirpik.business.dto.ProductDto;
import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IProductservice;
import com.kadirirpik.business.service.IRoleService;
import com.kadirirpik.business.service.IUserService;
import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Product;
import com.kadirirpik.entities.Role;
import com.kadirirpik.entities.User;
import com.kadirirpik.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BootcampProjeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    IRoleService roleService;

    @Test
    public void addRole(){
        Role r = new Role();
        r.setRoleName(ERole.ROLE_WRITER);
        roleService.saveRole(r);
    }
    @Autowired
    IUserService userService;
    @Test
    public void addUser() {
        UserDto user = new UserDto();
        user.setPassword("12345678");
        user.setEmail("admin@gmail.com");
        user.setFirstName("adminfirstname");
        user.setLastName("adminlastname");
        userService.userSave(user);
    }
    @Autowired
    IUserRepository userRepository;
    @Test
    public void userDelete(){
        Optional<User> u = userRepository.findById(4L);
        u.get().getRelationRoleRegisterEntities().clear();
        userRepository.delete(u.get());
    }

    @Autowired
    IProductservice productservice;
    @Test
    public void addProduct(){
        ProductDto p = new ProductDto();
        p.setProductName("Deneme");
        p.setProductPrice(100.0);
        p.setProductTrade("Abc");
        p.setProductSerialNumber("Abc123");
        productservice.saveProduct(p);
    }

}
