

<%@page import="Datos.TipoComida"%>
<%@page import="Datos.Restaurante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="BaseDeDatos.Conexion"%>

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
    String nombre = (String) actual.getAttribute("logueado");
    System.out.println(nombre + " este es el nombre del user");

    if (actual.isNew()) {
        response.sendRedirect("login.jsp");
        return;
    }
    if (actual == null) {
        response.sendRedirect("login.jsp");
    } else {
        if (actual.getAttribute("logueado") == null) {
            response.sendRedirect("login.jsp");
        }
    }

%>

<html>
    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>


    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title >Come y me cuentas</title>

    <body>
         <h6 style="color: white;opacity: 0.7; font-family:sans-serif; position: absolute; right: 13%; top: 25%;">Bienvenido: <%=actual.getAttribute("logueado")%>!</h6>
        
        <aside>
            <%
                HttpSession sesion = request.getSession();
                if (sesion.getAttribute("tipoUsuario") != null) {
                    Integer num = (Integer) sesion.getAttribute("tipoUsuario");
                    if (num == 1) {
                        out.println(" <li ><a href=\"nuevoRestaurante.jsp\">Agregar Restaurante</a></li>");
                    }
                }
            %> 

          <h2 class="titulos"> Tipos de comida: </h2>
          <ul style="border-bottom: double; border-color: orange; padding-bottom: 5%;">
                 <% Conexion conec2 = new Conexion();
                ArrayList<TipoComida> listaRestaurantes2 = conec2.mostrarTipoComida();
                for (int i = 0; i < listaRestaurantes2.size(); i++) {
                    
                    TipoComida res = listaRestaurantes2.get(i);
                    %>
                   
                    <div>         
                        <li>  <a href="mostrarTipoComida.jsp?id=<%=res.getIdTipoComida()%>"><%=res.getNombreTipoComida()%></a></li>
                    </div>      
          

            <%}%>

            </ul> 
            <ul>

                <li><a href="restaurantesNuevos.jsp">Restaurantes</a></li>
                <li><a href="agregarComentarios.jsp">Comentar o recomendar</a></li>
               
            </ul>
        </aside>


        <section>
            <h1 style="color:grey;">Restaurantes preferidos</h1>


            <div class="cajaImagenes">

                <div class="mySlides">
                    <a href="mostrarRestaurantes.jsp?id=1"><img src="img/Corral.jpg" alt="" style="width:100%" ></a>                   
                    <div class="textoImagen">
                        El corral
                    </div>
                </div>

                <div class="mySlides">
                    <a href="mostrarRestaurantes.jsp?id=2"><img src="img/HardRock.png" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                        HardRock
                    </div>
                </div>

                <div class="mySlides">
                    <a href="mostrarRestaurantes.jsp?id=3"><img src="img/Crepes.jpg" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                        Crepes And Waffles
                    </div>
                </div>


            </div>

            <button class="botonIzquierda" onclick="plusDivs(-1)">&#10094;</button>
            <button class="botonDerecha" onclick="plusDivs(+1)">&#10095;</button>

           


            <script>
                var slideIndex = 1;
                showDivs(slideIndex);

                function plusDivs(n) {
                    showDivs(slideIndex += n);
                }


                function showDivs(n) {
                    var i;
                    var x = document.getElementsByClassName("mySlides");
                    if (n > x.length) {
                        slideIndex = 1
                    }
                    if (n < 1) {
                        slideIndex = x.length
                    }
                    for (i = 0; i < x.length; i++) {
                        x[i].style.display = "none";
                    }
                    x[slideIndex - 1].style.display = "block";
                }
            </script>


        </section>

</html>
