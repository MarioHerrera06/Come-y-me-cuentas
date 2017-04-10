package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import BaseDeDatos.Conexion;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Valentina
 */
@WebServlet(urlPatterns = {"/AgregarImagenRestauranteServlet"})
public class AgregarImagenRestauranteServlet extends HttpServlet {
private ServletFileUpload upload;
private DiskFileItemFactory fileItem;
private List<FileItem> items;
public String imgUrl;
Conexion conexion = new Conexion();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarImagenRestauranteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarImagenRestauranteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        fileItem = new DiskFileItemFactory();
        upload = new ServletFileUpload(fileItem);
        try{
            items=upload.parseRequest(request);
        }catch(FileUploadException ex){
            System.out.println("error 1");
        }
        System.out.println(":)");
        for(int i=0;i<items.size();i++){
            FileItem item = (FileItem)items.get(i);
            if(!item.isFormField()){
                 imgUrl="C:\\Users\\LauraValentina\\Music\\COMEYMECUENTAS\\Come-y-me-cuentas\\web\\img\\"+item.getName();
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
