package Servlet;



import BaseDeDatos.Conexion;
import BaseDeDatos.Hash;
import Servlet.CuentaServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);

    }       public static String comparar;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      

        Conexion con = new Conexion();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out= response.getWriter();
        request.getRequestDispatcher("login.jsp").include(request, response);
        String name= request.getParameter("user");
        String password=request.getParameter("password");
        comparar = Hash.hash(password);
      
        
        String res = con.buscarUsuarios(name, comparar);
        System.out.println(res);
        
              
        
        if(res.equals("true")){
            HttpSession session=request.getSession();
            session.setAttribute("usuario",name);
            session.setAttribute("tipoUsuario", name);
            RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        }else{
            out.println("<h1>El Usuario o la contraseña incorrectos<h1>");  
            request.getRequestDispatcher("login.jsp").include(request, response); 
        }
        out.close();
    }
}
