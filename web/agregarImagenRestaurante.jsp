

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Imagen</title>
        <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    </head>


    <body>
        
        
       
        <form style="padding-bottom: 5%;" id="nuevoRestaurante" action="AgregarImagenRestauranteServlet" method="POST" enctype="multipart/form-data">
            <h3 id="tituloImagenInsertar">Selecciona una imagen para el restaurante</h3>
            <table>
                <tr>
                    <td>Imagen:</td>
                    <td><input type="file" name="imgRestaurante" size="50"/></td>
                </tr >

            </table>
            <input  type="submit" value="Crear" id="botonCuenta" /><br>
        </form>
    </body>
</html>
