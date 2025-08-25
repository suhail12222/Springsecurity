package com.practices.demo.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class SignupDto {
    private String email;
    private String password;
    private String name;
}
