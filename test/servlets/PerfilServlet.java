package servlets;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/perfilServlet"})
public class PerfilServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/jsp");
       PrintWriter out=response.getWriter();
       request.getRequestDispatcher("login.jsp").include(request,response);
       
       HttpSession session=request.getSession(false);
       if(session!=null){
           String name=(String)session.getAttribute("user");
           out.print("Hello, " + name + "Welcome to profile");
           
       }else{
           out.print("Por favor logeate primero");
           request.getRequestDispatcher("login.jsp").include(request, response);
       }
       out.close();
    }

   
}
