<%-- 
    Document   : ProfileUser
    Created on : Jun 5, 2024, 10:50:53 AM
    Author     : kyler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="styles.css">
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

        </style>
    </head>
    <body>
                    <!-- Sidebar -->
            <div class="bg-dark border-right" id="sidebar-wrapper">
                <div class="sidebar-heading text-white"><i class="fas fa-tachometer-alt"></i> Admin Dashboard </div>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-home"></i> Home</a>
                    <a href="profile.html" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-user"></i> Profile</a>
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-users"></i> Users</a>
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-file-alt"></i> Posts</a>
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-comments"></i> Comments</a>
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-flag"></i> Reports</a>
                    <a href="#" class="list-group-item list-group-item-action bg-dark text-white"><i class="fas fa-cogs"></i> Settings</a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->
    </body>
</html>
