package com.practices.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
public class LoginDto {
private final String email;
private final String  password;

}
