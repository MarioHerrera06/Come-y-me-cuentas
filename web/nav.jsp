<%-- 
    Document   : nav
    Created on : 9/03/2017, 04:35:10 PM
    Author     : Valentina
--%>

<%@page import="Servlet.LoginServlet"%>
<%@page import="Datos.Usuario"%>
<%@page import="BaseDeDatos.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <nav>

        <div id="navTitulo"> <a href="index.jsp"> Atrevete, experimenta y disfruta </a> 
     
            <div id="links">
                <a href="login.jsp" id="linkLoginNav" >Login</a>
                <a href="nuevaCuenta.jsp" id="linkNuevaCuentaNav">Nueva cuenta</a>
                <a href="cerrar.jsp" id="cerrar">Cerrar</a>
            </div>

        </div>

    </nav>
</html>
