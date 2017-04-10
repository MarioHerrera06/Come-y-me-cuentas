<%-- 
    Document   : agregarImagenRestaurante
    Created on : 9/04/2017, 08:36:30 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Imagen</h1>
        <form id="nuevoRestaurante" action="AgregarImagenRestauranteServlet" method="POST" enctype="multipart/form-data">
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
