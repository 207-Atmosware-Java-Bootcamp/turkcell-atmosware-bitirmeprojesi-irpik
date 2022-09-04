package com.kadirirpik.controller;

import com.kadirirpik.business.dto.MailSenderDto;
import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IProductservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MainController {
    private IProductservice productservice;

    @Autowired
    public MainController(IProductservice productservice) {
        this.productservice = productservice;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("mailDto", new MailSenderDto());
    }

    @GetMapping({"/", "/index", "/index.html"})
    public String index(Model model){
        model.addAttribute("productList", productservice.productList());
        return "index";
    }

}
