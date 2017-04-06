package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"loginServlet"})
public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);

    }
}
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            request.getRequestDispatcher("Presentacion/login.jsp").include(request, response);
//            String name= request.getParameter("user");
//            String password=request.getParameter("password");
//            
//            if(password.equals(nuevoUsuarioServlet.contraseña)&& name.equals(nuevoUsuarioServlet.nomUsuario)){
//                HttpSession session=request.getSession();
//                session.setAttribute("user", name);
//                RequestDispatcher dispacher = request.getRequestDispatcher("Presentacion/index.jsp");
//                dispacher.forward(request, response);
//            }else{
//                out.print("Sorry, username or password error!");
//                request.getRequestDispatcher("Presentacion/login.jsp").include(request, response); 
//            }
//        }

