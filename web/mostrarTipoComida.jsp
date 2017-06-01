<%-- 
    Document   : Mexicana
    Created on : 09-abr-2017, 17:04:11
    Author     : Produccion
--%>


<%@page import="Datos.TipoComida"%>
<%@page import="Datos.Restaurante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDeDatos.Conexion"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Content-Type", "text/html; charset=windows-1252");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "mon, 01 Jan 2017 00:00:01 GMT");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Cache-Control", "must-revalidate");
    response.setHeader("Cache-Control", "no-cache");

    HttpSession actual = request.getSession(true);
    String id = actual.getId();
    String nombre = (String) actual.getAttribute("logueado");

    if (actual.isNew()) {
        response.sendRedirect("login.jsp");
        return;
    }
    if (actual == null) {
        response.sendRedirect("login.jsp");
    } else if (actual.getAttribute("logueado") == null) {
        response.sendRedirect("login.jsp");
    }

%>
<html>

    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title >Come y me cuentas</title>
    <body>
        <aside>
            <%                HttpSession sesion = request.getSession();
                if (sesion.getAttribute("tipoUsuario") != null) {
                    Integer num = (Integer) sesion.getAttribute("tipoUsuario");
                    if (num == 1) {
                        out.println(" <li><a href=\"nuevoRestaurante.jsp\">Agregar Restaurante</a></li>");
                    }
                }
            %> 

            <h2 class="titulos"> Tipos de comida: </h2>
            <ul style="border-bottom: double; border-color: orange; padding-bottom: 5%;">
                <% Conexion conec2 = new Conexion();
                    ArrayList<TipoComida> listaRestaurantes2 = conec2.mostrarTipoComida();
                    for (int i = 0; i < listaRestaurantes2.size(); i++) {

                        TipoComida res = listaRestaurantes2.get(i);
                %>
                <div>         
                    <li>  <a href="mostrarTipoComida.jsp?id=<%=res.getIdTipoComida()%>"><%=res.getNombreTipoComida()%></a></li>
                </div>        


                <%}%>

            </ul> 
            <ul>

                <li><a href="restaurantesNuevos.jsp">Restaurantes</a></li>
                <li><a href="agregarComentarios.jsp">Comentar o recomendar</a></li>

            </ul>
        </aside>
        <section>
            <h1>Restaurantes </h1>

            <% Conexion conec = new Conexion();
                int id2 = Integer.valueOf(request.getParameter("id"));
                ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestauranteRandom(id2);
                for (int i = 0; i < listaRestaurantes.size(); i++) {

                    Restaurante res = listaRestaurantes.get(i);%>

            <div id="campoRestaurante">

                <h1><%=res.getNombre()%></h1>
                <div class="imgcontainer">
                    <img class="imagen" src="img/<%=res.getImagen()%>" >
                </div>
                <a href="mostrarRestaurantes.jsp?id=<%=res.getId()%>">Ver informacion completa</a>
            </div>  


            <%}%>

        </section>

    </body>



</html>
