package BaseDeDatos;

import Datos.Comentario;
import Datos.Restaurante;
import Datos.Usuario;
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

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto?user=root&password=1234");
            statement = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        } catch (SQLException exe) {
            System.out.println("No se pudo conectar");
        }

    }
    public void agregarUsuarioNormal(Usuario usuario) {
        try {
            agregar = conexion.prepareStatement("insert into usuario (nom_persona,apellido,telefono,correo,nom_usuario,contraseña) values (?,?,?,?,?,?)");
            agregar.setString(1, usuario.getNombre());
            agregar.setString(2, usuario.getApellidos());
            agregar.setLong(3, usuario.getTelefono());
            agregar.setString(4, usuario.getCorreo());
            agregar.setString(5, usuario.getNomUsuario());
            agregar.setString(6, usuario.getContraseña());
            agregar.executeUpdate();
            agregar.close();
            System.out.println("Se agrego usuario");
        }catch (SQLException ex) {

            System.out.println("No se pudo agregar el usuario");
        }
        
    }

//    public static void main(String[] args) {
//        Conexion c=new Conexion();
//        Usuario s=new  Usuario("vale", "lindarte", "lau", 33333, "vale@gmail.com", "2345");
//        c.agregarUsuario(s);
//    }
    public void agregarRestaurante(Restaurante restaurante) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into restaurante (nom_restaurante,descripcion,direccion,telefono,hora_inicio,hora_fin,horario,tipo_comida,imagen) values(?,?,?,?,?,?,?,?,?)");
            agregar.setString(1, restaurante.getNombre());
            agregar.setString(2, restaurante.getDescripcion());
            agregar.setString(3, restaurante.getDireccion());
            agregar.setLong(4, restaurante.getTelefono());
            agregar.setLong(5, restaurante.getHoraInicio());
            agregar.setLong(6, restaurante.getHoraFin());
            agregar.setString(7, restaurante.getHorario());
            agregar.setString(8, restaurante.getTipoComida());
            agregar.setString(9, restaurante.getImagen());
            agregar.executeUpdate();
            agregar.close();

            System.out.println("se agrego el restaurante");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el restaurante");
        }

    }
    public void agregarComentarios(Comentario comentarios)throws FileNotFoundException, IOException{
        try{
            agregar = conexion.prepareStatement("insert into comentario (cod_restaurante,d_comentario,fecha) values (?,?,?)");
            agregar.setString(1, comentarios.getNombreRestaurante());
            agregar.setString(3, comentarios.getTextoComentario());
            agregar.setString(4, comentarios.getFecha());
            agregar.executeUpdate();
            agregar.close();
              System.out.println("se agrego el comentario");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el comentario");
        }
    }
    
    public String buscarUsuarios(String usuario, String contra){
       
         try {
            String x= Hash.hash(contra);              
             resultado = statement.executeQuery("select nom_usuario, contraseña from USUARIO where nom_usuario='"+usuario+"'; ");
             String us = resultado.getString(0);
             String co = resultado.getString(1);
             System.out.println(us+co);
             System.out.println("se encontro");
             
         }catch (SQLException ex) {
             System.out.println("no se encontro");
        } 
         return "";
    }

    public ArrayList mostrarRestaurante() {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("select * from  RESTAURANTE");
            while (resultado.next()) {

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
                res.setImagen(resultado.getString(10));
                listaRestaurantes.add(res);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaRestaurantes;

    }

   
    
      public ArrayList mostrarRestauranteRandom(String tipoComida) {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("SELECT * FROM RESTAURANTE WHERE TIPO_COMIDA='"+tipoComida+"'");
            while (resultado.next()) {

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
                 res.setImagen(resultado.getString(10));

                listaRestaurantes.add(res);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaRestaurantes;

    }
      
       public ArrayList mostrarRestaurantesPredeterminados(String nombreRestaurante) {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("SELECT * FROM RESTAURANTE WHERE NOM_RESTAURANTE='"+nombreRestaurante+"'");
            while (resultado.next()) {

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
                 res.setImagen(resultado.getString(10));

                listaRestaurantes.add(res);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaRestaurantes;

    }

}
