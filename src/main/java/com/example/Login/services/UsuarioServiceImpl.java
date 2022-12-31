package com.example.Login.services;

import com.example.Login.entities.Usuario;
import com.example.Login.repositories.BaseRepository;
import com.example.Login.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;


    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public void validar(String usuario, String email, String contrasena, String nombre) throws Exception{

        if (nombre == null || nombre.isEmpty()){
            throw new Exception("el nombre no puede ser nulo o estar vacio");
        }
        if (nombre == null || usuario.isEmpty()){
            throw new Exception("el nombre no puede ser nulo o estar vacio");
        }
        if (contrasena == null || contrasena.isEmpty()){
            throw new Exception("el contrasena no puede ser nulo o estar vacio");
        }
        if (email == null ||  email.isEmpty()){
            throw new Exception("el mail no puede ser nulo o estar vacio");
        }
        Usuario usuarioRegistrado = usuarioRepository.searchByMail(email);
        if (usuarioRegistrado != null){
            throw new Exception("El mail ya esta registrado");
        }
    }

    @Override
    public void registrar(String nombre ,String usuarioNuevo, String mail, String contrasena) throws UsernameNotFoundException {

        Usuario usuarioRegistrado = usuarioRepository.searchByMail(mail);
        Usuario usuarioRegistrado2 = usuarioRepository.searchByUsuario(usuarioNuevo);
        if (usuarioRegistrado != null || usuarioRegistrado2 != null){
            throw new UsernameNotFoundException("Este usuario ya existe");
        }else {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setUsuario(usuarioNuevo);
            usuario.setMail(mail);
            usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
            usuarioRepository.save(usuario);
        }
    }


    public void enviarMail(String mailTo) throws Exception{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("gonzamasetto21@gmail.com");
        mail.setTo(mailTo);
        mail.setSubject("subject");
        mail.setText("blabla");
        mailSender.send(mail);
    }

    @Override
    public UserDetails loadUserByUsername(String usuarioNuevo) throws UsernameNotFoundException {

        Usuario usuarioRegistrado = usuarioRepository.searchByMail(usuarioNuevo);

        if (usuarioRegistrado != null){
            throw new UsernameNotFoundException("Este usuario ya existe");
        }else{

        }
        return null;
    }
}
