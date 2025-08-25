package com.practices.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String email;
private String name;
}
