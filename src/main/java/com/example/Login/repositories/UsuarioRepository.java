package com.example.Login.repositories;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
    public Usuario searchByMail(@Param("mail") String mail);

    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    public Usuario searchByUsuario(@Param("usuario") String usuario);

    @Query(value = "SELECT * FROM usuarios WHERE usuarios.grupo LIKE %:grupoid%",
            countQuery = "SELECT count(*) FROM productos",
            nativeQuery = true)
    Page<Usuario> searchByGrupo(@Param("grupoid")long grupoid, Pageable pageable);

    Usuario findByUsuario(String username);

    Usuario findByMail(String mail);


}

