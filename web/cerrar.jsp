<%-- 
    Document   : cerrar
    Created on : 30/05/2017, 11:01:04 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setContentType("text/html");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");

    response.setDateHeader("Espires", 0);
    response.setHeader("Pragma", "no-cache");

    request.getSession().removeAttribute("logueado");
    session.invalidate();
    response.sendRedirect("index.jsp");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrar Sesion</title>
    </head>
    <body>
        <h1>Sesion cerrada</h1>
    </body>
</html>
