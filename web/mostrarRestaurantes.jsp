<%-- 
    Document   : mostrarRestaurantes
    Created on : 31/05/2017, 12:19:25 AM
    Author     : Valentina
--%>

<%@page import="Datos.Comentario"%>
<%@page import="Datos.Restaurante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDeDatos.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/Style.css" rel="stylesheet" type="text/css" >
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <title>Restaurantes</title>
    </head>
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
                <li><a href="agregarComentarios.jsp">Comentar</a></li>
                <li> <a href="calificacion.jsp">Calificar</a></li>
                <li><a href="">Recomendar</a></li>
            </ul>
        </aside>
        <%Conexion conexion = new Conexion();
            int id = Integer.valueOf(request.getParameter("id"));
            ArrayList<Restaurante> listaRestaurantes = conexion.mostrarRestaurantePorId(id);
            ArrayList<Comentario> listaComentarios = conexion.mostrarComentarios(id);
            

            for (int i = 0; i < listaRestaurantes.size(); i++) {
        %>
        <section>
        <div id="campoRestaurante" style="color: white">

            <h1 class="titulos" style="opacity: .70"><%=listaRestaurantes.get(i).getNombre()%></h1>
            <img class="imagen" src="./img/<%=listaRestaurantes.get(i).getImagen()%>">
            <div id="getDescripcion"><strong>Descripcion: </strong><%=listaRestaurantes.get(i).getDescripcion()%></div>

            <div id="getDireccion"><strong>Direccion: </strong><%=listaRestaurantes.get(i).getDireccion()%></div>

            <div id="getTelefono"><strong>Telefono: </strong><%=listaRestaurantes.get(i).getTelefono()%></div>

            <div id="getHoraInicio"><strong>Hora de apertura: </strong><%=listaRestaurantes.get(i).getHoraInicioClasificar()%></div>

            <div id="getHoraFin"><strong>Hora de cierre: </strong><%=listaRestaurantes.get(i).getHoraFinClasificar()%></div>

            <div id="getHorario"><strong>Horario de atencion: </strong><%=listaRestaurantes.get(i).getHorario()%></div>

            <div id="getTipoComida"><strong>Tipo principal de comida: </strong><%=listaRestaurantes.get(i).getTipoComida()%></div>
             <h1 class="titulos" style="opacity: .70">Comentarios</h1>
        </div>

        <%}%>
        
        <%
           
            for (int i = 0; i < listaComentarios.size(); i++) {
                int num  = listaComentarios.get(i).getUsuario();
              String nombre =  conexion.buscarNombrePorId(num);
                System.out.println(num);
        %>
             <div id="campoComentarios" style="color: white">

            <h1 class="titulos" style="opacity: .70"><%=nombre%></h1>
             <div id="getDescripcion"><strong>Usuario #: </strong><%=num%></div>
            <div id="getDescripcion"><strong>Comentario: </strong><%=listaComentarios.get(i).getTextoComentario()%></div>

            <div id="getDireccion"><strong>Fecha: </strong><%=listaComentarios.get(i).getFecha()%></div>

            
             </div>
<%}%>
        </section>
    </body>
</html>
