package com.example.test404.rest;

import com.example.test404.models.Role;
import com.example.test404.models.Status;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponseDTO {
    private String email;
    private String token;
    private Role roles;
    private Status status;
}
