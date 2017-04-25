
package Datos;

import java.util.ArrayList;


public class TipoComida {
    public static ArrayList<Restaurante> listaRestaurantes= new ArrayList<>();
    private String nombreTipoComida;

    public TipoComida(String nombreComida) {
        this.nombreTipoComida=nombreComida;
    }

    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }
    
}
