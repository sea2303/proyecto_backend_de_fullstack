package com.pagina_web.backend.service.impl;

import com.pagina_web.backend.model.Usuario;
import com.pagina_web.backend.repository.UsuarioRepository;
import com.pagina_web.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        return usuarioRepository.save(usuario);
    }

    // 🛠️ IMPLEMENTACIÓN DEL PUT (ACTUALIZAR)
    @Override
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());

            // Si mandas una contraseña nueva, la encriptamos; si no, dejamos la anterior
            if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isBlank()) {
                usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
            }

            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
    }

    // 🗑️ IMPLEMENTACIÓN DEL DELETE (ELIMINAR)
    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Usuario no encontrado con el id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
