/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Datos.Calificacion;
import Datos.Comentario;
import Datos.Restaurante;
import static Datos.Restaurante.listaComentarios;
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
@WebServlet(name = "AgregarCalificacionServlet", urlPatterns = {"/AgregarCalificacionServlet"})
public class AgregarCalificacionServlet extends HttpServlet {
    public static Calificacion calificacion;
    public static ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public static HttpServletRequest request;
    public static HttpServletResponse response;
//    String nombre, calificacion ;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.request = request;
//        this.response = response;
//        nombre = request.getParameter("elejirRestaurante");
//        
//        calificacion = new Calificacion(0, usuario, restaurante);
//        listaComentarios.add(comentario);
//        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
//        dispacher.forward(request, response);
//    }
//  
}
