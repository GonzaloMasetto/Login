package com.example.Login.services;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;

import com.example.Login.repositories.BaseRepository;
import com.example.Login.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService, UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender emailsender;
    @Autowired
    private final TemplateEngine templateEngine = null;

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
    public void registrar(String usuarioNuevo, String mail, String contrasena, Boolean jefeEquipo, Grupo grupo, String pais, String provincia) throws UsernameNotFoundException {

        Usuario usuarioRegistrado = usuarioRepository.searchByMail(mail);
        Usuario usuarioRegistrado2 = usuarioRepository.searchByUsuario(usuarioNuevo);
        if (usuarioRegistrado != null || usuarioRegistrado2 != null){
            throw new UsernameNotFoundException("Este usuario ya existe");
        }else {
            Usuario usuario = new Usuario();
            usuario.setUsuario(usuarioNuevo);
            usuario.setMail(mail+"@gmail.com");
            usuario.setJefeEquipo(jefeEquipo);
            usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));
            usuario.setGrupo(grupo);
            usuario.setPais(pais);
            usuario.setProvincia(provincia);
            usuarioRepository.save(usuario);
        }
    }


    public void mensajeCliente(String nombre, Boolean grupo,String para){
        Context context = new Context();
        context.setVariable("grupo", grupo);
        context.setVariable("nombre", nombre);

        String htmlContent = templateEngine.process("mensajecliente", context);
        enviarCliente(htmlContent,para);
    }
    public void mensajeLiberium(Grupo grupo,Pageable pageable) throws Exception {
        Context context = new Context();
        Page<Usuario> usuarios = searchByGrupo(grupo.getId(),pageable);
        context.setVariable("usuarios", usuarios);
        String htmlContent = templateEngine.process("mensajeliberium", context);
        enviarLiberium(htmlContent,"gonzamasetto21@gmail.com");
        enviarLiberium(htmlContent,"luismasetto21@gmail.com");
        //enviarLiberium(htmlContent,"lucasgomezportillo@gmail.com");
        //enviarLiberium(htmlContent,"matias.neri@gmail.com");
    }
    public void enviarLiberium(String htmlContent,String para){

        new Thread(() -> {
            try {
                MimeMessage message = emailsender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
                helper.setFrom("contacto@liberium.com.ar");
                helper.setTo(para);
                helper.setSubject("Han creado una cuenta de prueba en Liberium");
                helper.setText(htmlContent, true);


                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlContent, "text/html");


                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                emailsender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void enviarCliente(String htmlContent,String para){

        new Thread(() -> {
            try {
                MimeMessage message = emailsender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
                helper.setFrom("contacto@liberium.com.ar");
                helper.setTo(para);
                helper.setSubject("Has creado una cuenta de prueba en Liberium");
                helper.setText(htmlContent, true);


                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(htmlContent, "text/html");

                //String urlArchivos = "C:/Users/Gonza/OneDrive/Documentos/TALLER PROGRAMACION/Login/src/main/resources/static/img";
                String urlArchivos = "/proyectos/liberium/Login/src/main/resources/static/img";
                String icono = urlArchivos + "/icono.png";
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.attachFile(icono);
                imagePart.setContentID("<icono>");
                imagePart.setDisposition(Part.INLINE);

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(imagePart);
                message.setContent(multipart);
                emailsender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
       return null;
    }

    @Override
    public Page<Usuario> searchByGrupo(long grupoid, Pageable pageable) throws Exception {
        try{
            Page<Usuario> usuarios = usuarioRepository.searchByGrupo(grupoid,pageable);
            return usuarios;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
