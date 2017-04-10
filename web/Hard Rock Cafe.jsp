<%-- 
    Document   : Hard Rock Cafe
    Created on : 18/03/2017, 09:30:44 PM
    Author     : Valentina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Datos.Restaurante"%>
<%@page import="BaseDeDatos.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <%@include file="aside.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/Style.css" rel="stylesheet" type="text/css">

        <title>Hard Rock Cafe</title>
    </head>
    <body>
        <section>

            <% Conexion conec = new Conexion();
                ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestaurantesPredeterminados("Hard Rock Cafe");
                for (int i = 0; i < listaRestaurantes.size(); i++) {

            %>
            <div id="campoRestaurante">
                
                <h1 class="titulos" style="opacity: .70"><%=listaRestaurantes.get(i).getNombre()%></h1>
                
                <img class="imagen" src="./img/<%=listaRestaurantes.get(i).getImagen()%>">

                <div id="getDescripcion"><strong>Descripcion: </strong><%=listaRestaurantes.get(i).getDescripcion()%></div>

                <div id="getDireccion"><strong>Direccion: </strong><%=listaRestaurantes.get(i).getDireccion()%></div>

                <div id="getTelefono"><strong>Telefono: </strong><%=listaRestaurantes.get(i).getTelefono()%></div>

                <div id="getHoraInicio"><strong>Hora de apertura: </strong><%=listaRestaurantes.get(i).getHoraInicioClasificar()%></div>

                <div id="getHoraFin"><strong>Hora de cierre: </strong><%=listaRestaurantes.get(i).getHoraFinClasificar()%></div>

                <div id="getHorario"><strong>Horario de atencion: </strong><%=listaRestaurantes.get(i).getHorario()%></div>

                <div id="getTipoComida"><strong>Tipo principal de comida: </strong><%=listaRestaurantes.get(i).getTipoComida()%></div>

            </div>


            <%}%>
            <br><ul id="agregarComentario"> <li><a href="">Agregar Comentario</a></li></ul>




        </section>
    </body>
</html>
