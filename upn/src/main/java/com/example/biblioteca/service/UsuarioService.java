package com.example.biblioteca.service;

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar un usuario
    @Transactional
    public Usuario registrar(
            String nombre,
            String username,
            String email,
            String password) {

        if (nombre == null || nombre.isBlank()
                || username == null || username.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                "Todos los campos son obligatorios"
            );
        }

        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException(
                "El nombre de usuario ya existe"
            );
        }

        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                "El correo electronico ya existe"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setEmail(email);

        usuario.setPasswordHash(
            passwordEncoder.encode(password)
        );

        usuario.setRol("LECTOR");
        usuario.setActivo(true);

        return repository.save(usuario);
    }

    // Consultar todos los usuarios
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // Buscar usuario por identificador
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Buscar usuario por nombre de acceso
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorUsername(
            String username) {

        return repository.findByUsername(username);
    }

    // Contar usuarios registrados
    @Transactional(readOnly = true)
    public long contarUsuarios() {
        return repository.count();
    }
}