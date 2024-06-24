<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reports</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0e60f78292.js" crossorigin="anonymous"></script> <!-- Add Font Awesome -->
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            display: flex;
            flex: 1;
        }
        .sidebar {
            background-color: #f8f9fa;
            padding: 20px;
            width: 250px;
        }
        .sidebar a {
            display: block;
            padding: 10px;
            color: #000;
            text-decoration: none;
            margin-bottom: 10px;
            border-radius: 4px;
        }
        .sidebar a:hover {
            background-color: #e9ecef;
            text-decoration: none;
        }
        .main-content {
            padding: 20px;
            flex: 1;
        }
        .table-container {
            margin-top: 20px;
        }
        footer {
            background-color: #f8f9fa;
            padding: 10px;
            text-align: center;
            width: 100%;
            position: fixed;
            bottom: 0;
        }
    </style>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="content">
        <!-- Sidebar -->
        <div class="sidebar">
            <a href="ChangeInformation.jsp"><i class="fas fa-user"></i> Profile</a>
            <a href="posts.jsp"><i class="fas fa-pencil-alt"></i> Posts</a>
            <a href="report.jsp"><i class="fas fa-chart-bar"></i> Report</a>
            <a href="bookmark.jsp"><i class="fas fa-bookmark"></i> Bookmark</a>
        </div>

        <!-- Main Content -->
        <div class="main-content">
            <div class="container mt-5 mb-5">
                <h1>Reports</h1>
                <div class="table-container">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Author</th>
                                <th>Content</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${reportList}" var="report">
                                <tr>
                                    <td>${report.id}</td>
                                    <td>${report.title}</td>
                                    <td>${report.author}</td>
                                    <td>${report.content}</td>
                                    <td>${report.date}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <footer>
        <%@ include file="Footer.jsp" %>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0V
