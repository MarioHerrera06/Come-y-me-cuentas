/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Datos.Comentario;
import Datos.Restaurante;
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
    public static ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public static HttpServletRequest request;
    public static HttpServletResponse response;
    String nombre, fecha , comentarios;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.request = request;
        this.response = response;
        nombre = request.getParameter("elejirRestaurante");
        comentarios = request.getParameter("comentario");
        fecha = request.getParameter("fecha");
        comentario = new Comentario(nombre, fecha, comentarios);
        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

}
