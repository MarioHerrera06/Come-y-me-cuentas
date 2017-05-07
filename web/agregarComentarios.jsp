<%-- 
    Document   : agregarComentarios
    Created on : 6/05/2017, 02:30:25 PM
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
    <title>Comentarios </title>

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >




    <h1 id="tituloRestaurante" style="opacity: .80;"> Nuevo Comentario</h1> 
    <form id="nuevoRestaurante" action="AgregarComentarioServlet" method="POST">
        <table>
            <tr>
                <td>Nombre :</td>
                <td> <select name="elejirRestaurante" required >
                        <option selected value="0"> Elija un restaurante</option>
                
                
        <%  Conexion conec = new Conexion();
        ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestaurante();
            for(int i=0;i<listaRestaurantes.size();i++){%>
        
        <option value="<%= listaRestaurantes.get(i).getId()%>"  > <%out.print(listaRestaurantes.get(i).getNombre());%> </option>
        <%}%>                
                    </select><br>  
                </td>                            
            </tr><br>
            <td>Fecha:</td>
            <td> <input type="Date" name="fecha" ></td>
            </table> 
                    <h5>Comentario:</h5>
            <textArea name="comentario"  id="comentario"rows="10" cols="20">   
            </textArea>
                 
                
                   

        <input  type="submit" value="Agregar" id="botonCuenta" /><br>

    </form>

</html>

