package com.endereco.service;

import com.endereco.domain.entity.Usuario;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> findByEmail(String email);
}
