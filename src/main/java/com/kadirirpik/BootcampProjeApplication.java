package com.kadirirpik;

import com.kadirirpik.business.service.IRoleService;
import com.kadirirpik.entities.ERole;
import com.kadirirpik.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootcampProjeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootcampProjeApplication.class, args);
    }

}
