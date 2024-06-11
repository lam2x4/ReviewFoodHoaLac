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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <title>Home Page </title>
        <style>
            body {
                font-family: 'Open Sans', sans-serif;
            }

            .navbar-brand {
                font-weight: bold;
                display: flex;
                align-items: center;
            }

            .navbar-brand img {
                width: 40px;
                margin-right: 10px;
            }

            .hero {
                background: url('https://marketplace.canva.com/EAFA7Zl1wfs/1/0/1600w/canva-pastel-red-green-illustrative-element-centric-video-background-Rs7EVOqIM2c.jpg') no-repeat center center;
                background-size: cover;
                color: white;
                padding: 100px 0;
            }

            .hero .display-4 {
                font-weight: 700;
            }

            .hero .btn-primary {
                background-color: #007bff;
                border: none;
            }

            .latest-articles .card {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
            }

            .latest-articles .card:hover {
                transform: translateY(-10px);
            }

            .latest-articles .card-title {
                font-size: 1.25rem;
                font-weight: 700;
            }

            .latest-articles .card-text {
                font-size: 1rem;
                color: #555;
            }

            .latest-articles .btn-primary {
                background-color: #007bff;
                border: none;
            }

            .pagination {
                justify-content: center;
            }

            footer {
                background-color: #3f3f3f;
                color: #d5d5d5;
                padding: 20px 0;
            }

            footer .list-inline-item {
                margin: 0 10px;
            }

            footer .list-inline-item a {
                color: #d5d5d5;
                font-size: 1.5rem;
            }

            footer .list-inline-item a:hover {
                color: #fff;
            }

            /* Ensuring all images have the same width and height */
            .col-md-4 .card-img-top {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }
            .col-md-4 .card-img-top {
                width: 100%;
                height: 200px;
                object-fit: cover;
            }
        </style>

    </head>

    <body>
        <!-- Header begin -->
        <header>
            <!-- Navigation bar -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="home">
                    <img src="https://img.freepik.com/free-vector/bird-colorful-logo-gradient-vector_343694-1365.jpg?size=338&ext=jpg&ga=GA1.1.34264412.1716346800&semt=sph" style="width: 60px" alt="Logo">
                    HoaLac3School
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home"><i class="fas fa-home"></i> Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="upload"><i class="fas fa-book-open"></i> Add Blog</a>
                        </li>
                        <c:choose>
                            <c:when test="${sessionScope.User!=null}">
                                <a class="nav-link" href="change-information"><i class="fas fa-user-circle"></i></i> Profile</a>
                                <a class="nav-link" href="UserController?service=logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                            </c:when>
                            <c:when test="${sessionScope.Admin!=null}">
                                <a class="nav-link" href="change-information"><i class="fas fa-user-circle"></i></i> Profile</a>
                                <a class="nav-link" href="#"><i class="fas fa-sign-out-alt"></i> Logout</a>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="LoginPage.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="RegisterPage.jsp"><i class="fas fa-user-plus"></i> Register</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div>
            </nav>
        </header>
    </body>
</html>
