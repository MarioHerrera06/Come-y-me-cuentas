
package Datos;

import java.sql.Time;
import java.util.Date;


public class Comentario {
   
    private int idRestaurante;
    private String fecha ;   
    private String textoComentario;

    public Comentario(int restaurante, String fecha, String textoComentario) {
        this.idRestaurante = restaurante;
        this.fecha = fecha;
        this.textoComentario = textoComentario;
    }

    public Comentario(int id, String comentario) {
        this.idRestaurante=id;
        this.textoComentario=comentario;
    }

    public int getId() {
        return idRestaurante;
    }

    public void setNombreRestaurante(int id) {
        this.idRestaurante =id;
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
