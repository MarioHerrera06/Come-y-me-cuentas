<%-- 
    Document   : nuevaCuenta
    Created on : 19/03/2017, 07:26:28 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html
    
<html>
     <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <body>
       
        <section>

            <h1 id="tituloCuenta"> Nueva Cuenta</h1> 
            <form id="cuentaNueva" action="CuentaServlet" method="POST">
                <table>
            <tr>
                <td>Nombre :</td>
                <td> <input type="text" name="nombre" placeholder="Escriba su nombre" ></td>
            </tr>
            <tr>
                <td>Apellido :</td> 
                <td> <input type="text" name="apellidos"></td>
            </tr>
            <tr> 
                <td>Telefono :</td>
                <td><input type="number" name="telefono"></td>
            </tr>
            <tr>
                <td>Correo :</td>
                <td><input type="email" name="correo"></td>
            </tr>
            <tr>
                <td>Nombre de Usuario :</td>
                <td><input type="text" name="nombreUsuario" required=""><span class="red"> *</span></td>
            </tr>
            <tr>
                <td> Contraseña :</td>
                <td><input type="password" name="password" required=""><span class="red"> *</span></td>
            </tr>

        </table>            

                <input  type="submit" value="Crear Cuenta" id="botonCuenta" /><br>
               
            </form>
            
           
          

        </section>

    </body>
</html>
