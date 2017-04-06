/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;

/**
 *
 * @author Ingenieria
 */
public class TipoComida {
    public static ArrayList<Restaurante> listaRestaurantes= new ArrayList<>();
    private String nombreTipoComida;

    public TipoComida() {
    }

    public String getNombreTipoComida() {
        return nombreTipoComida;
    }

    public void setNombreTipoComida(String nombreTipoComida) {
        this.nombreTipoComida = nombreTipoComida;
    }
    
}
