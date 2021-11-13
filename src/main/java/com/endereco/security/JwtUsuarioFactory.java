package com.endereco.security;

import com.endereco.domain.entity.Usuario;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class JwtUsuarioFactory {

    public static JwtUsuario create(Usuario usuario) {
        return new JwtUsuario(usuario.getId(), usuario.getEmail(), usuario.getSenha(), createGrantedAuthorities());
    }

    private static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return authorities;
    }

}