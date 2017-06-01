

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
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <title>Nuevo Restaurante</title>

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >




    <h1 id="tituloRestaurante" style="opacity: .80;"> Nuevo Restaurante</h1> 
    <form name="otro" id="nuevoRestaurante" action="AgregarRestauranteServlet" method="POST">
        <table>
            <tr>
                <td>Nombre :</td>
                <td> <input type="text" name="nombreRestaurante" required></td>
            </tr>
            <tr>
                <td>Descripcion:</td> 
                <td> <input type="text" name="descripcion" required></td>
            </tr>
            <tr>
                <td>Direccion :</td> 
                <td> <input type="text" name="direccion" required></td>
            </tr>
            <tr> 
                <td>Telefono :</td>
                <td><input type="number" name="telefono" required="" min='0'></td>
            </tr>
            <tr>
                <td>Hora apertura:</td>
                <td><select name="horaInicio" required >
                        <option selected value="0"> Hora inicio </option>
                        <option value="1">1</option> 
                        <option value="2">2</option> 
                        <option value="3">3</option>
                        <option value="4">4</option> 
                        <option value="5">5</option> 
                        <option value="6">6</option>  
                        <option value="7">7</option> 
                        <option value="8">8</option> 
                        <option value="9">9</option>
                        <option value="10">10</option> 
                        <option value="11">11</option> 
                        <option value="12">12</option> 
                        <option value="13">13</option> 
                        <option value="14">14</option> 
                        <option value="15">15</option>
                        <option value="16">16</option> 
                        <option value="17">17</option> 
                        <option value="18">18</option> 
                        <option value="19">19</option> 
                        <option value="20">20</option> 
                        <option value="21">21</option>
                        <option value="22">22</option> 
                        <option value="23">23</option> 
                        <option value="24">24</option> 
                    </select></td>
            </tr>


            <tr>
                <td>Hora cierre:</td>
                <td><select name="horaFin" required > 
                        <option selected value="0"> Hora fin </option>
                        <option value="1">1</option> 
                        <option value="2">2</option> 
                        <option value="3">3</option>
                        <option value="4">4</option> 
                        <option value="5">5</option> 
                        <option value="6">6</option> 
                        <option value="7">7</option> 
                        <option value="8">8</option> 
                        <option value="9">9</option>
                        <option value="10">10</option> 
                        <option value="11">11</option> 
                        <option value="12">12</option> 
                        <option value="13">13</option> 
                        <option value="14">14</option> 
                        <option value="15">15</option>
                        <option value="16">16</option> 
                        <option value="17">17</option> 
                        <option value="18">18</option> 
                        <option value="19">19</option> 
                        <option value="20">20</option> 
                        <option value="21">21</option>
                        <option value="22">22</option> 
                        <option value="23">23</option> 
                        <option value="24">24</option> 
                    </select></td>
            </tr>

            <tr>
                <td>Horario:</td>
                <td><input type="radio" name="horario" value="Lunes a viernes">Lunes a viernes</td>
            </tr>
            <tr>
                <td></td>
                <td> <input type="radio" name="horario" value="Lunes a sabado">Lunes a sabado</td>
            </tr>
            <tr>
                <td></td>
                <td> <input type="radio" name="horario" value="Lunes a domingo">Lunes a domingo</td>
            </tr>

            <tr>
                <td></td>
            </tr>
            <tr>
                <td>Tipo de comida principal:</td>
                <td><select name="tipoComida" required>
                        <option selected value="0"> Elige una opción </option>
                        <option value="Mexicana">Mexicana</option> 
                        <option value="China">China</option> 
                        <option value="Italiana">Italiana</option>
                        <option value="Francesa">Francesa</option> 
                        <option value="Americana">Americana</option> 
                        <option value="Comida rapida" >Comida rapida</option> 
                        <option value="Otro" onclick="myFunction()">Otro</option> 
                    </select>
             
                   
                </td> 
               <td id="demo"  ></td>
            </tr>

            
            <script>
                
                function  myFunction() {
                     x= "<input "+"type="+"text "+"name="+"otro"+">";
                   document.getElementById("demo").innerHTML=x;
                
                }
            </script>
           


        </table>            
 
        <input  type="submit" value="Crear" id="botonCuenta" /><br>

    </form>

</html>
