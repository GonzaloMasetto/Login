package com.example.Login.controllers;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;
import com.example.Login.repositories.BaseRepository;
import com.example.Login.repositories.UsuarioRepository;
import com.example.Login.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;



@Controller
public class UsuarioController{
    @Autowired
    UsuarioService svcUsuario;
    String provincia;
    private int exitoRegistro = 0;
    private int cantidadMiembros = 0;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "usuario/registro")
    public String registro(@RequestParam String usuario, @RequestParam String mail, @RequestParam String contrasena, @RequestParam String pais,
                           String provinciaAR, String provinciaCH, String provinciaOTRO,
                           String miembros,
                           @RequestParam String usuariodos, @RequestParam String maildos,
                           @RequestParam String usuariotres, @RequestParam String mailtres,
                           @RequestParam String usuariocuatro, @RequestParam String mailcuatro,
                           @RequestParam String usuariocinco, @RequestParam String mailcinco,
                           @RequestParam String usuarioseis, @RequestParam String mailseis,
                           @RequestParam String usuariosiete, @RequestParam String mailsiete,
                           @RequestParam String usuarioocho, @RequestParam String mailocho,
                           @RequestParam String usuarionueve, @RequestParam String mailnueve,
                           ModelMap modelo, Pageable pageable){

        try {
            Grupo grupo = new Grupo();

            if (pais.equals("AR")){
                this.provincia = provinciaAR;
            } else if (pais.equals("CL")) {
                this.provincia = provinciaCH;
            }else {
                this.provincia = provinciaOTRO;
            }
            modelo.addAttribute("miembros",miembros);
            modelo.addAttribute("usuarioGuardado1",usuario);
            modelo.addAttribute("gmailGuardado1",mail);
            modelo.addAttribute("usuarioGuardado2",usuariodos);
            modelo.addAttribute("gmailGuardado2",maildos);
            modelo.addAttribute("usuarioGuardado3",usuariotres);
            modelo.addAttribute("gmailGuardado3",mailtres);
            modelo.addAttribute("usuarioGuardado4",usuariocuatro);
            modelo.addAttribute("gmailGuardado4",mailcuatro);
            modelo.addAttribute("usuarioGuardado5",usuariocinco);
            modelo.addAttribute("gmailGuardado5",mailcinco);
            modelo.addAttribute("usuarioGuardado6",usuarioseis);
            modelo.addAttribute("gmailGuardado6",mailseis);
            modelo.addAttribute("usuarioGuardado7",usuariosiete);
            modelo.addAttribute("gmailGuardado7",mailsiete);
            modelo.addAttribute("usuarioGuardado8",usuarioocho);
            modelo.addAttribute("gmailGuardado8",mailocho);
            modelo.addAttribute("usuarioGuardado9",usuarionueve);
            modelo.addAttribute("gmailGuardado9",mailnueve);

            if (miembros.equals("one")){
                this.exitoRegistro = 1;
            } else if (miembros.equals("two")) {
                this.exitoRegistro = 2;
            } else if (miembros.equals("three")) {
                this.exitoRegistro = 3;
            } else if (miembros.equals("four")) {
                this.exitoRegistro = 4;
            } else if (miembros.equals("five")) {
                this.exitoRegistro = 5;
            }else if (miembros.equals("six")) {
                this.exitoRegistro = 6;
            } else if (miembros.equals("seven")) {
                this.exitoRegistro = 7;
            }else if (miembros.equals("eight")) {
                this.exitoRegistro = 8;
            }else if (miembros.equals("nine")){
                this.exitoRegistro = 9;
            }

            if (!(usuariodos.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuariodos);
                Usuario usermail = usuarioRepository.findByMail(maildos+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail2","El usuario: " +usuariodos + " y el Gmail: "+ maildos + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                    }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail2", "El gmail: "+maildos +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                    }
                else if (user != null){
                    modelo.addAttribute("errorUsuario2", "El usuario: "+usuariodos +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }

            if (!(usuariotres.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuariotres);
                Usuario usermail = usuarioRepository.findByMail(mailtres+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail3","El usuario: " +usuariotres + " y el Gmail: "+ mailtres + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail3", "El gmail: "+mailtres +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario3", "El usuario: "+usuariotres +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuariocuatro.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuariocuatro);
                Usuario usermail = usuarioRepository.findByMail(mailcuatro+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail4","El usuario: " +usuariocuatro + " y el Gmail: "+ mailcuatro + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail4", "El gmail: "+mailcuatro +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario4", "El usuario: "+usuariocuatro +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuariocinco.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuariocinco);
                Usuario usermail = usuarioRepository.findByMail(mailcinco+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail5","El usuario: " +usuariocinco + " y el Gmail: "+ mailcinco + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail5", "El gmail: "+mailcinco +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario5", "El usuario: "+usuariocinco +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuarioseis.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuarioseis);
                Usuario usermail = usuarioRepository.findByMail(mailseis+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail6","El usuario: " +usuarioseis + " y el Gmail: "+ mailseis + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail6", "El gmail: "+mailseis +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario6", "El usuario: "+usuarioseis +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuariosiete.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuariosiete);
                Usuario usermail = usuarioRepository.findByMail(mailsiete+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail7","El usuario: " +usuariosiete + " y el Gmail: "+ mailsiete + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail7", "El gmail: "+mailsiete +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario7", "El usuario: "+usuariosiete +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuarioocho.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuarioocho);
                Usuario usermail = usuarioRepository.findByMail(mailocho+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail8","El usuario: " +usuarioocho + " y el Gmail: "+ mailocho + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail8", "El gmail: "+mailocho +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario8", "El usuario: "+usuarioocho +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            if (!(usuarionueve.equals("")))
            {
                Usuario user = usuarioRepository.findByUsuario(usuarionueve);
                Usuario usermail = usuarioRepository.findByMail(mailnueve+"@gmail.com");

                if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail9","El usuario: " +usuarionueve + " y el Gmail: "+ mailnueve + "@gmail.com Ya están registrados en el sistema, Porfavor ingrese otros");
                }
                else if (usermail!= null){
                    modelo.addAttribute("errorGmail9", "El gmail: "+mailnueve +"@gmail.com Ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                    modelo.addAttribute("errorUsuario9", "El usuario: "+usuarionueve +" Ya está registrado en el sistema, Porfavor ingrese otro");

                } else {
                    this.cantidadMiembros += 1;
                }
            }
            Usuario user = usuarioRepository.findByUsuario(usuario);
            Usuario usermail = usuarioRepository.findByMail(mail+"@gmail.com");

            if (user != null && usermail != null){
                    modelo.addAttribute("errorUsuarioGmail1","El usuario: " +usuario + "y el Gmail: "+ mail + "@gmail.com ya están registrados en el sistema, Porfavor ingrese otros");
           }
                else if (usermail != null){
                        modelo.addAttribute("errorGmail1", "El gmail: "+mail +"@gmail.com ya está registrado en el sistema, Porfavor ingrese otro");
                }
                else if (user != null){
                        modelo.addAttribute("errorUsuario1", "El usuario: "+usuario +" ya está registrado en el sistema, Porfavor ingrese otro");
                }
            else {
                this.cantidadMiembros += 1;
            }

           if (exitoRegistro == cantidadMiembros){
                modelo.addAttribute("Exito", "¡Se ha registrado con Exito!");
                svcUsuario.registrar(usuario, mail, contrasena, true, grupo, pais, provincia);
                if (exitoRegistro >= 2){
                    svcUsuario.registrar(usuariodos, maildos, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 3){
                    svcUsuario.registrar(usuariotres, mailtres, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 4){
                    svcUsuario.registrar(usuariocuatro, mailcuatro, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 5){
                    svcUsuario.registrar(usuariocinco, mailcinco, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 6){
                    svcUsuario.registrar(usuarioseis, mailseis, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 7){
                    svcUsuario.registrar(usuariosiete, mailsiete, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 8){
                    svcUsuario.registrar(usuarioocho, mailocho, contrasena, false, grupo, pais, provincia);
                }
                if (exitoRegistro >= 9){
                    svcUsuario.registrar(usuarionueve, mailnueve, contrasena, false, grupo, pais, provincia);
                }
            }


            if (usuariodos.equals("")){
                svcUsuario.mensajeCliente(usuario,false,mail+"@gmail.com");

            }else{
                svcUsuario.mensajeCliente(usuario,true,mail+"@gmail.com");
            }
            svcUsuario.mensajeLiberium(grupo,pageable);

            cantidadMiembros = 0;
            return  "index";

        }catch(Exception e){
            modelo.put("error", e.getMessage());
            return "error";
        }
    }


}
