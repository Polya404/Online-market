package com.market.back.controllers;

import com.market.back.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record RegistrationForm(String username, String password, String fullName, String street, String city,
                               String phoneNumber) {
    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password),
                fullName, street, city, phoneNumber);
    }
}
