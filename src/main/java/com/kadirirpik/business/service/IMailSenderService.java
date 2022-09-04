package com.kadirirpik.business.service;

import com.kadirirpik.business.dto.MailSenderDto;

public interface IMailSenderService {
    boolean simpleMailSendMethod(MailSenderDto mailSenderdto);
}
