<%-- 
    Document   : BlogPostApplication
    Created on : Jun 3, 2024, 3:05:37 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="https://ckeditor.com" prefix="ckeditor" %>--%>
<%@page import="entity.User"%>
<%@page import="entity.Blog"%>
<%@page import="entity.Images"%>
<%@page import="java.util.Vector"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog Edit</title>
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
                Blog blog = (Blog)request.getAttribute("blog");
                Vector<Images> imgs = (Vector<Images>)request.getAttribute("blogPictures");
                    if(blog == null){
                        out.println("No blog found!");
                        return;
                    }
        %>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-15 col-lg-10">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Edit To Reapply Your Post</h2>
                        <% String message = (String) request.getParameter("message"); %>
                        <% if (message != null && !message.isEmpty()) { %>
                        <div class="alert alert-success">
                            <%= message %>
                        </div>
                        <% } %>
                        <%--<div class="text-center mb-3">
                            <a href="ViewPosts.jsp" class="text-decoration-none">View available posts</a>
                        </div>--%>
                        <form id="apply-post-form" action="upload" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="postTitle">Post Title</label>
                                <textarea id="postTitle" name="postTitle" placeholder="Post Title" required><%= blog.getTitle() %></textarea>
                            </div>
                            <div class="form-group">
                                <label for="postDescription">Post Description</label>
                                <textarea id="postDescription" name="postDescription" placeholder="Post Description" required><%= blog.getContent() %></textarea>
                            </div>
                            <div class="form-group">
                                <label for="billUpload">Upload Bill</label>
                                <input type="file" id="billUpload" name="billUpload" class="form-control-file" accept="image/*">
                                <div class ="preview" id="billPreview">
                                    <% if (imgs != null && !imgs.isEmpty()) { %>
                                    <img class ="preview" src="<%= imgs.get(0).getLink()%>">
                                    <% } %>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="imageUpload">Upload Content Images</label>
                                <input type="file" id="imageUpload" name="imageUpload" class="form-control-file" accept="image/*" multiple>
                                <div class ="preview" id="imagePreview">
                                    <% if (imgs != null && imgs.size() > 1) { 
                                        for (int i = 1; i < imgs.size(); i++) { %>
                                    <img class ="preview" src="<%= imgs.get(i).getLink() %>">
                                    <% } } %>
                                </div>
                            </div>
                            <div class="form-group, spaced-buttons">
                                <input type="submit" name="Reapply" value="Reapply" class="btn btn-primary">
                                <input type="reset" name="Clear" value="Clear" class="btn btn-secondary">
                            </div>

                            <input type="hidden" id="userId" name="userId" value="<%= user.getId() %>">
                            <input type="hidden" id="blogId" name="blogId" value="<%= blog.getId() %>">
                            <input type="hidden" id="service" name="service" value="edit">
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
