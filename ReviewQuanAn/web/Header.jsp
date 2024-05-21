<%-- 
    Document   : Header
    Created on : May 21, 2024, 6:09:02 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                background-color: lavender;
                margin-top:5%;
                margin-right: 5%
            }
            header{
                margin-bottom: 10px;
            }
        </style>

    </head>

    <body>
        <!-- Header begin -->
        <header>
            <!-- Navigation bar -->
            <nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
                <!-- Brand -->
                <a class="navbar-brand" href="#"> 
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPrBt0UbsRdMwTbJ0L4X4QIrbzl6hKLBUlPhQfk0GiGw&s" class="rounded-circle" width="100px"></a>

                <!-- Toggler/collapsibe Button -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Navbar links -->

                <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item nav-justified">
                            <a class="nav-link " href="#">Trang chủ</a>
                        </li>

                        <li class="nav-item nav-justified">
                            <a class="nav-link " href="#">Quán ăn</a>
                        </li>
                        <li class="nav-item nav-justified ">
                            <a class="nav-link" href="#">Liên hệ</a>
                        </li>


                    </ul>
                    <form class="form-inline search-justified" action="#">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search">
                        <button class="btn btn-success" type="submit">Tìm kiếm</button>
                    </form>
                </div>

                <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#" > Đăng quán ăn    <i class="fa-solid fa-pen"></i> </a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#" > Đăng nhập   <i class="fa-solid fa-right-to-bracket"></i> </a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#">Đăng ký   <i class="fa-solid fa-feather"></i></a>
                        </li>

                    </ul>
                </div>
            </nav>
        </header>
    </body>
</html>
