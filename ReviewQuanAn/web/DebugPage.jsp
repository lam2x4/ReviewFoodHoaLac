<%-- 
    Document   : DebugPage -- use for debugging transmition of data
    Created on : May 23, 2024, 8:00:47 AM
    Author     : Tuan Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="java.util.Vector"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            if((Vector<User>) session.getAttribute("data") != null){
                Vector<User> vector = (Vector<User>) session.getAttribute("data");
                for(User user: vector){
        %>
                <p><%=user.toString()%></p>
        <%
                }
            }
            if((User)session.getAttribute("userToVerify") != null){
        %>
                <p><%=((User)session.getAttribute("userToVerify")).toString()%></p>
        <%
            }
        %>
    </body>
</html>
