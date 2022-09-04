package com.kadirirpik.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailSenderDto {
    @NotBlank(message = "Enter your name.")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50.")
    private String name;
    @NotBlank(message = "Email address cannot be left blank.")
    @Email
    private String from;
    @NotBlank(message = "The subject cannot be left blank.")
    @Size(min = 3, max = 100, message = "Size must be between 3 and 100.")
    private String subject;
    @NotBlank(message = "Message field cannot be left blank.")
    @Size(min = 3, max = 250, message = "Size must be between 3 and 250.")
    private String content;
}
