package com.example.Login.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario extends Base{

    @Column(name="nombre")
    private String nombre;


    @Column(name="mail")
    private String mail;

    @Column(name="usuario")
    private String usuario;

    @Column(name = "contrasena")
    private String contrasena;


}
