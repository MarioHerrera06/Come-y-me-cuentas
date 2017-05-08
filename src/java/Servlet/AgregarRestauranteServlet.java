package Servlet;


import BaseDeDatos.Conexion;
import Datos.RangoHora;
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
public static TipoComida tipocomida;
public static RangoHora rangoHora;
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
        String horario = request.getParameter("horario");
        tipocomida= new TipoComida(request.getParameter("tipoComida"));
        rangoHora= new RangoHora(Integer.parseInt(request.getParameter("horaInicio")),Integer.parseInt(request.getParameter("horaFin")));
        
        conexion.agregarTipoComida(tipocomida);
        conexion.agregarRangoHora(rangoHora);
        TipoComida consultaTipoComida = conexion.consultaTipoComida(request.getParameter("tipoComida"));
        RangoHora consultaRangoHora= conexion.consultaRangoHora(String.valueOf(rangoHora.getHoraInicio()));
        restaurante = new Restaurante(nombre, direccion, telefono, consultaRangoHora.getHoraInicio(), consultaRangoHora.getHoraFin(), horario, consultaTipoComida.getNombreTipoComida(),descripcion);
        RequestDispatcher dispacher = request.getRequestDispatcher("agregarImagenRestaurante.jsp");
        dispacher.forward(request, response);
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
