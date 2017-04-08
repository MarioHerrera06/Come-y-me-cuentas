package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Valentina
 */
@WebServlet(urlPatterns = {"/AgregarRestauranteServlet"})
public class AgregarRestauranteServlet extends HttpServlet {

            public static ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
    	
     public static HttpServletRequest request;
     public static HttpServletResponse response;
     Conexion conexion = new Conexion();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        String nombre = request.getParameter("nombreRestaurante");
        String direccion = request.getParameter("direccion");
        int telefono = Integer.parseInt(request.getParameter("telefono"));
        String horario = request.getParameter("horario");
        Long tipoComida =  Long.parseLong(request.getParameter("tipoComida"));
        Restaurante restaurante = new Restaurante(nombre,direccion,telefono);
        conexion.agregarRestaurante(restaurante);
          RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
