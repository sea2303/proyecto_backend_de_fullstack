package com.pagina_web.backend.service;

import com.pagina_web.backend.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();
    Usuario registrar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}
