package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public record UsuarioRequest(

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    String nombre,

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 3, max = 50)
    String username,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo electrónico inválido")
    @Size(max = 120)
    String email,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 72)
    String password

) {}