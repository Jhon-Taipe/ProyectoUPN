package com.example.biblioteca.controller;

import java.net.URI;
import java.util.List;

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.service.UsuarioService;

import com.example.biblioteca.dto.UsuarioRequest;
import com.example.biblioteca.dto.UsuarioResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // 1. LISTAR TODOS LOS USUARIOS - GET

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponse>> index() {

        List<UsuarioResponse> usuarios = service.listar()
                .stream()
                .map(UsuarioResponse::from)
                .toList();

        return ResponseEntity.ok(usuarios);
    }

    // 2. CONSULTAR USUARIO POR ID - GET

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> show(
            @PathVariable Long id) {

        Usuario usuario = service.buscarPorId(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                    )
                );

        return ResponseEntity.ok(
            UsuarioResponse.from(usuario)
        );
    }

    // 3. CREAR NUEVO USUARIO - POST

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> create(
            @Valid @RequestBody UsuarioRequest req) {

        Usuario usuario = service.registrar(
            req.nombre(),
            req.username(),
            req.email(),
            req.password()
        );

        UsuarioResponse respuesta =
            UsuarioResponse.from(usuario);

        URI location = URI.create(
            "/api/usuarios/" + respuesta.id()
        );

        return ResponseEntity
                .created(location)
                .body(respuesta);
    }

}
