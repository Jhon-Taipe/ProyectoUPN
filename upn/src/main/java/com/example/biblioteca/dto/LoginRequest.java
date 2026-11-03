package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @NotBlank(message = "Ingrese su usuario")
    String username,

    @NotBlank(message = "Ingrese su contraseña")
    String password

) {}