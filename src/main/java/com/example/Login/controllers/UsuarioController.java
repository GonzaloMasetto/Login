package com.example.Login.controllers;

import com.example.Login.entities.Grupo;
import com.example.Login.entities.Usuario;
import com.example.Login.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;





@RequestMapping("/usuario")
@Controller
public class UsuarioController{
    @Autowired
    UsuarioService svcUsuario;
    String provincia;

    @PostMapping(value = "/registro")
    public String registro(@RequestParam String usuario, @RequestParam String mail, @RequestParam String contrasena, @RequestParam String pais,
                           String provinciaAR, String provinciaCH, String provinciaOTRO,
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

            svcUsuario.registrar(usuario, mail, contrasena,true, grupo, pais, provincia);
            if (!(usuariodos.equals(""))){

                svcUsuario.registrar(usuariodos, maildos, contrasena, false, grupo, pais, provincia);
                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariotres.equals("")))
            {
                svcUsuario.registrar(usuariotres, mailtres, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariocuatro.equals("")))
            {
                svcUsuario.registrar(usuariocuatro, mailcuatro, contrasena, false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariocinco.equals("")))
            {
                svcUsuario.registrar(usuariocinco, mailcinco, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuarioseis.equals("")))
            {
                svcUsuario.registrar(usuarioseis, mailseis, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariosiete.equals("")))
            {
                svcUsuario.registrar(usuariosiete, mailsiete, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuarioocho.equals("")))
            {
                svcUsuario.registrar(usuarioocho, mailocho, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (!(usuariosiete.equals("")))
            {
                svcUsuario.registrar(usuarionueve, mailnueve, contrasena,false, grupo, pais, provincia);

                modelo.put("exito", "Registrado correctamente");
            }
            if (usuariodos.equals("")){
                svcUsuario.mensajeCliente(usuario,false,mail+"@gmail.com");

            }else{
                svcUsuario.mensajeCliente(usuario,true,mail+"@gmail.com");
            }
            svcUsuario.mensajeLiberium(grupo,pageable);
            modelo.put("exito", "Registrado correctamente");
            return  "index";

        }catch(Exception e){
            modelo.put("error", e.getMessage());
            return "error";
        }
    }


}
