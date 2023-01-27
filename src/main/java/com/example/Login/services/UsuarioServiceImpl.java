package com.example.Login.services;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;
import com.example.Login.enumerations.Rol;
import com.example.Login.repositories.BaseRepository;
import com.example.Login.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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
            usuario.setRol(Rol.CLIENTE);
            usuario.setGrupo(grupo);
            usuario.setPais(pais);
            usuario.setProvincia(provincia);
            usuarioRepository.save(usuario);
        }
    }


    public void mensajeCliente(String nombre, Boolean grupo){
        Context context = new Context();
        context.setVariable("grupo", grupo);
        context.setVariable("nombre", nombre);

        String htmlContent = templateEngine.process("mensajecliente", context);
        simpleTextMessage(htmlContent);
    }
    public void mensajeLiberium(Grupo grupo,Pageable pageable) throws Exception {
        Context context = new Context();
        Page<Usuario> usuarios = searchByGrupo(grupo.getId(),pageable);
        context.setVariable("usuarios", usuarios);
        String htmlContent = templateEngine.process("mensajeliberium", context);
        simpleTextMessage(htmlContent);
    }


    public void simpleTextMessage(String htmlContent){

        new Thread(() -> {
            String[] para = new String[]{"gonzamasetto21@gmail.com"};
            try {
                MimeMessage message = emailsender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
                helper.setFrom("luismasetto21@gmail.com");
                helper.setTo(para);
                helper.setSubject("Has creado una cuenta de prueba en Liberium");
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

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.searchByMail(mail);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getMail(), usuario.getContrasena(), permisos);
        } else {
            return null;
        }
    }
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
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
