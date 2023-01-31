package com.example.Login.services;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long>, UserDetailsService {
    void validar(String usuario, String email, String contrasena, String nombre) throws Exception;


    void registrar(String usuarioNuevo, String mail, String contrasena, Boolean jefeEquipo, Grupo grupo, String pais, String provincia) throws UsernameNotFoundException;

    Page<Usuario> searchByGrupo(long grupoid, Pageable pageable) throws Exception;
    public void mensajeLiberium(Grupo grupo,Pageable pageable) throws Exception;
    public void mensajeCliente(String nombre, Boolean grupo,String para);
}
