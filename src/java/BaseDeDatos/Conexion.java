package BaseDeDatos;

import Datos.Comentario;
import Datos.Restaurante;
import Datos.TipoComida;
import Datos.Usuario;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public void agregarRestaurante(Restaurante restaurante) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into restaurante (nom_restaurante,descripcion,direccion,telefono,hora_inicio,hora_fin,horario,cod_tipo_comida,imagen) values(?,?,?,?,?,?,?,?,?)");
            agregar.setString(1, restaurante.getNombre());
            agregar.setString(2, restaurante.getDescripcion());
            agregar.setString(3, restaurante.getDireccion());
            agregar.setLong(4, restaurante.getTelefono());
            agregar.setLong(5, restaurante.getHoraInicio());
            agregar.setLong(6, restaurante.getHoraFin());
            agregar.setString(7, restaurante.getHorario());
            agregar.setInt(8, restaurante.getIdtipoComida());
            agregar.setString(9, restaurante.getImagen());
            agregar.executeUpdate();
            agregar.close();

            System.out.println("se agrego el restaurante");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el restaurante");
        }

    }

    public String buscarNombrePorId(int id) {
        System.out.println("Llego" + id);
        String nom = null;
        try {
            resultado = statement.executeQuery("select nom_usuario from usuario where cod_usuario='" + id + "'");
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
            agregar = conexion.prepareStatement("insert into comentario (cod_usuario,cod_restaurante,d_comentario,fecha,cod_tipoComentario) values (?,?,?,?,?)");
            agregar.setInt(1, comentarios.getUsuario());
            agregar.setInt(2, comentarios.getCodRes());
            agregar.setString(3, comentarios.getTextoComentario());
            agregar.setString(4, comentarios.getFecha());
            agregar.setInt(5, comentarios.getCodTipo());

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
                res.setIdtipoComida(resultado.getInt(9));
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
                res.setIdtipoComida(resultado.getInt(9));
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

            resultado = statement.executeQuery("select cod_usuario,d_comentario,fecha,cod_tipoComentario from  COMENTARIO where cod_restaurante=" + id);
            while (resultado.next()) {

                Comentario com = new Comentario();
                com.setUsuario(resultado.getInt(1));
                com.setTextoComentario(resultado.getString(2));
                com.setFecha(resultado.getString(3));
                com.setCodTipo(resultado.getInt(4));
                listaComentarios.add(com);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaComentarios;

    }

    public ArrayList mostrarRestauranteRandom(int id) {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        try {

            resultado = statement.executeQuery("SELECT * FROM RESTAURANTE WHERE COD_TIPO_COMIDA=" + id);
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
                res.setIdtipoComida(resultado.getInt(9));
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
                res.setIdtipoComida(resultado.getInt(9));
                res.setImagen(resultado.getString(10));

                listaRestaurantes.add(res);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaRestaurantes;

    }

      public void agregarTipoComida(TipoComida comida) throws FileNotFoundException, IOException {
        try {
            agregar = conexion.prepareStatement("insert into tipoComida (nom_tipo_comida) values (?)");
            agregar.setString(1,comida.getNombreTipoComida());
            agregar.executeUpdate();
            agregar.close();
            System.out.println("se agrego el tipo de comida");
        } catch (SQLException ex) {

            System.out.println("No se pudo agregar el tipo de comida");
        }
    }
      
       public int buscarComidaPorNombre(String comida) {
        System.out.println("Llego" + comida);
        int nom = 0;
        try {
            resultado = statement.executeQuery("select cod_tipo_comida from tipoComida where nom_tipo_comida=" + comida );
            while (resultado.next()) {
                nom = resultado.getInt(1);

                System.out.println("aqui tipoComida:" + nom);
            }
            resultado.close();
            System.out.println("Se encontro el tipoComida");
        } catch (SQLException ex) {

            System.out.println("No se encontro el nombre");
        }

        return nom;
    }

       
       
       
        public ArrayList mostrarTiposComida(int id) {
        ArrayList<TipoComida> listaTipoComida = new ArrayList<>();
        try {

            resultado = statement.executeQuery("select cod_tipo_comida,nom_tipo_comida from  tipoComida where cod_tipo_comida=" + id);
            while (resultado.next()) {

                TipoComida com = new TipoComida();
                com.setIdTipoComida(resultado.getInt(1));
                com.setNombreTipoComida(resultado.getString(2));
                
                listaTipoComida.add(com);

            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaTipoComida;

    }
        
        public ArrayList mostrarTipoComida() {
        ArrayList<TipoComida> listaTipoComida = new ArrayList<>();
        try {
            resultado = statement.executeQuery("select * from  TipoComida");
            while (resultado.next()) {
                TipoComida res = new TipoComida();
                res.setIdTipoComida(resultado.getInt(1));
                res.setNombreTipoComida(resultado.getString(2));
               
                listaTipoComida.add(res);
            }
            resultado.close();
        } catch (SQLException ex) {

        }
        return listaTipoComida;

    }
}
