package com.endereco.security.service;

import java.util.Optional;

import com.endereco.domain.entity.Usuario;
import com.endereco.security.JwtUsuarioFactory;
import com.endereco.service.UsuarioService;
import com.endereco.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LoggerUtil.logger.info("JwtUsuarioDetailsServiceImpl - loadUserByUsername");

        Optional<Usuario> user = userService.findByEmail(email);

        if (user.isPresent()) {
            return JwtUsuarioFactory.create(user.get());
        }

        throw new UsernameNotFoundException("Email não encontrado.");
    }
}