package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDTO {
    public static final String SERVER_ERROR = "server_error";
    public static final String ARGUMENT_NOT_VALID = "argument_not_valid";
    public static final String USER_NOT_FOUND = "user_not_found";
    public static final String NOT_ENOUGH_AUTHORITY = "not_enough_authority";
    public static final String POST_NOT_FOUND = "post_not_found";
    public static final String LOGIN_ALREADY_EXISTS = "login_already_exists";
    private String message;
}
