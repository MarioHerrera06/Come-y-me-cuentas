package BaseDeDatos;

import Datos.Comentario;
import Datos.Horario;
import Datos.RangoHora;
import Datos.Restaurante;
import Datos.TipoComida;
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
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/proyecto?user=root&password=12345");
            statement = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        } catch (SQLException exe) {
            System.out.println("No se pudo conectar");
        }

    }
    
    public void agregarTipoComida(TipoComida tipoComida) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into TIPOCOMIDA (tipoComida) values(?)");
            agregar.setString(1, tipoComida.getNombreTipoComida());
            
            agregar.executeUpdate();
            agregar.close();

            System.out.println("se agrego");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el tipo de comida");
        }

    }
    
    
    public void agregarRangoHora(RangoHora rangohora) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into RANGOHORA (horaInicio,horaFin) values(?,?)");
            agregar.setLong(1, rangohora.getHoraInicio());
            agregar.setLong(2, rangohora.getHoraFin());
           
            agregar.executeUpdate();
            agregar.close();

            System.out.println("se agrego");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el restaurante");
        }

    }

    public void agregarRestaurante(Restaurante restaurante,TipoComida tipoComida, RangoHora rangoHora) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into restaurante (nom_restaurante,descripcion,direccion,telefono,cod_rangoHora,hora_inicio,hora_fin,horario,tipo_comida,cod_tipo_comida,imagen) values(?,?,?,?,?,?,?,?,?,?,?)");
            agregar.setString(1, restaurante.getNombre());
            agregar.setString(2, restaurante.getDescripcion());
            agregar.setString(3, restaurante.getDireccion());
            agregar.setLong(4, restaurante.getTelefono());
            agregar.setLong(5, rangoHora.getId());
            agregar.setLong(6, restaurante.getHoraInicio());
            agregar.setLong(7, restaurante.getHoraFin());
            agregar.setString(8, restaurante.getHorario());
            agregar.setString(9, restaurante.getTipoComida());
            agregar.setLong(10, tipoComida.getId());
            agregar.setString(11, restaurante.getImagen());
            agregar.executeUpdate();
            agregar.close();

            System.out.println("se agrego");
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
              System.out.println("se agrego");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el comentario");
        }
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
    
    public TipoComida consultaTipoComida(String tipocomida){
        try {

            resultado = statement.executeQuery("SELECT * FROM TIPOCOMIDA WHERE TIPOCOMIDA='"+tipocomida+"'");
            while (resultado.next()) {

                TipoComida res = new TipoComida();
                res.setId(resultado.getInt(1));
                res.setNombreTipoComida(resultado.getString(2));
              

             return res;

            }
            resultado.close();
        } catch (SQLException ex) {
            
        }
        return null;
        
    }

    
     public RangoHora consultaRangoHora(String horaInicio){
        try {

            resultado = statement.executeQuery("SELECT * FROM RANGOHORA WHERE horaInicio='"+horaInicio+"'");
            while (resultado.next()) {

                RangoHora res = new RangoHora();
                res.setId(resultado.getInt(1));
                res.setHoraInicio(Integer.parseInt(resultado.getString(2)));
                res.setHoraFin(Integer.parseInt(resultado.getString(3)));
              

            
                return res;
            }
            resultado.close();
        } catch (SQLException ex) {
            
        }
        return null;
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
