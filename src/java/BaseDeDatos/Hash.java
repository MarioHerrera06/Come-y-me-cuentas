/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

/**
 *
 * @author Valentina
 */
 import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
   private String mensaje;
    
    public Hash(String mensaje)
        {
        this.mensaje = mensaje;
        }


   
public static String resumen;


        public static String hash (String mensaje){ 
        String sumar = "El mensaje original";
        
        String cifrar = mensaje+sumar;
        byte[] bytesDelMensaje = cifrar.getBytes();

        MessageDigest resumenDelMensaje = null;
        try {
            resumenDelMensaje = MessageDigest.getInstance("MD5");
            byte[] bytesDelResumen = resumenDelMensaje.digest(bytesDelMensaje);

            BigInteger resumenNumero = new BigInteger(1, bytesDelResumen);
            resumen = resumenNumero.toString(16);
//            
//            System.out.println("Mensaje '" + mensaje + "' -> Hash MD5: " + resumen);
      
            
        } catch (NoSuchAlgorithmException e) {}
        return resumen;
        }
        
    public static String getResumen() {
        return resumen;
    }

    public static void setResumen(String resumen) {
        Hash.resumen = resumen;
    }
        

//    public static void main(String[] args) {
//
//        System.out.println(hash("1234"));
//    }
// 

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
    

