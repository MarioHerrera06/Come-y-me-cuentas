/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.awt.Image;
import java.io.InputStream;
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
    private String descripcion;
    private String  imagen;
    

    public static ArrayList<Comentario> listaComentarios;

    public Restaurante(){}
    public Restaurante(String nombre, String direccion, int telefono, int horaInicio, int horaFin, String horario,String tipoComida,String descripcion
    ) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.horario = horario;
        this.tipoComida=tipoComida;
        this.descripcion = descripcion;
       
       
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public String getHoraInicioClasificar(){
        if(horaInicio<=12){
            return horaInicio + ":00 am";
        }else{
        
        return horaInicio + ":00 pm";
        }
    }
    
    public String getHoraFinClasificar(){
        if(horaFin>12){
            return horaFin + ":00 pm";
        }
        else {
            return horaFin + ":00 am";
        }
        
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
