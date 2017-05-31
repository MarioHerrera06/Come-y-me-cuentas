/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import BaseDeDatos.Conexion;
import Datos.Comentario;
import Datos.Restaurante;
import static Datos.Restaurante.listaComentarios;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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
import javax.servlet.http.HttpSession;


@WebServlet(name = "AgregarComentarioServlet", urlPatterns = {"/AgregarComentarioServlet"})
public class AgregarComentarioServlet extends HttpServlet {

public static Conexion con = new Conexion();

    public static HttpServletRequest request;
    public static HttpServletResponse response;
    String fecha , text;
    int codRes,idU,codTipo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
    HttpSession actual = request.getSession(true);
    idU = (int)actual.getAttribute("idU");
    
        this.request = request;
        this.response = response;
        codRes = Integer.parseInt(request.getParameter("Restaurante"));
        text = request.getParameter("comentario");
        fecha = request.getParameter("fecha");
        codTipo= Integer.parseInt(request.getParameter("codigoTipo"));
        System.out.println(fecha);
        System.out.println(codRes);
        System.out.println(text);
        System.out.println(idU);
       
        
        
       Comentario com = new Comentario(codTipo,idU, codRes, text, fecha);
        System.out.println(idU+"<------"+fecha);
       con.agregarComentarios(com);
        RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

}
