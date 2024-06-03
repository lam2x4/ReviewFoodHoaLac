<%-- 
    Document   : BlogPostApplication
    Created on : Jun 3, 2024, 3:05:37 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apply for Post</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-image: url('https://static.vecteezy.com/system/resources/previews/008/877/711/original/food-icons-colored-food-background-doodle-illustration-with-food-icon-free-vector.jpg');
                background-size: auto;
                background-position: center;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Soft and rounded font */
            }

            .container {
                margin-top: 50px;
                margin-bottom: 50px;
            }

            .form-container {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .form-container h2 {
                color: #343a40;
            }

            #message {
                margin-bottom: 20px;
            }

            .form-group input,
            .form-group textarea,
            .form-group select {
                border-radius: 5px;
                border: 1px solid #ced4da;
            }

            .form-group input:focus,
            .form-group textarea:focus,
            .form-group select:focus {
                border-color: #80bdff;
                outline: none;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
            }

            .form-group input[type="submit"],
            .form-group input[type="reset"] {
                border-radius: 5px;
                padding: 10px 20px;
            }

            .form-group input[type="submit"] {
                background-color: #007bff;
                border: none;
                color: #ffffff;
            }

            .form-group input[type="reset"] {
                background-color: #6c757d;
                border: none;
                color: #ffffff;
            }

            .form-group input[type="submit"]:hover,
            .form-group input[type="reset"]:hover {
                opacity: 0.8;
            }

            .text-center a {
                color: #007bff;
            }

            .text-center a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("userToVerify");
            if(user == null){
                response.sendRedirect("LoginPage.jsp");
            }
            else if(user.getEmail() == null){
                response.sendRedirect("AfterLogin.jsp");
            }
            else{
        %>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Apply for Post</h2>
                        <div id="message" class="alert" style="display: none;"></div>
                        <div class="text-center mb-3">
                            <a href="ViewPosts.jsp" class="text-decoration-none">View available posts</a>
                        </div>
                        <form id="apply-post-form" action="upload" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="text" name="postTitle" class="form-control" placeholder="Post Title" required>
                            </div>
                            <div class="form-group">
                                <textarea name="postDescription" class="form-control" rows="4" placeholder="Post Description" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="postImages">Upload Images</label>
                                <input type="file" name="postImages" class="form-control-file" id="postImages" accept="image/*" multiple>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="Apply" value="Apply" class="btn btn-primary">
                                <input type="reset" name="Clear" value="Clear" class="btn btn-secondary">
                            </div>
                            <input type="hidden" id="userId" name="userId" value="<%= user.getId() %>">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%}%>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Script/Apply_Post_Page_Script.js"></script>
    </body>
</html>
