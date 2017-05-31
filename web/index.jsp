

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
    <!--%@include file="nav.jsp" %-->


    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title >Come y me cuentas</title>

    <body>
        <nav>
            <div id="navTitulo"> <a href="index.jsp"> Atrevete, experimenta y disfruta </a> 

                <div id="links">
                    <h1 style="color: white">Bienvenido: <%=actual.getAttribute("logueado")%>  </h1>
                    <a href="login.jsp" id="linkLoginNav" >Login</a>
                    <a href="nuevaCuenta.jsp" id="linkNuevaCuentaNav">Nueva cuenta</a>
                    <a href="cerrar.jsp" id="cerrar">Cerrar</a>
                </div>

            </div>

        </nav>
        <aside>
            <%
                HttpSession sesion = request.getSession();
                if (sesion.getAttribute("tipoUsuario") != null) {
                    Integer num = (Integer) sesion.getAttribute("tipoUsuario");
                    if (num == 1) {
                        out.println(" <li><a href=\"nuevoRestaurante.jsp\">Agregar Restaurante</a></li>");
                    }
                }
            %> 

            <h2 class="titulos"> Tipos de comida: </h2>
            <ul>

                <li><a href="Mexicana.jsp">Mexicana</a></li>
                <li><a href="China.jsp">China</a></li>
                <li><a href="Italiana.jsp">Italiana</a></li>
                <li><a href="Francesa.jsp">Francesa</a></li>
                <li><a href="Americana.jsp">Americana</a></li>
                <li><a href="ComidaRapida.jsp">Comida rapida</a></li>

            </ul> 
            <ul>

                <li><a href="restaurantesNuevos.jsp">Restaurantes</a></li>
                <li><a href="agregarComentarios.jsp">Comentar</a></li>
                <li> <a href="calificacion.jsp">Calificar</a></li>
                <li><a href="">Recomendar</a></li>
            </ul>
        </aside>


        <section>
            <h1 style="color:grey;">Restaurantes preferidos</h1>


            <div class="cajaImagenes">

                <div class="mySlides">
                    <a href="ElCorral.jsp"><img src="img/Corral.jpg" alt="" style="width:100%" ></a>                   
                    <div class="textoImagen">
                        El corral
                    </div>
                </div>

                <div class="mySlides">
                    <a href="Hard Rock Cafe.jsp"><img src="img/HardRock.png" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                        HardRock
                    </div>
                </div>

                <div class="mySlides">
                    <a href="Crepes.jsp"><img src="img/Crepes.jpg" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                        Crepes And Waffles
                    </div>
                </div>


            </div>

            <button class="botonIzquierda" onclick="plusDivs(-1)">&#10094;</button>
            <button class="botonDerecha" onclick="plusDivs(+1)">&#10095;</button>

            <h1 class="titulos" style="opacity: .70;">Todos los restaurantes</h1>

            <% Conexion conec = new Conexion();

                ArrayList<Restaurante> listaRestaurantes = conec.mostrarRestaurante();
                System.out.println(listaRestaurantes.size());
                for (int i = 0; i < listaRestaurantes.size(); i++) {

            %>

            <div id="listaRestaurantesIndex">
                <ul>
                    <li  id="listaPrincipal" type="disc" onclick="plusDivs(<%=i + 1%>)"><a><%=listaRestaurantes.get(i).getNombre()%></a></li>
                </ul>
            </div>
            <%}%>





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
