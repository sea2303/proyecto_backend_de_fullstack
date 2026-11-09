package com.pagina_web.backend.controller;

import com.pagina_web.backend.model.Usuario;
import com.pagina_web.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PostMapping
    public Usuario registrar(@Valid @RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    // ENDPOINT PUT (http://localhost:8080/api/usuarios/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = usuarioService.actualizar(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ENDPOINT DELETE (http://localhost:8080/api/usuarios/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminar(id);
            return ResponseEntity.ok("Usuario eliminado correctamente con id: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
