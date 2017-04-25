package Servlet;



import BaseDeDatos.Conexion;
import Datos.RangoHora;
import Datos.Restaurante;
import Datos.TipoComida;
import java.io.IOException;
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
    Conexion conexion = new Conexion();
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String nombre = request.getParameter("nombreRestaurante");
        String descripcion = request.getParameter("descripcion");
        String direccion = request.getParameter("direccion");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        RangoHora rangoHora=new RangoHora(Integer.parseInt(request.getParameter("horaInicio")), Integer.parseInt(request.getParameter("horaFin")));
        String horario = request.getParameter("horario");
        TipoComida tipoComida= new TipoComida(request.getParameter("tipoComida"));        
        restaurante = new Restaurante(nombre, direccion, telefono, rangoHora.getHoraInicio(), rangoHora.getHoraFin(), horario, tipoComida.getNombreTipoComida(),descripcion);
        
        conexion.agregarRestauranteRangoHora(restaurante);
        conexion.agregarRestauranteTipoComida(restaurante);
        conexion.agregarRestaurante(restaurante);
        RequestDispatcher dispacher = request.getRequestDispatcher("restaurantesNuevos.jsp");
        dispacher.forward(request, response);
    }

   
}
