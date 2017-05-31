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
    private int codRes;
    private String fecha ;   
    private String textoComentario;
    private int usuario;
    
    public Comentario(){
        
    }

    public Comentario( int usuario, int codRes,String textoComentario, String fecha) {
        this.codRes = codRes;
        this.fecha = fecha;
        this.textoComentario = textoComentario;
        this.usuario = usuario;
    }

    public int getCodRes() {
        return codRes;
    }

    public void setCodRes(int codRes) {
        this.codRes = codRes;
    }

   
    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
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
