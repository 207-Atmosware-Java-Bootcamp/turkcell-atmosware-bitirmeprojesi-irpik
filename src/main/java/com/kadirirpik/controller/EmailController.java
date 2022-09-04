package com.kadirirpik.controller;

import com.kadirirpik.business.dto.MailSenderDto;
import com.kadirirpik.business.dto.UserDto;
import com.kadirirpik.business.service.IMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class EmailController {

    private IMailSenderService mailSenderService;

    @Autowired
    public EmailController(IMailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("mailDto", new MailSenderDto());
    }

    @GetMapping("/mail")
    public String getMail(){
        return "index";
    }

    @PostMapping("/mail")
    public String postMail(@Valid @ModelAttribute("mailDto") MailSenderDto mailSenderDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "index";
        }
        if (mailSenderService.simpleMailSendMethod(mailSenderDto)) {
            redirectAttributes.addFlashAttribute("mailSendSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("mailSendError", true);
        }
        return "redirect:/index";
    }
}
