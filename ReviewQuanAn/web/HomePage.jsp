<%-- 
    Document   : HomePage
    Created on : May 14, 2024, 4:28:34 PM
    Author     : lam1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>       
        <script src="https://kit.fontawesome.com/0e60f78292.js" crossorigin="anonymous"></script>
        <title>Home Page </title>
        <style>
            .nav-justified {
                font-size: 19px;

            }
            .login-register-justified{
                margin-right:20px;

            }
            .search-justified{
                padding-left:10%;
            }
            .col{
                border-style: hidden;
                margin-top:5%;
                margin-right: 5%
            }
            footer{
                background-color: #3f3f3f;
                color:#d5d5d5;
            }
            header{
                margin-bottom: 10px;
            }
            .card{
                width:350px;
            }
        </style>

    </head>

    <body>
        <!-- Header begin -->
        <%@include file="./Header.jsp" %>
        <div class="container text-center"><h3>Các quán ăn </h3> </div>

        <div class="container-fluid" >
            <div class="row">
                <c:forEach items="${requestScope.list}" var="i">   
                    <div class ="col-4"> 
                        <div class="card" >
                            <img class="card-img-top" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTskvGE9g2h_ZgPGtkx6I9ziNRsEF9YG0LXJd35ZV-O0Q&s" alt="Card image" style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">${i.title}</h4>
                                <p class="card-text">${i.content}</p>
                                <a href="#" class="btn btn-primary stretched-link">See Blog</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>             
            </div>
        </div>

        <%@include file="./Footer.jsp" %>


    </body>
</html>
