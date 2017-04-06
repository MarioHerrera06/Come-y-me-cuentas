package servlets;
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
@WebServlet(urlPatterns = {"/nuevoUsuarioServlet"})
public class nuevoUsuarioServlet extends HttpServlet {
public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
 
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            
            RequestDispatcher dispacher = request.getRequestDispatcher("Presentacion/login.jsp");
            dispacher.forward(request, response);
          
           
        }
    }
    
    public static String nomUsuario,contraseña;
   public static HttpServletRequest request;
     public static HttpServletResponse response;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
       
                String nombre = request.getParameter("nombre");
                String apellidos = request.getParameter("apellidos");                
                String celular = request.getParameter("celular");
                String correo = request.getParameter("correo");
                nomUsuario = request.getParameter("nomUsuario");
                contraseña = request.getParameter("password");
		Usuario usuario= new Usuario(nombre,apellidos, nomUsuario, celular, correo,contraseña);
                listaUsuarios.add(usuario);  
                 RequestDispatcher dispacher = request.getRequestDispatcher("newjsp.jsp");
            dispacher.forward(request, response);
            
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
