package com.practices.demo.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private int id;
    private String name;

    private String role;
    private String adress;
    private String email;
    private int age;
    private String phno;
}
