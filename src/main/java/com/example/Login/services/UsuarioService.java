package com.example.Login.services;

import com.example.Login.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends BaseService<Usuario, Long>, UserDetailsService {
    void validar(String usuario, String email, String contrasena, String nombre) throws Exception;

    void registrar(String usuario, String email, String contrasena, String nombre) throws Exception;

    public void enviarMail(String mailTo) throws Exception;
}
