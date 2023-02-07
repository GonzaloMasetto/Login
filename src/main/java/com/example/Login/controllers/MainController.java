package com.example.Login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
    @GetMapping("/condiciones")
    public String condiciones() {
        return "condiciones";
    }

}
