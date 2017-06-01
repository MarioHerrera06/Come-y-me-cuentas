package Servlet;


import BaseDeDatos.Conexion;
import Datos.Restaurante;
import Datos.TipoComida;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/AgregarRestauranteServlet"})
public class AgregarRestauranteServlet extends HttpServlet {
public static Restaurante restaurante;
public static TipoComida tipoComida2;
Conexion conexion = new Conexion();
    public static ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public static HttpServletRequest request;
    public static HttpServletResponse response;
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombreRestaurante");
        String descripcion = request.getParameter("descripcion");
        String direccion = request.getParameter("direccion");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        int horaInicio = Integer.parseInt(request.getParameter("horaInicio"));
        int horaFin = Integer.parseInt(request.getParameter("horaFin"));       
        String horario = request.getParameter("horario");
        String tipoComida = request.getParameter("tipoComida");
        tipoComida2= new TipoComida(tipoComida);
        conexion.agregarTipoComida(tipoComida2);
        int idTipoComida= conexion.buscarComidaPorNombre(tipoComida);
        tipoComida2.setIdTipoComida(idTipoComida);
         
        restaurante = new Restaurante(nombre, direccion, telefono, horaInicio, horaFin, horario, idTipoComida,descripcion);
        RequestDispatcher dispacher = request.getRequestDispatcher("agregarImagenRestaurante.jsp");
        dispacher.forward(request, response);
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
