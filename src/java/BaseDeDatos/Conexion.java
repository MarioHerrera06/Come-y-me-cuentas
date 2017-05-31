package BaseDeDatos;

import Datos.Comentario;
import Datos.Restaurante;
import Datos.Usuario;
import Servlet.AgregarRestauranteServlet;
import static Servlet.AgregarRestauranteServlet.listaRestaurantes;
import static Servlet.AgregarRestauranteServlet.request;
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
import javax.servlet.http.HttpSession;

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
            agregar = conexion.prepareStatement("insert into usuario (nom_persona,apellido,telefono,correo,nom_usuario,contraseña,cod_tipoUsuario) values (?,?,?,?,?,?,?)");

            agregar.setString(1, usuario.getNombre());
            agregar.setString(2, usuario.getApellidos());
            agregar.setLong(3, usuario.getTelefono());
            agregar.setString(4, usuario.getCorreo());
            agregar.setString(5, usuario.getNomUsuario());
            agregar.setString(6, usuario.getContraseña());
            agregar.setInt(7, usuario.getTipoUsuario());
            agregar.executeUpdate();
            agregar.close();
            System.out.println("Se agrego usuario");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el usuario");
        }

    }

    public int buscarIdU(String nombre) {
        int idU = 0;
        try {
            resultado = resultado = statement.executeQuery("select cod_usuario from usuario where nom_usuario='" + nombre + "'");
            while (resultado.next()) {
                idU = resultado.getInt(1);

               
            }
            resultado.close();

            System.out.println("Se encontro el tipo");
        } catch (SQLException ex) {

            System.out.println("No se encontro el tipo");
        }

        return idU;
    }

    public int buscarTipoUsuario(String nombre) {
        int tipoU = 0;
        try {
            resultado = resultado = statement.executeQuery("select cod_tipoUsuario from usuario where nom_usuario='" + nombre + "'");
            while (resultado.next()) {
                tipoU = resultado.getInt(1);

            }
            resultado.close();

            System.out.println("Se encontro el tipo");
        } catch (SQLException ex) {

            System.out.println("No se encontro el tipo");
        }

        return tipoU;
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
        

    public String buscarNombrePorId(int id) {
        System.out.println("Llego"+id);
        String nom = null;
        try {
            resultado  = statement.executeQuery("select nom_usuario from usuario where cod_usuario='" + id + "'");
            while (resultado.next()) {
                nom = resultado.getString(1);

                System.out.println("aqui usuario:" + nom);
            }
            resultado.close();
            System.out.println("Se encontro el nombre");
        } catch (SQLException ex) {

            System.out.println("No se encontro el nombre");
        }

        return nom;
    }

    public void agregarComentarios(Comentario comentarios) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into comentario (cod_usuario,cod_restaurante,d_comentario,fecha) values (?,?,?,?)");
            agregar.setInt(1, comentarios.getUsuario());
           // System.out.println("id usuario :" + comentarios.getUsuario());
            agregar.setInt(2, comentarios.getCodRes());
            //System.out.println("id res: " + comentarios.getCodRes());
            agregar.setString(3, comentarios.getTextoComentario());
            //System.out.println("text:" + comentarios.getTextoComentario());
            agregar.setString(4, comentarios.getFecha());
            //System.out.println("fecha:" + comentarios.getFecha());
            agregar.executeUpdate();
            agregar.close();
            System.out.println("se agrego el comentario");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el comentario");
        }
    }

    Usuario us = new Usuario();

    public String buscarUsuarios(String usuario, String contra) {

        String x = null;
        try {

            resultado = statement.executeQuery("select nom_usuario, contraseña from USUARIO where nom_usuario='" + usuario + "'" + "and contraseña='" + contra + "'");
            System.out.println(contra);
            while (resultado.next()) {
                String nom = resultado.getString(1);
                String cont = resultado.getString(2);

              //  System.out.println("aqui usuario:" + nom + " contraseña:" + cont);
               // System.out.println("se encontro");

                us.setNomUsuario(usuario);
                us.setContraseña(contra);
                x = "true";
            }
            resultado.close();
        } catch (SQLException ex) {
            System.out.println("no se encontro");
            x = "false";
        }
        return x;
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

    public ArrayList mostrarRestaurantePorId(int id) {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("select * from  RESTAURANTE where cod_restaurante=" + id);
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

    public ArrayList mostrarComentarios(int id) {
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        try {

            resultado = statement.executeQuery("select cod_usuario,d_comentario,fecha from  COMENTARIO where cod_restaurante=" + id);
            while (resultado.next()) {

                Comentario com = new Comentario();
                com.setUsuario(resultado.getInt(1));
                com.setTextoComentario(resultado.getString(2));
                com.setFecha(resultado.getString(3));
                listaComentarios.add(com);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaComentarios;

    }

    public ArrayList mostrarRestauranteRandom(String tipoComida) {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("SELECT * FROM RESTAURANTE WHERE TIPO_COMIDA='" + tipoComida + "'");
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

            resultado = statement.executeQuery("SELECT * FROM RESTAURANTE WHERE NOM_RESTAURANTE='" + nombreRestaurante + "'");
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
