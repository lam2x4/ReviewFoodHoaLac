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
        <title>Admin DashBoard</title>
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            #wrapper {
                display: flex;
                width: 100%;
            }

            #sidebar-wrapper {
                min-height: 100vh;
                margin-left: -250px;
                transition: margin 0.25s ease-out;
            }

            #wrapper.toggled #sidebar-wrapper {
                margin-left: 0;
            }

            #page-content-wrapper {
                width: 100%;
            }

            .sidebar-heading {
                padding: 0.875rem 1.25rem;
                font-size: 1.2rem;
                font-weight: bold;
            }

            .list-group-item {
                border: none;
            }

            .list-group-item-action {
                padding: 0.75rem 1.25rem;
            }

            .list-group-item-action:hover {
                background-color: #007bff;
                color: #ffffff;
            }


            .card {
                border-radius: 10px;
            }

            .card-header {
                font-weight: bold;
                font-size: 1.25rem;
            }

            .shadow-sm {
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075) !important;
            }

            .card-body ul {
                padding-left: 0;
            }

            .card-body ul li {
                margin-bottom: 10px;
                font-size: 16px;
            }

            .card-body ul li i {
                margin-right: 10px;
            }

            .card-title i {
                margin-right: 10px;
                color: #007bff;
            }

            .card-title {
                font-size: 2rem;
                display: flex;
                align-items: center;
            }

            .navbar-brand {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .navbar-nav .nav-item .nav-link {
                font-size: 1.1rem;
                font-weight: 500;
            }

            .navbar-nav .nav-item .nav-link:hover {
                color: #007bff;
            }

            .card img {
                max-width: 100%;
                border-radius: 10px;
            }

            .border-right{
                height:742px;
            }

        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-dark border-right" style="min-height:742px ;height: auto">
                <div class="sidebar-heading text-white"><i class="fas fa-tachometer-alt"></i> Admin Dashboard </div>
                <div class="list-group list-group-flush">
                    <a href="Admin" class="list-group-item list-group-item-action bg-dark text-white "  ><i class="fas fa-home"></i> DashBoard</a>
                    <a href="AdminUserManagement" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-users"></i> User </a>
                    <a href="AdminBlogManagement" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-file-alt"></i> Blog</a>
                    <a href="AdminReportManagement" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-flag"></i> Report</a>
                    <a href="home" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-comments"></i> HomePage</a>

                </div>
            </div>
            <div id="page-content-wrapper">
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">


                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="ProfileAdmin.jsp"><i class="fas fa-user-circle"></i> Profile</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <!-- /#sidebar-wrapper -->





                </body>
                </html>
