package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserResponseDTO {
    private long id;
    private String login;
    private List<String> roles;
}