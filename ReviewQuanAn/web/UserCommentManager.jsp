<%-- 
    Document   : VerifyPage
    Created on : May 22, 2024, 7:54:01 PM
    Author     : Tuan Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <title>Comment Section</title>
    <style>
        .comment-section {
            background-color: #f9f9f9;
            border-radius: 8px;
            padding: 15px;
            margin: 20px 0;
            display: block; /* Ensure the anchor covers the whole div */
            color: inherit; /* Inherit text color */
            text-decoration: none; /* Remove underline from the link */
        }
        .comment-section:hover {
            text-decoration: none; /* Remove underline on hover */
        }
        .comment-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .comment-header img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;
        }
        .comment-header .comment-author {
            font-weight: bold;
            margin-right: 10px;
        }
        .comment-body {
            margin-bottom: 10px;
        }
        .comment-footer {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .comment-footer .comment-actions {
            display: flex;
            align-items: center;
        }
        .comment-footer .comment-actions i {
            margin-right: 5px;
            cursor: pointer;
        }
        .comment-footer .comment-time {
            font-size: 0.9em;
            color: gray;
        }
        .comment-time a {
            color: black; /* Make the link text black */
            text-decoration: none; /* Remove underline */
        }
        .comment-time a:hover {
            text-decoration: underline; /* Underline on hover */
        }
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
    <%@include file="Header.jsp" %>
    <%@include file="NavbarProfile.jsp" %>
     <c:if test="${requestScope.commentList.size()==0}">
            <div class="content" style="height:450px"> 
                <img src="img/hinh-anh-c_ac6fe9c1-2cad-493e-8ab4-1aa7b92bc64a.jpg" alt="Placeholder Image">
                <p>${requestScope.user.username} hasn't comment yet</p>
            </div>
        </c:if>
<div class="container">
    <c:forEach items="${requestScope.commentList}" var="i">
        <div class="comment-section">
            <div class="comment-header">  
                <img src="img/${requestScope.user.avatar}" alt="User Avatar"> <!-- Adjust the path as needed -->
                <a href="BlogPageController?id=${i.blog_id}" class="comment-time text-info" >${requestScope.Comment_Blog.get(i.getBlog_id())}</a>
            </div>
            <div class="comment-body">
                <p><strong>${requestScope.Comment_User.get(i.getUser_id())} commented on ${i.create_date}</strong></p>
                <p>${i.content}</p>
            </div>
        </div>
    </c:forEach>
</div>
    <%@include file="Footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
