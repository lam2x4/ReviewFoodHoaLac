<%-- 
    Document   : StorePage
    Created on : May 21, 2024, 6:03:04 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Blog, entity.Comment, entity.Images, java.util.Vector"%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Blog Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="Css/Blog_Page_Css.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!-- Navigation Bar -->
        <%@ include file="./Header.jsp" %>
        <%Vector<Comment> comments = (Vector<Comment>)request.getAttribute("blogComments");
          Vector<Images> imgs = (Vector<Images>)request.getAttribute("blogPictures");%>
        <div class="post-container">
            <div class="post-header">
                <img src="<%=(String)request.getAttribute("profPic")%>" alt="Profile Picture" class="profile-pic">
                <div class="user-info">
                    <h2><a href="" class="profile-link"><%=(String)request.getAttribute("username")%></a></h2>
                    <p><%=(String)request.getAttribute("publishDate")%></p>
                </div>
            </div>
            <h2 class="post-title"><%=(String)request.getAttribute("blogTitle")%></h2>
            <p class="post-content"><%=(String)request.getAttribute("blogContent")%></p>
            <div class="post-images" id="post-images">
                <%for(int i = 0; i < imgs.size(); i++){%>
                <img src="<%=imgs.get(i).getLink()%>" class="post-image" onclick="openModal();currentSlide(<%=i + 1%>)">
                <%}%>
            </div>

            <div id="myModal" class="modal">
                <span class="close cursor" onclick="closeModal()"><i class="fa-regular fa-circle-xmark"></i></span>
                <div class="modal-content">

                    <%for(int i = 0; i < imgs.size(); i++){ %>
                    <div class="mySlides">
                        <div class="numbertext"><%=i+1%> / <%=imgs.size()%></div>
                        <img src="<%=imgs.get(i).getLink()%>" style="width:100%">
                    </div>
                    <%}%>

                    <a class="prev" onclick="plusSlides(-1)"><i class="fa-solid fa-chevron-left"></i></a>
                    <a class="next" onclick="plusSlides(1)"><i class="fa-solid fa-chevron-right"></i></a>
                </div>
            </div>
            <hr>
            <div class="post-sub-info">
                <span id="likeCount" class="count"><i class="fa-solid fa-thumbs-up"></i> Likes: <%=(int)request.getAttribute("blogLikes")%></span>
                <span id="commentCount" class="count">Comments: 0</span>
            </div>
            <hr>
            <div class="like-comment-share">
                <button class="button" role="button" id="like-button">Like</button>
                <button class="button" role="button" id="commentButton">Comment</button>
                <button class="button" role="button">Share</button>
            </div>
            <hr>
            <div class="comment-section">
                <div class="comment-box" id="comment-box">
                    <img src="<%=(String)request.getAttribute("commentProfPic")%>" alt="Profile Picture" class="profile-pic">
                    <input type="text" id="comment-input" class="glowing-input" placeholder="Add a comment...">
                    <div class="buttons">
                        <button class="button" id="cancel-button">Cancel</button>
                        <button class="button" id="add-comment-button">Add Comment</button>
                    </div>
                </div>
                <div class="comment-list" id="comment-list">
                    <%for(Comment comm : comments){%>
                    <div class="comment">
                        <div class="thumbnail">
                            <a class="toProfile">
                                <img src="img/photo_7_2024-06-06_11-09-40.jpg" alt="Profile Picture" class="profile-pic">
                            </a>
                        </div>
                        <div class="comment-body">
                            <p><a href="" class="profile-link"><%=comm.getUsername()%></a></p>
                            <p><%=comm.getContent()%></p>
                            <div class="comment-actions">
                                <button class="rating"><i class="fa-regular fa-thumbs-up"></i></button>   
                                <%=comm.getLikes()%> likes   
                                <button class="rating"><i class="fa-regular fa-thumbs-down"></i></button>
                                <button class="reply-button" onclick="showReplyInput(this)">Reply</button>
                                <div class="replies" id="replies"></div>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
        <%@ include file="./Footer.jsp" %>
        <script src="Script/Blog_Page_Script.js"></script>
        <script>
            updateCommentCount();
        </script>
    </body>
</html>
