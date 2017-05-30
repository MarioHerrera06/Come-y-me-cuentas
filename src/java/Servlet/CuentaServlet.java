package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import BaseDeDatos.Conexion;
import BaseDeDatos.Hash;
import Datos.Usuario;
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
@WebServlet(urlPatterns = {"/CuentaServlet"})
public class CuentaServlet extends HttpServlet {

    public static Usuario usuario;
    public static Conexion c = new Conexion();

    public static String nombreUsuario, contraHash, tipoUsuario;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        tipoUsuario = request.getParameter("tipoUsuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");

        contraHash = Hash.hash(password);

//                Cifrado cifrado=new Cifrado(contra);
//                usuario = new Usuario(nombre, apellidos, nombreUsuario, Integer.parseInt(telefono), correo, cifrado.getWord());
        usuario = new Usuario(nombre, apellidos, nombreUsuario, Integer.parseInt(telefono), correo, contraHash,tipoUsuario);
        //System.out.println("Crea cuenta con pas:"+password+"cifrada:"+contraHash);
        c.agregarUsuarioNormal(usuario);
        RequestDispatcher dispacher = request.getRequestDispatcher("login.jsp");
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
