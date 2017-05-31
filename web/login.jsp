<%-- 
    Document   : login
    Created on : 10/03/2017, 08:08:22 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Come y me cuentas - Login</title>
        <link href="styles/Style.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <a href="index.jsp"><img src="https://images.cooltext.com/4901958.png" width="500" height="100" alt="Come y me cuentas" style="position: absolute; top: 10%; left: 30%;"/></a>


        <form id="formularioLogin" action="LoginServlet" method="POST">
            <table>

                <tr>
                    <td>Usuario:</td>
                    <td><input title="Se necesita un usuario" type="text"  name="user" placeholder="Escriba el nombre de su usuario" required  /></td>

                <tr>
                    <td>Contraseña:</td>
                    <td><input title="Se necesita una contraseña" type="password" name="password" placeholder="Escriba su contraseña" required/></td>
                </tr>
            </table>
            <input  type="submit" value="Ingresar" id="botonLogin" /><br>
        </form>
        <br><a href="nuevaCuenta.jsp" id="nuevaCuenta" class="nuevaLink">Nueva cuenta </a>


    </body>
</html>
