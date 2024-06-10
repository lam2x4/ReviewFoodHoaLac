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
        <%Vector<String> vector = (Vector<String>) session.getAttribute("srcs");
        for(String src: vector){
        %>
        <img src="<%=src%>" alt="alt"/>
        <%
            }
        %>
        
    </body>
</html>
