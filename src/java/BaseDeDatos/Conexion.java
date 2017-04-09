
package BaseDeDatos;

import Datos.Restaurante;
import Servlet.AgregarRestauranteServlet;
import static Servlet.AgregarRestauranteServlet.listaRestaurantes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    
    
public void agregarRestaurante(Restaurante restaurante) throws FileNotFoundException, IOException {
   try{ 
    agregar = conexion.prepareStatement("insert into restaurante (nom_restaurante,descripcion,direccion,telefono,hora_inicio,hora_fin,horario,tipo_comida) values(?,?,?,?,?,?,?,?)");
 
    agregar.setString(1, restaurante.getNombre());
    agregar.setString(2, restaurante.getDescripcion());
    agregar.setString(3, restaurante.getDireccion());
    agregar.setLong(4, restaurante.getTelefono());
    agregar.setLong(5, restaurante.getHoraInicio());
    agregar.setLong(6, restaurante.getHoraFin());
    agregar.setString(7, restaurante.getHorario());
    agregar.setString(8, restaurante.getTipoComida());
    agregar.executeUpdate();
    agregar.close();
    

       System.out.println("se agrego");
   }catch(SQLException ex){
       
       System.out.println("No se pudo agregar el restaurante");
   } 
   
}
    
public ArrayList mostrarRestaurante() {
     ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    try{
   
    resultado = statement.executeQuery("select * from  RESTAURANTE");
    while(resultado.next()){
        
    Restaurante res = new Restaurante();
    res.setId(resultado.getInt(1));
    res.setNombre(resultado.getString(2));
    res.setDescripcion(resultado.getString(3));
    res.setDireccion(resultado.getString(4));
    res.setTelefono(resultado.getInt(5));
    res.setHorario(resultado.getString(8));
    res.setHoraInicio(resultado.getInt(6));
    res.setHoraFin(resultado.getInt(7));
    res.setTipoComida(resultado.getString(9));
    
    listaRestaurantes.add(res);
         
    }
    resultado.close();
    }catch(SQLException ex){
        System.out.println("paila No se pudo con los restaurantes ");
    }
    return listaRestaurantes;
    
}    
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
