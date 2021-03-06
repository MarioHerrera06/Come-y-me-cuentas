package Servlet;



import BaseDeDatos.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



@WebServlet(urlPatterns = {"/AgregarImagenRestauranteServlet"})
public class AgregarImagenRestauranteServlet extends HttpServlet {
private ServletFileUpload ServletFileUpload;
private DiskFileItemFactory DiskFileItemFactory ;
private List<FileItem> listaItems;
public String imgUrl;
Conexion conexion = new Conexion();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           RequestDispatcher dispacher = request.getRequestDispatcher("restaurantesNuevos.jsp");
        dispacher.forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiskFileItemFactory= new DiskFileItemFactory();
        ServletFileUpload = new ServletFileUpload(DiskFileItemFactory);
        try{
            listaItems=ServletFileUpload.parseRequest(request);
        }catch(FileUploadException ex){
            System.out.println("error 1");
        }
        System.out.println(":)");
        for(int i=0;i<listaItems.size();i++){
            FileItem item = (FileItem)listaItems.get(i);
            if(!item.isFormField()){
                 imgUrl="C:\\Users\\LauraValentina\\Videos\\Nuevas\\Come-y-me-cuentas\\web\\img\\"+item.getName();
                File file = new File(imgUrl);
                 try{
                     item.write(file);
                 }catch(Exception ex){
                     System.out.println("error 2");
                 }    
                        }
       
      AgregarRestauranteServlet.restaurante.setImagen(item.getName());
      conexion.agregarRestaurante(AgregarRestauranteServlet.restaurante);
      
        processRequest(request, response);
    }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
