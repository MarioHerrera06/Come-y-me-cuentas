

<%@page import="java.util.ArrayList"%>
<%@page import="Datos.Restaurante"%>
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
            <h1>Restaurantes </h1>
            <% Conexion conec = new Conexion();
            System.out.println("breve");
                ArrayList<Restaurante> listaRestaurantes=conec.mostrarRestaurante();
                System.out.println(listaRestaurantes.size());
                for(int i=0; i<listaRestaurantes.size();i++){ 
                
             %>
             <h1 class="titulos"><%=listaRestaurantes.get(i).getNombre() %></h1>
             <p class="informacion"><%=listaRestaurantes.get(i).getDescripcion()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getDireccion()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getTelefono()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getHoraInicio()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getHoraFin()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getHorario()%></p>
             <br>
             <p class="informacion"><%=listaRestaurantes.get(i).getTipoComida()%></p>
             <br>
             

             
            <%}%>
            
        </section>
        
    </body>




</html>

