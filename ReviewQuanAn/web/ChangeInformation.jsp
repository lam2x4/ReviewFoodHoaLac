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
    <title>Profile Update</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/0e60f78292.js" crossorigin="anonymous"></script> <!-- Add Font Awesome -->
    <style>
        .sidebar {
            background-color: #f8f9fa;
            padding: 20px;
            margin-top: 20px;
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
            margin-top: 20px;
        }
        .preview {
            display: flex;
            flex-wrap: wrap;
            overflow-y: auto;
            height: 200px;
            width: 200px;
            padding: 10px;
            border: 1px solid #ccc;
        }
        .container-fluid{
            margin-bottom: 50px;
        }
        
    </style>
</head>
<body>
    <%@include file="Header.jsp" %>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 sidebar">
                <a href="ChangeInformation.jsp"><i class="fas fa-user"></i> Profile</a>
                <a href="posts.jsp"><i class="fas fa-pencil-alt"></i> Posts</a>
                <a href="report.jsp"><i class="fas fa-chart-bar"></i> Report</a>
                <a href="bookmark.jsp"><i class="fas fa-bookmark"></i> Bookmark</a>
            </div>

            <!-- Main Content -->
            <div class="col-md-10 main-content">
                <%
                User user = (User)session.getAttribute("User");
                if(user == null){
                   response.sendRedirect("LoginPage.jsp");
                }
                else if(user.getEmail() == null){
                    response.sendRedirect("AfterLogin.jsp");
                }
                else {
                %>

                <div class="container mt-5 mb-5">
                    <h1 style="margin-bottom: 40px">Update Profile</h1>
                    <form action="change-information" method="post" id="change-information-form" enctype="multipart/form-data">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4" style="font-weight: bold">Email</label>
                                <input type="email" class="form-control" id="inputEmail" placeholder="<%=user.getEmail()%>" name="email">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputUsername4" style="font-weight: bold">Username: </label>
                                <input type="text" class="form-control" id="inputUsername" placeholder="<%=user.getUsername()%>" name="username">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="phone" style="font-weight: bold">Phone</label>
                                <input type="text" class="form-control" id="phone" name="phone" placeholder="<%=user.getPhone()%>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="address" style="font-weight: bold">Description: </label>
                                <input type="text" class="form-control" id="address" placeholder="<%=user.getDescription()%>" name="address">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="avatar" style="font-weight: bold">Avatar: </label>
                            </div>
                            <img src="img/<%= user.getAvatar()%>" alt="Avatar" style="width: 150px; height: 150px;">
                        </div>
                        <div class="form-row">
                            <label for="avatarUpload" style="font-weight: bold">Change Avatar:</label>
                            <input type="file" id="avatarUpload" name="avatarUpload" class="form-control-file" accept="image/*" multiple>
                            <div class="preview" id="avatarPreview"></div>
                        </div>
                        <button type="submit" class="btn btn-primary updateBtn">Save</button>
                    </form>
                </div>
                <%}%>
            </div>
        </div>
    </div>

    <%@include file="Footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    <script src="Script/Change_Information_Script.js"></script>
    <script>
        var loadFile = function (event) {
            var output = document.getElementById('output');
            output.src = URL.createObjectURL(event.target.files[0]);
            output.onload = function () {
                URL.revokeObjectURL(output.src);
            }
        };
    </script>
</body>
</html>
