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
<%
response.setHeader("Content-Type", "text/html; charset=windows-1252");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "mon, 01 Jan 2017 00:00:01 GMT");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Cache-Control", "must-revalidate");
response.setHeader("Cache-Control", "no-cache");

HttpSession actual = request.getSession(true);
String id = actual.getId();
String nombre = (String)actual.getAttribute("logueado");

if(actual.isNew()){
    response.sendRedirect("login.jsp");
    return;
}
if(actual == null){
    response.sendRedirect("login.jsp");
}else{
    if(actual.getAttribute("logueado") == null){
        response.sendRedirect("login.jsp");
    }
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
            <%
                HttpSession sesion = request.getSession();
                if (sesion.getAttribute("tipoUsuario") != null) {
                    Integer num = (Integer) sesion.getAttribute("tipoUsuario");
                    if (num == 1) {
                        out.println(" <li><a href=\"nuevoRestaurante.jsp\">Agregar Restaurante</a></li>");
                    }
                }
            %> 

            <h2 class="titulos"> Tipos de comida: </h2>
            <ul>

                <li><a href="Mexicana.jsp">Mexicana</a></li>
                <li><a href="China.jsp">China</a></li>
                <li><a href="Italiana.jsp">Italiana</a></li>
                <li><a href="Francesa.jsp">Francesa</a></li>
                <li><a href="Americana.jsp">Americana</a></li>
                <li><a href="ComidaRapida.jsp">Comida rapida</a></li>

            </ul> 
            <ul>

                <li><a href="restaurantesNuevos.jsp">Restaurantes</a></li>
                <li><a href="agregarComentarios.jsp">Comentar o recomendar</a></li>
                
            </ul>
        </aside>
        <section>
            <h1 id="titulosRestaurantes" style="color: grey; border-bottom: double; border-color: orange; border-left: double; padding-left: 4%;">Restaurantes italianos </h1>
            <% Conexion conec = new Conexion();
                ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestauranteRandom("Italiana");
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
