<%-- 
    Document   : footer
    Created on : 9/03/2017, 04:34:25 PM
    Author     : Valentina
--%>

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
   <footer>
    <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
        <img alt="Licencia de Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nd/4.0/80x15.png" /></a><br />Este obra está bajo una <a rel="license" href="http://creativecommons.org/licenses/by-nd/4.0/">
        licencia de Creative Commons Reconocimiento-SinObraDerivada 4.0 Internacional</a>.
</footer>

</html>
