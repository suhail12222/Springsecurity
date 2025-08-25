package com.practices.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PostDto {
    private long id;
    private String tittle;
    private String content;
}
