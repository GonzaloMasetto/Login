package com.example.Login.repositories;

import com.example.Login.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario searchByMail(@Param("mail") String mail);

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    public Usuario searchByUsuario(@Param("usuario") String usuario);
}

