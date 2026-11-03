package com.example.biblioteca.dto;

import com.example.biblioteca.entity.Usuario;

public record UsuarioResponse(

    Long id,
    String nombre,
    String username,
    String email,
    String rol,
    boolean activo

) {

    public static UsuarioResponse from(Usuario u) {

        return new UsuarioResponse(
            u.getId(),
            u.getNombre(),
            u.getUsername(),
            u.getEmail(),
            u.getRol(),
            Boolean.TRUE.equals(u.getActivo())
        );
    }
}