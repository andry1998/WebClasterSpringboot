package com.example.test404.rest;

import com.example.test404.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthRequestDTO {
    private String email;
    private String password;
}
