package Servlet;


import BaseDeDatos.Conexion;
import Datos.Restaurante;
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
    public static ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public static HttpServletRequest request;
    public static HttpServletResponse response;
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String nombre = request.getParameter("nombreRestaurante");
        String descripcion = request.getParameter("descripcion");
        String direccion = request.getParameter("direccion");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        int horaInicio = Integer.parseInt(request.getParameter("horaInicio"));
        int horaFin = Integer.parseInt(request.getParameter("horaFin"));
        String horario = request.getParameter("horario");
        String tipoComida = request.getParameter("tipoComida");
        restaurante = new Restaurante(nombre, direccion, telefono, horaInicio, horaFin, horario, tipoComida,descripcion);
        RequestDispatcher dispacher = request.getRequestDispatcher("agregarImagenRestaurante.jsp");
        dispacher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}