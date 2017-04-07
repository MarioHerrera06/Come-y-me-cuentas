/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.Date;

/**
 *
 * @author Valentina
 */
public class Comentario {
    private Usuario usuario;
    private Restaurante restaurante;
    private Date fecha ;   
    private String textoComentario;

    public Comentario(Usuario usuario, Restaurante restaurante, Date fecha, String textoComentario) {
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.textoComentario = textoComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }
    

    
    
    
}
