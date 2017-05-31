

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
