<%-- 
    Document   : nuevaCuenta
    Created on : 19/03/2017, 07:26:28 PM
    Author     : Valentina
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>


    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
  

        

            <h1 id="tituloCuenta"> Nuevo Usuario</h1> 
            <form id="cuentaNueva" action="CrearUsuarioServlet" method="POST">
                Nombre : 
                <input type="text" name="nombre"><span class="red"> *</span> <br>
                <br>
                Apellido :
                <input type="text" name="apellidos"><br>
                <br>               
                Celular: 
                <input type="number" name="celular"><br>
                <br>
                Correo :
                <input type="email" name="correo"><br>
                <br>
                Nombre de Usuario :
                <input type="text" name="nomUsuario"><br>
                <br>
                Contraseña:
                <input type="password" name="password"><span class="red"> *</span><br>
                <br>

                <input  type="submit" value="Crear Cuenta" id="botonCuenta" /><br>

            </form>

                </html>
