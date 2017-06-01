/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;

public class TipoComida {
    public static ArrayList<Restaurante> listaRestaurantes= new ArrayList<>();
    private String nombreTipoComida;
    private int idTipoComida;

    public TipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
       
    }

    public static ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    public static void setListaRestaurantes(ArrayList<Restaurante> listaRestaurantes) {
        TipoComida.listaRestaurantes = listaRestaurantes;
    }

    public int getIdTipoComida() {
        return idTipoComida;
    }

    public void setIdTipoComida(int idTipoComida) {
        this.idTipoComida = idTipoComida;
    }

    public TipoComida() {
    }

    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }
    
}
