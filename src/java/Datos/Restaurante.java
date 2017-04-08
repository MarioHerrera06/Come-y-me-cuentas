/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;

/**
 *
 * @author Valentina
 */
public class Restaurante {
    
    private int id;
    private String nombre;
    private String direccion;
    private int telefono;
   // private Horario horario;
   // private TipoComida tipoComidaPrincipal;
    
    public static ArrayList<Calificacion> listaCalificacion;
    public static ArrayList<Comentario> listaComentarios;
    
   
    
    public Restaurante(String nombre, String direccion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
//        this.horario = horario;
//        this.tipoComidaPrincipal = tipoComidaPrincipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

//    public Horario getHorario() {
//        return horario;
//    }
//
//    public void setHorario(Horario horario) {
//        this.horario = horario;
//    }

//    public TipoComida getTipoComidaPrincipal() {
//        return tipoComidaPrincipal;
//    }
//
//    public void setTipoComidaPrincipal(TipoComida tipoComidaPrincipal) {
//        this.tipoComidaPrincipal = tipoComidaPrincipal;
//    }
//    
    
    
}
