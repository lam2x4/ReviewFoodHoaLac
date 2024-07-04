<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
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
    </style>
</head>
<body>
<%@ include file="./Header.jsp" %>

<%@ include file="./NavbarProfile.jsp" %>



<div class="content" style="height:450px"> 
    <img src="img/cute-kawaii-mushroom-chibi-mascot-cartoon-style-vector.jpg" alt="Placeholder Image">
    <p>u/No-Butterscotch-1291 hasn't posted yet</p>
</div>
<%@ include file="./Footer.jsp" %>
</body>
</html>
