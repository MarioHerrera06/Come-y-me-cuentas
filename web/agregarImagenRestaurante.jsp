

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="header.jsp" %>
        <%@include file="nav.jsp" %>
      

        <link href="styles/Style.css" rel="stylesheet" type="text/css" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Imagen</title>
    </head>
    <body>
        <h1>Imagen</h1>
        <form style="padding-bottom: 5%;" id="nuevoRestaurante" action="AgregarImagenRestauranteServlet" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>Imagen en JPG:</td>
                    <td><input type="file" name="imgRestaurante" size="50"/></td>
                </tr >

            </table>
            <input  type="submit" value="Crear" id="botonCuenta" /><br>
        </form>
    </body>
</html>
