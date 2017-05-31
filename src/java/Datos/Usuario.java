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
    private int telefono;
    private String correo;
    private String nomUsuario;
    private String contraseña;
    private int tipoUsuario;

     public static ArrayList<Comentario> listaComentarios= new ArrayList<>();
     public static ArrayList<Calificacion> listaCalificacion= new ArrayList<>();
     
     public Usuario(){
         
     }
    public Usuario(String nombre, String apellidos, String nomUsuario, int telefono, String correo, String contraseña, int tipoUsuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nomUsuario = nomUsuario;
        this.telefono=telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int  tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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