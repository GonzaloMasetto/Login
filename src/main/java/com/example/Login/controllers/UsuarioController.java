package com.example.Login.controllers;

import com.example.Login.entities.Usuario;
import com.example.Login.repositories.UsuarioRepository;
import com.example.Login.services.UsuarioService;
import com.example.Login.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "*")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl> {

    @Autowired
    UsuarioService svcUsuario;

    UsuarioRepository usuarioRepository;


    @GetMapping("/UsuarioController")
    public String formularioProducto(ModelMap modelo){

        try{
            return "bien";
        }catch(Exception e){
            modelo.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping(value = "/registro")
    public String registro(@RequestParam String nombre, @RequestParam String usuario, @RequestParam String mail, @RequestParam String contrasena,
                           @RequestParam String usuariodos, @RequestParam String maildos,
                           @RequestParam String usuariotres, @RequestParam String mailtres,
                           @RequestParam String usuariocuatro, @RequestParam String mailcuatro,
                           @RequestParam String usuariocinco, @RequestParam String mailcinco,
                           @RequestParam String usuarioseis, @RequestParam String mailseis,
                           @RequestParam String usuariosiete, @RequestParam String mailsiete,
                           @RequestParam String usuarioocho, @RequestParam String mailocho,
                           @RequestParam String usuarionueve, @RequestParam String mailnueve,
                           ModelMap modelo){
        try {


            if (!(usuariodos.equals(""))){

                svcUsuario.registrar(nombre, usuariodos, maildos, contrasena);
                svcUsuario.enviarMail(maildos);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariotres.equals("")))
            {
                svcUsuario.registrar(nombre, usuariotres, mailtres, contrasena);
                svcUsuario.enviarMail(mailtres);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariocuatro.equals("")))
            {
                svcUsuario.registrar(nombre, usuariocuatro, mailcuatro, contrasena);
                svcUsuario.enviarMail(mailcuatro);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariocinco.equals("")))
            {
                svcUsuario.registrar(nombre, usuariocinco, mailcinco, contrasena);
                svcUsuario.enviarMail(mailcinco);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuarioseis.equals("")))
            {
                svcUsuario.registrar(nombre, usuarioseis, mailseis, contrasena);
                svcUsuario.enviarMail(mailseis);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariosiete.equals("")))
            {
                svcUsuario.registrar(nombre, usuariosiete, mailsiete, contrasena);
                svcUsuario.enviarMail(mailsiete);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuarioocho.equals("")))
            {
                svcUsuario.registrar(nombre, usuarioocho, mailocho, contrasena);
                svcUsuario.enviarMail(mailocho);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariosiete.equals("")))
            {
                svcUsuario.registrar(nombre, usuarionueve, mailnueve, contrasena);
                svcUsuario.enviarMail(mailnueve);
                modelo.put("exito", "Registrado correctamente");
            }
            svcUsuario.registrar(nombre, usuario, mail, contrasena);
            svcUsuario.enviarMail(mail);
            modelo.put("exito", "Registrado correctamente");
            return  "hecho";

        }catch(Exception e){
            modelo.put("error", e.getMessage());
            return "error";
        }
    }


}
