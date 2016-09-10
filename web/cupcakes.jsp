<%-- 
    Document   : cupcakes
    Created on : Sep 8, 2016, 1:06:07 PM
    Author     : Asger
--%>
<%@page import="java.util.List, entity.user, entity.topping, entity.bottom, entity.invoice" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cupcakes</title>
    </head>
    <body>
        <h1>Cupcakes</h1>
        <%  
            user u = null;
            if(session.getAttribute("user") != null) {
                List<user> userList = (List<user>)session.getAttribute("user");
                u = userList.get(0);
            }
        %>
        <div id="loggedIn">
            <% if(u == null){ %>                    
                    <p>Error!</p>
            <% } else { %>
            <p>You are logged in as <%= u.getUsername() %> <br>Your balance is: <label id="userBalance"><%= u.getBalance() %></label> kr.</p>
            <% } %>
        </div>
        <div id="shoppingCart">
            <h3>Shopping Cart</h3>
            <ul>
                <%
                    double totalPrice = 0;
                    if(session.getAttribute("invoice") != null) {
                        List<invoice> invoiceList = (List<invoice>)session.getAttribute("invoice");
                        int counter = 0;
                        for(invoice in : invoiceList) {
                            %>
                            <li>Topping: <%= in.getTopping()%>, Bottom: <%= in.getBottom()%>, Total Price: <%= in.getTotalPrice() %> kr. <form action="cupcakesServle" method="POST"><input type="submit" value="Remove"><input type="hidden" name="remove" value="<%= counter %>"><input type="hidden" name="origin" value="removeFromCart"></form></li>
                            <%
                            totalPrice = in.getTotalPrice() + totalPrice;
                            counter++;
                        }
                    }
                %>
            </ul>
            <p class="red" id="notEnoughBalance"></p>
            <a id="buyOrder">Buy order</a>
            <p id="totalPrice">Total: <label id="price"><%= totalPrice %></label> kr.</p>
            <% if(request.getAttribute("succes") != null) { %>
                <p class="green">Order was completed</p>
            <% } %>
        </div>
        <%  List<topping> toppingList = null;
            if(request.getAttribute("topping") != null) {
                toppingList = (List<topping>) request.getAttribute("topping");
            }
            List<bottom> bottomList = null;
            if(request.getAttribute("bottom") != null) {
                bottomList = (List<bottom>) request.getAttribute("bottom");
            }
        %>
        <div id="container-center-login">
            <div class="radioStyle" id="container-center-left">
                <h3>Topping</h3>
                <form action="cupcakesServle" method="POST">
                    <% 
                        if(toppingList != null) {
                            for(topping top : toppingList ) {
                                %>
                                <label><input type="radio" name="topping" value="<%= top.getTopping()%>,<%= top.getPrice()%>"><%= top.getTopping() %> <%= top.getPrice()%> kr.</label>
                                <%
                            }
                        }
                    %>
            </div>
            <div class="radioStyle" id="container-center-right">
                <h3>Bottom</h3>
                    <% 
                        if(bottomList != null) {
                            for(bottom bot : bottomList ) {
                                %>
                                <label><input type="radio" name="bottom" value="<%= bot.getBottom()%>,<%= bot.getPrice()%>"><%= bot.getBottom()%> <%= bot.getPrice()%> kr.</label>
                                <%
                            }
                        }
                    %>
                            <input type="submit" name="AddToCart" value="Add to cart" />
                            <input type="hidden" name="origin" value="Add to cart">
                </form>
            </div>
        </div>
        <script src="scripts/js.js" type="text/javascript"></script>
    </body>
</html>
