package com.proyecto.upn;
import com.proyecto.upn.entity.Usuario;
import com.proyecto.upn.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {


    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) 
    {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional 
    public Usuario registrar(String nombre, String username, String email, String password) 
    {
        if (nombre == null || nombre.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        if (repository.existsByUsername(username)) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordEncoder.encode(password));
        usuario.setRol("LECTOR");
        usuario.setActivo(true);
        return repository.save(usuario);
    }



}
