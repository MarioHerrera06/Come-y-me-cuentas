/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;

public class Restaurante {

    private int id;
    private String nombre;
    private String direccion;
    private int telefono;
    private int horaInicio;
    private int horaFin;
    private String horario;
    private String tipoComida;
    
    public static ArrayList<Calificacion> listaCalificacion;
    public static ArrayList<Comentario> listaComentarios;

    public Restaurante(String nombre, String direccion, int telefono, int horaInicio, int horaFin, String horario,String tipoComida) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horario = horario;
        this.tipoComida=tipoComida;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
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
