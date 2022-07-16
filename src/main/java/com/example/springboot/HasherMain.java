package com.example.springboot;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class HasherMain {
    public static void main(String[] args) {
        final Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
    }
}
