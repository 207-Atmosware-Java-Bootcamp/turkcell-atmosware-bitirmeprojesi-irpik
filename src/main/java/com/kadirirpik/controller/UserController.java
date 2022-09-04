package com.kadirirpik.controller;

import com.kadirirpik.business.dto.MailSenderDto;
import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IUserService;
import com.kadirirpik.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("mailDto", new MailSenderDto());
    }

    @GetMapping("/usersave")
    public String userSaveGet(){
        return "index";
    }

    @PostMapping("/usersave")
    public String userSavePost(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("saveerror", true);
            return "index";
        }
        if (userService.existsByEmail(userDto.getEmail())){
            model.addAttribute("registered", true);
            return "index";
        }
        userService.userSave(userDto);
        redirectAttributes.addFlashAttribute("savesuccess", true);
        return "redirect:/index";
    }

}
