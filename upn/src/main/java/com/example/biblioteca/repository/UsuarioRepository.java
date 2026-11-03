package com.example.biblioteca.repository;


import com.example.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {

    // Buscar usuario por username
    Optional<Usuario> findByUsername(String username);

    // Verificar si existe un username
    boolean existsByUsername(String username);

    // Verificar si existe un correo
    boolean existsByEmail(String email);

    // Buscar usuarios por estado
    List<Usuario> findByActivo(Boolean activo);

    // Buscar usuarios por nombre
    List<Usuario> findByNombreContainingIgnoreCase(
            String nombre);
}