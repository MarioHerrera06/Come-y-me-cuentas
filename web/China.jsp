<%-- 
    Document   : Mexicana
    Created on : 09-abr-2017, 17:04:11
    Author     : Produccion
--%>


<%@page import="Datos.Restaurante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDeDatos.Conexion"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <%@include file="aside.jsp" %>

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title >Come y me cuentas</title>
    <body>

        <section>
            <h1 id="titulosRestaurantes" style="color: grey; border-bottom: double; border-color: orange; border-left: double; padding-left: 4%;">Restaurantes chinos </h1>
            <% Conexion conec = new Conexion();
                ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestauranteRandom("China");
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

        </section>

    </body>
</html>
