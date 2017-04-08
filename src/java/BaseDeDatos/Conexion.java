/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Datos.Restaurante;
import Servlet.AgregarRestauranteServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Valentina
 */
public class Conexion {
     Connection conexion;
    Statement statement;
    ResultSet resultado;
    PreparedStatement agregar;

    public Conexion() {
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/proyecto?user=root&password=1234");
        statement = conexion.createStatement();
        }catch(ClassNotFoundException ex){
            System.out.println("Clase no encontrada");
        }catch(SQLException exe){
            System.out.println("No se pudo conectar");
        }
        
    }
    
    
public void agregarRestaurante(Restaurante restaurante) {
   try{ 
    agregar = conexion.prepareStatement("insert into restaurante (nom_restaurante,direccion,telefono) values(?,?,?)");
    agregar.setString(1, restaurante.getNombre());
    agregar.setString(2, restaurante.getDireccion());
    agregar.setLong(3, restaurante.getTelefono());
    agregar.executeUpdate();
    agregar.close();
       System.out.println("se agrego");
   }catch(SQLException ex){
       
       System.out.println("No se pudo agregar el restaurante");
   }
   
}
    
//public ArrayList mostrarRestaurante() throws SQLException{
//   AgregarRestauranteServlet.listaRestaurantes = new ArrayList<>();
//   
//    resultado = statement.executeQuery("select * from restaurante");
//    while(resultado.next()){
//        
//    Restaurante res = new Restaurante();
//    res.setId(resultado.getInt(1));
//    res.setNombre(resultado.getString(2));
//    res.setDireccion(resultado.getString(3));
//    res.setTelefono(resultado.getLong(4));
//    cont.setFijo(resultado.getInt(5));
//    cont.setCorreo(resultado.getString(6));
//    cont.setColor(resultado.getString(7));
//    
//        Servlet.listaContactos.add(cont);
//          
//    }
//    return listaContactos;
//    
//}    
//
//public contacto obtenerContacto(int x) {
//    
//    contacto cont = new contacto();
//
//        try {
//            resultado = statement.executeQuery("select * from contacto where id="+x);
//            while(resultado.next()){
//                
//                cont.setId(resultado.getInt(1));
//                cont.setNombre(resultado.getString(2));
//                cont.setApellidos(resultado.getString(3));
//                cont.setCelular(resultado.getLong(4));
//                cont.setFijo(resultado.getInt(5));
//                cont.setCorreo(resultado.getString(6));
//                cont.setColor(resultado.getString(7));
//                
//                
//                
//            }   } catch (SQLException ex) {
//            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    return cont;
//}
//
//public void actualizarContacto(contacto cont){
//        try {
//            agregar = conexion.prepareStatement("update contacto set nombre=?,apellidos=?,celular=?,fijo=?,correo=?,color=? where id=?");
//            agregar.setString(1, cont.getNombre());
//            agregar.setString(2, cont.getApellidos());
//            agregar.setLong(3, cont.getCelular());
//            agregar.setInt(4, cont.getFijo());
//            agregar.setString(5, cont.getCorreo());
//            agregar.setString(6, cont.getColor());
//            agregar.setInt(7, ServletDatos.x);
//            agregar.executeUpdate();
//            agregar.close();
//            System.out.println("el usuario a sido actualizado");
//        } catch (SQLException ex) {
//            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//}
//
//public void borrarContacto(int num){
//        try {
//            agregar = conexion.prepareStatement("delete from contacto where id ="+num);
//            agregar.executeUpdate();
//            agregar.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//}
}
