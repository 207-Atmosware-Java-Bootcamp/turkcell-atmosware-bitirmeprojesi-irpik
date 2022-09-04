package com.kadirirpik.business.service.Impl;

import com.kadirirpik.business.dto.MailSenderDto;
import com.kadirirpik.business.service.IMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements IMailSenderService {

    private final String TO = "kadirirpikbootcamp@gmail.com";
    private JavaMailSender mailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean simpleMailSendMethod(MailSenderDto mailSenderdto) {
        StringBuilder content = new StringBuilder();
        content.append(mailSenderdto.getName()).append(" => ").append(mailSenderdto.getContent());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSenderdto.getFrom());
        simpleMailMessage.setTo(TO);
        simpleMailMessage.setSubject(mailSenderdto.getSubject());
        simpleMailMessage.setText(content.toString());
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
