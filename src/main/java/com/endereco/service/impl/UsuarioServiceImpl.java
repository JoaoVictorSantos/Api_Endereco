package com.endereco.service.impl;

import com.endereco.domain.entity.Usuario;
import com.endereco.repository.UsuarioRepository;
import com.endereco.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmailEquals(email);
    }
}
