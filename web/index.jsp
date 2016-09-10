<%-- 
    Document   : index
    Created on : Sep 8, 2016, 11:55:14 AM
    Author     : Asger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Login site</title>
    </head>
    <body>
        <div id="container-center-login">
                <% 
                   if(request.getParameter("invalid") != null) {
                %> 
            <p class="red">Your username or password is invalid, try again!</p>
                <%
                   }
                %>
            <form action="loginServlet" method="POST">
                <input type="text" name="username" placeholder="Username">
                <input type="password" name="password" placeholder="Password">
                <input type="hidden" name="hidden" value="login">
                <input type="submit" name="submit" value="Login">
            </form>
        </div>
    </body>
</html>
