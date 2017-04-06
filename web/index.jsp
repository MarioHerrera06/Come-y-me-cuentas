<%-- 
    Document   : index
    Created on : 9/03/2017, 05:08:02 PM
    Author     : Valentina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@include file="header.jsp" %>
    <%@include file="nav.jsp" %>
    <%@include file="aside.jsp" %>

    <link href="styles/Style.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title >Come y me cuentas</title>




    <body>



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
                    <a href="Crepes.jsp"><img src="img/Crepes.jpg" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                        Crepes And Waffles
                    </div>
                </div>
                
                <div class="mySlides">
                   <a href="Hard Rock Cafe.jsp"><img src="img/HardRock.png" alt="" style="width:100%" ></a>    
                    <div class="textoImagen">
                       HardRock
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
