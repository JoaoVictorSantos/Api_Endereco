package com.endereco.util;

import com.endereco.domain.entity.Usuario;
import com.endereco.service.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioUtil {

    public static UsuarioService staticService;

    public UsuarioUtil(UsuarioService service){
        UsuarioUtil.staticService = service;
    }

    public static Long getAuthenticatedUserId() {
        try{
            Optional<Usuario> optionalUser = staticService.findByEmail(SecurityContextHolder.getContext()
                    .getAuthentication().getName());
            if(optionalUser.isPresent()){
                return optionalUser.get().getId();
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
}
