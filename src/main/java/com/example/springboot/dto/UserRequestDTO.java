package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@Data
public class UserRequestDTO {
    @Min(0)
    private long id;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9]{3,100}")
    private String login;
    @NotNull
    @Size(min = 8, max = 64)
    private String password;
    @NotNull
    @Size(min = 1, max = 100)
    private List<@NotNull @Pattern(regexp = "ROLE_[A-Z]{1,100}") String> roles;
}