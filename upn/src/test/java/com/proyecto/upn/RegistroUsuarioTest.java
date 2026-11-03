package com.proyecto.upn;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.proyecto.upn.repository.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.proyecto.upn.entity.Usuario;

@SpringBootTest
public class RegistroUsuarioTest {

    @Autowired 
    private UsuarioService usuarioService;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Test 
    void probarRegistrarUsuario() 
    {
        // Aquí puedes escribir el código para probar el registro de un usuario
        // Por ejemplo, crear un objeto Usuario y llamar al método de registro
        String username = "alumno_" + UUID.randomUUID().toString().substring(0, 8);

        Usuario usuario = usuarioService.registrar(
            
            "Ronny Lagos",
            username,
            username + "@upn.pe",
            "upn123@"
        );

        assertNotNull(usuario);
        assertNotNull(usuario.getId());

        System.out.println("Usuario registardo correctamente");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
        

    }

    
}
