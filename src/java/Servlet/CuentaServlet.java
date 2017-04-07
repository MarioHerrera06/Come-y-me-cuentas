package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

  public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
 
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//         out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet borrarContacto</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Usuario Creado... <br> Nombre: "+request.getParameter("nombre") +"<br>Apellido: "+request.getParameter("apellidos")+"<br> Edad: "+request.getParameter("edad")+"<br> Celular: "+request.getParameter("celular")+"<br> Correo: "+request.getParameter("correo") +"</h1>");
//            out.println("</body>");
//            out.println("</html>");
            
            RequestDispatcher dispacher = request.getRequestDispatcher("login.jsp");
            dispacher.forward(request, response);
//            
           
        }
    }
    
    public static String nombreUsuario,contraseña;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
       
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");
                String celular = request.getParameter("celular");
                String correo = request.getParameter("correo");
                nombreUsuario = request.getParameter("nombreUsuario");
                contraseña = request.getParameter("password");
		Usuario usuario= new Usuario(nombre,apellidos, celular, correo,nombreUsuario,contraseña);
                listaUsuarios.add(usuario);  
                 RequestDispatcher dispacher = request.getRequestDispatcher("newjsp.jsp");
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