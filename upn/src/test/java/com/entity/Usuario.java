package com.entity;
import jakarta.persistence.*;

@Entity 
@Tabla(name = "usuario")

public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column (nullable = false, length = 20)
    private String rol;

    @Column (nullable = false)
    private boolean activo = true;

    


}
