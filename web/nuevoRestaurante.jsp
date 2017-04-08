<%-- 
    Document   : nuevoRestaurante
    Created on : 6/04/2017, 06:43:38 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>


    <link href="styles/Style.css" rel="stylesheet" type="text/css" >




    <h1 id="tituloRestaurante"> Nuevo Restaurante</h1> 
    <form id="nuevoRestaurante" action="AgregarRestauranteServlet" method="POST">
        <table>
            <tr>
                <td>Nombre :</td>
                <td> <input type="text" name="nombreRestaurante" required></td>
            </tr>
            <tr>
                <td>Direccion :</td> 
                <td> <input type="text" name="direccion" required></td>
            </tr>
            <tr> 
                <td>Telefono :</td>
                <td><input type="number" name="telefono" required=""></td>
            </tr>
            <tr>
                <td>Horario :</td>
                <td><input type="text" name="horario" required=""></td>
            </tr>
            <tr>
                <td>Tipo de comida principal:</td>
                <td><select name="tipoComida" >
                        <option selected value="0"> Elige una opción </option>
                        <option value="1">Mexicana</option> 
                        <option value="2">China</option> 
                        <option value="3">Italiana</option>
                        <option value="4">Francesa</option> 
                        <option value="5">Americana</option> 
                        <option value="6">Comida rapida</option> 
                    </select></td>
            </tr>


        </table>            

        <input  type="submit" value="Crear" id="botonCuenta" /><br>

    </form>

</html>
