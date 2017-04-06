package Datos;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valentina
 */
public class Usuario {
    private String nombre;
    private String apellidos;    
    private String celular;
    private String correo;
    private String nomUsuario;
    private String contraseña;

     public static ArrayList<Comentario> listaComentarios= new ArrayList<>();
     public static ArrayList<Calificacion> listaCalificacion= new ArrayList<>();
     
    public Usuario(String nombre, String apellidos, String nomUsuario, String celular, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nomUsuario = nomUsuario;
        this.celular = celular;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuairo) {
        this.nomUsuario= nomUsuairo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}