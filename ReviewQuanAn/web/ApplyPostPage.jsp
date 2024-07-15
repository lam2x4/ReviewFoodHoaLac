<%-- 
    Document   : BlogPostApplication
    Created on : Jun 3, 2024, 3:05:37 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="https://ckeditor.com" prefix="ckeditor" %>--%>
<%@page import="entity.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apply for Post</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="Css/Apply_Post_Page.css">
    </head>
    <body>
        <%@ include file="./Header.jsp" %>
        <%
            User user = (User)session.getAttribute("User");
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
                <div class="col-md-15 col-lg-10">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Apply for Post</h2>
                        <%--<div class="text-center mb-3">
                            <a href="ViewPosts.jsp" class="text-decoration-none">View available posts</a>
                        </div>--%>
                        <form id="apply-post-form" action="upload" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="postTitle">Post Title</label>
                                <textarea id="postTitle" name="postTitle" placeholder="Post Title" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="postDescription">Post Description</label>
                                <textarea id="postDescription" name="postDescription" placeholder="Post Description" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="billUpload">Upload Bill</label>
                                <input type="file" id="billUpload" name="billUpload" class="form-control-file" accept="image/*" required>
                                <div class ="preview" id="billPreview"></div>
                            </div>
                            <div class="form-group">
                                <label for="imageUpload">Upload Content Images</label>
                                <input type="file" id="imageUpload" name="imageUpload" class="form-control-file" accept="image/*" multiple>
                                <div class ="preview" id="imagePreview"></div>
                            </div>
                            <div id="message" class="alert" style="display: none;"></div>
                            <div class="form-group, spaced-buttons">
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
        <!--<script src="ckeditor5-41.4.2-3tuwcidvt9ht/build/ckeditor.js"></script>-->
        <script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
        <script src="Script/Apply_Post_Page_Script.js"></script>
        <%@ include file="./Footer.jsp" %>
    </body>
</html>
