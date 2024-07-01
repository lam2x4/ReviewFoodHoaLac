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
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
            }
            .profile-header {
                display: flex;
                align-items: center;
                padding: 20px;
                background-color: #fff;
                border-bottom: 1px solid #ddd;
            }
            .profile-header img {
                border-radius: 50%;
                width: 80px;
                height: 80px;
                margin-right: 20px;
            }
            .profile-header .profile-info {
                display: flex;
                flex-direction: column;
            }
            .profile-header .profile-info h1 {
                margin: 0;
                font-size: 1.8em;
            }
            .profile-header .profile-info p {
                margin: 0;
                color: #555;
            }
            .profile-nav {
                display: flex;
                justify-content: center;
                background-color: #fff;
                border-bottom: 1px solid #ddd;
            }
            .profile-nav a {
                padding: 15px 20px;
                text-decoration: none;
                color: #555;
                border-bottom: 3px solid transparent;
                transition: border-color 0.3s;
            }
            .profile-nav a:hover {
                border-bottom: 3px solid #0079d3;
                color: #0079d3;
                text-decoration: none;
            }
            .profile-nav .active {
                border-bottom: 3px solid #0079d3;
                color: #0079d3;
            }
            .content {
                text-align: center;
                padding: 50px;
                background-color: #fff;
            }
            .content img {
                width: 100px;
                margin-bottom: 20px;
            }
            .content p {
                font-size: 1.2em;
                color: #555;
            }
            .create-post-btn {
                display: flex;
                justify-content: center;
                padding: 20px;
                background-color: #fff;
                border-top: 1px solid #ddd;
            }
            .create-post-btn button {
                background-color: #0079d3;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1em;
            }
            .create-post-btn button:hover {
                background-color: #005bb5;
            }
            .tooltip-inner {
                white-space: pre-wrap; /* Allows for multiline tooltips */
            }
        </style>
    </head>
    <body>
        <%@ include file="Header.jsp" %>
        <%@include file="NavbarProfile.jsp" %>
        <div class="content">       
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
                                    <th>Blog</th>
                                    <th>Reason</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.reportList}" var="r">

                                    <tr>
                                        <td>${r.id}</td>
                                        <td>${requestScope.report_User.get(r.user_id)}</td>
                                        <td><a href="BlogPageController?id=${r.blog_id}" class="text-info">${r.blog_id}</a></td>
                                        <td>${requestScope.report_ReportType.get(r.type_id)}</td>
                                        <td>${r.content}</td>                                       
                                        <td>${r.create_date} </td>
                                        <c:choose>

                                            <c:when test = "${r.is_approved==0}">
                                             <td>Waiting </td>
                                            </c:when>

                                            <c:when test = "${r.is_approved==1}">
                                               <td>Approved </td>
                                            </c:when>
                                                <c:when test = "${r.is_approved==2}">
                                               <td>Rejected </td>
                                            </c:when>

                                          
                                        </c:choose>
                                       
                                    </tr>

                                </c:forEach>
                                <!-- Add more post rows as needed -->
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
