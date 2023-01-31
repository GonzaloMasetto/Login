package com.example.Login.controllers;


import com.example.Login.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping(value = {"/", "/index"})
    public String index() {

            return "index";
    }
    @GetMapping("/condiciones")
    public String condiciones() {
        return "condiciones";
    }

}
