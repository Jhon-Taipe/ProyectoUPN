package com.example.biblioteca;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.service.UsuarioService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistroUsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Transactional
    @Commit 
    void probarRegistrarUsuario() {

        String username = "alumno_" +
                UUID.randomUUID().toString().substring(0, 8);

        Usuario usuario = usuarioService.registrar(
                "Ronny Lagos",
                username,
                username + "@upn.pe",
                "Password123"
        );

        assertNotNull(usuario);
        assertNotNull(usuario.getId());

        System.out.println("USUARIO REGISTRADO CORRECTAMENTE");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
    }
}