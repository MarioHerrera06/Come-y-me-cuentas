<%-- 
    Document   : calificacion
    Created on : 13/05/2017, 09:24:00 AM
    Author     : Valentina
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Datos.Restaurante"%>
<%@page import="BaseDeDatos.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <title>Calificación </title>
    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
     <script src= "script.js"></script>
    <h1 id="tituloRestaurante" style="opacity: .80;"> Calificación</h1> 
    <form id="nuevoRestaurante" action="AgregarCalificacionServlet" method="POST">
        <table>
            <tr>
                <td>Nombre :</td>
                <td> <select name="elejirRestaurante" required >
                        <option selected value="0"> Elija un restaurante</option>


                        <%  Conexion conec = new Conexion();
                            ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestaurante();
                            for (int i = 0; i < listaRestaurantes.size(); i++) {%>

                        <option value="<%= listaRestaurantes.get(i).getId()%>"  > <%out.print(listaRestaurantes.get(i).getNombre());%> </option>
                        <%}%>                
                    </select><br>  
                </td>                            
            </tr>            
        </table> 
         <div id = "esttrellas">

                            
                
                <img class="estrella" src="img/gris.png" onclick="cambiar(0)" style="width: 10%">
                <img class="estrella" src="img/gris.png" onclick="cambiar(1)" style="width: 10%">
                <img class="estrella" src="img/gris.png" onclick="cambiar(2)" style="width: 10%">
                <img class="estrella" src="img/gris.png" onclick="cambiar(3)" style="width: 10%">
                <img class="estrella" src="img/gris.png" onclick="cambiar(4)" style="width: 10%">
                              
            </div> 



        <input  type="submit" value="Agregar" id="botonCuenta" /><br>

    </form>


</html>




