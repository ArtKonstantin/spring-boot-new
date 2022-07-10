package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostRequestDTO {
    private long id;
    private String name;
    private String content;
}
