<%-- 
    Document   : ChangeInformation
    Created on : Nov 8, 2023, 5:02:03 PM
    Author     : Dan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">  
        <style>
            .dropdown-toggle::after {
                content: none;
            }
            .dropdown-toggle {
                background-color: transparent;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
                <%
            User user = (User)session.getAttribute("User");
            if(user == null){
                response.sendRedirect("LoginPage.jsp");
            }
            else if(user.getEmail() == null){
                response.sendRedirect("AfterLogin.jsp");
            }
            else{
        %>
       <%--<c:set var="user" value="${sessionScope.user}"/>--%>
        <div class="container mt-5 mb-5">
            <h1 style="margin-bottom: 40px">Update Profile</h1>
            <form action="change-information" method="post">
                <input value="<%=user.getId()%>" name="id" hidden="">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4" style="font-weight: bold">Email</label>
                        <input type="email" class="form-control" id="inputEmail4" placeholder="<%=user.getEmail()%>" value="${user.email}" name="email">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputUsername4" style="font-weight: bold">Username: </label>
                        <input type="text" class="form-control" id="inputUsername4" placeholder="<%=user.getUsername()%>" value="${user.username}" name="username">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="phone" style="font-weight: bold">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="<%=user.getPhone()%>" value="${user.phone}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="address" style="font-weight: bold">Description: </label>
                        <input type="text" class="form-control" id="address" placeholder="<%=user.getDescription()%>" value="${user.address}" name="address">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlFile1" style="font-weight: bold">Image</label>
                        <input type="file" class="form-control-file" accept="image/*" onchange="loadFile(event)" id="exampleFormControlFile1" name="image">
                        <img src="img/<%=user.getAvatar()%>" id="output" style="width: 200px;height: 200px;object-fit: cover"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary updateBtn">Save</button>
            </form>
        </div>
                            <%}%>
        <%@include file="Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    </body>
    <script>
                        var loadFile = function (event) {
                            var output = document.getElementById('output');
                            output.src = URL.createObjectURL(event.target.files[0]);
                            output.onload = function () {
                                URL.revokeObjectURL(output.src);
                            }
                        };
    </script>   
</html>
