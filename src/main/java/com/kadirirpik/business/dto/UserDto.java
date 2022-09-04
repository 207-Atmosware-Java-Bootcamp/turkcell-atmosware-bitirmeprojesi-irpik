package com.kadirirpik.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;

    @NotBlank(message = "Kullanıcı adınızı giriniz.")
    @Size(min = 3, max = 50, message = "Min 3 , Max 50 karakter.")
    private String firstName;

    @NotBlank(message = "Kullanıcı soyadınızı giriniz.")
    @Size(min = 3, max = 50, message = "Min 3 , Max 50 karakter.")
    private String lastName;

    @NotBlank(message = "Mail adresini giriniz.")
    @Email(message = "Hatalı mail adresi.")
    private String email;

    @NotBlank(message = "Parolanızı giriniz.")
    @Size(min = 7, max = 50, message = "Min. 7 Max. 50 karakter.")
    private String password;

}
