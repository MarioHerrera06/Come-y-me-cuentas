/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Valentina
 */
public class Comentario {
   // private Usuario usuario;
    private String nombreRestaurante;
    private String fecha ;   
    private String textoComentario;

    public Comentario(String restaurante, String fecha, String textoComentario) {
        this.nombreRestaurante = restaurante;
        this.fecha = fecha;
        this.textoComentario = textoComentario;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

   
    

    
    
    
}
