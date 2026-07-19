package com.example.SIS_INVENTARIO_BACK.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}