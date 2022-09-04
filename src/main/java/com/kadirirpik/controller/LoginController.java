package com.kadirirpik.controller;

import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    private IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getName().equals("anonymousUser")) {
            UserDto user = userService.findByEmail(authentication.getName());
            session.setAttribute("userDtoFirstName", user.getFirstName());
            session.setAttribute("userDtoLastName", user.getLastName());
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN"))
                    return "admin";
            }
        }
        return "redirect:/index";
    }

}
