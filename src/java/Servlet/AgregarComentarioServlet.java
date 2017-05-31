
package Servlet;

import BaseDeDatos.Conexion;
import Datos.Comentario;
import Datos.Restaurante;
import static Datos.Restaurante.listaComentarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valentina
 */
@WebServlet(name = "AgregarComentarioServlet", urlPatterns = {"/AgregarComentarioServlet"})
public class AgregarComentarioServlet extends HttpServlet {

    public static Comentario comentario;
    public static Conexion con = new Conexion();

    public static HttpServletRequest request;
    public static HttpServletResponse response;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        int id = Integer.parseInt(request.getParameter("elejirRestaurante"));
        String comentarioHecho = request.getParameter("comentario");
        // fecha = request.getParameter("fecha");
        comentario = new Comentario(id, comentarioHecho);
        System.out.println(comentario.getId());
        System.out.println(comentario.getTextoComentario());

        con.agregarComentarios(comentario);
        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

}
