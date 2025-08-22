package com.practices.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class SignupDto {
    private String email;
    private String password;
    private String name;
}
