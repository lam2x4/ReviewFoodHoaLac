<%-- 
    Document   : StorePage
    Created on : May 21, 2024, 6:03:04 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.*, java.util.Vector"%>

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
    <% 
        Vector<Comment> comments = (Vector<Comment>)request.getAttribute("blogComments");
        Vector<Images> imgs = (Vector<Images>)request.getAttribute("blogPictures");
        Vector<String> avatars = (Vector<String>)request.getAttribute("commentAvatars");
    %>
    <div class="post-container">
        <div class="post-header d-flex justify-content-between align-items-center">
            <div>
                <img src="img/<%=(String)request.getAttribute("profPic")%>" alt="Profile Picture" class="profile-pic">
                <div class="user-info">
                    <h2><a href="" class="profile-link"><%=(String)request.getAttribute("username")%></a></h2>
                    <p><%=(String)request.getAttribute("publishDate")%></p>
                </div>
            </div>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#reportModal">
                Report
            </button>
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
            <button class="button likeBtn" role="button" id="like-button" onclick="toggleLike()" aria-pressed="false">Like</button>
            <button class="button" role="button" id="commentButton">Comment</button>
            <button class="button" role="button">Share</button>
        </div>
        <hr>
        <div class="comment-section">
            <div class="comment-box" id="comment-box">
                <img src="img/<%=(String)request.getAttribute("commentProfPic")%>" alt="Profile Picture" class="profile-pic">
                <input type="text" id="comment-input" class="glowing-input" placeholder="Add a comment...">
                <div class="buttons">
                    <button class="button" id="cancel-button">Cancel</button>
                    <button class="button" id="add-comment-button" onclick="updateCommentCount();postComment('<%=(String)request.getAttribute("commentUsername")%>', '<%=(String)request.getAttribute("commentProfPic")%>')">Add Comment</button>
                </div>
            </div>
            <div class="comment-list" id="comment-list">
                <%for(int i = 0; i < comments.size() && i < avatars.size(); i++){%>
                <div class="comment">
                    <div class="thumbnail">
                        <a class="toProfile">
                            <img src="<%=avatars.get(i)%>" alt="Profile Picture" class="profile-pic">
                        </a>
                    </div>
                    <div class="comment-body">
                        <p><a href="" class="profile-link"><%=comments.get(i).getUsername()%></a> <%=comments.get(i).getCreate_date()%></p>
                        <p><%=comments.get(i).getContent()%></p>
                        <div class="comment-actions">
                            <button class="rating"><i class="fa-regular fa-thumbs-up"></i></button>   
                            <%=comments.get(i).getLikes()%> likes   
                            <button class="reply-button" onclick="showReplyInput(this, '<%=(String)request.getAttribute("commentUsername")%>', '<%=(String)request.getAttribute("commentProfPic")%>')">Reply</button>
                            <div class="replies" id="replies"></div>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>
    <%@ include file="./Footer.jsp" %>

    <!-- Report Modal -->
    <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="reportModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="reportModalLabel">Report Post</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="Report" method="post">
                        <div class="form-group">
                            <label for="reportReason">Reason for reporting:</label>
                            <select class="form-control" id="reportReason" name="reportReason">
                                <c:forEach items="${requestScope.listReportType}" var="l">
                                    <option value="${l.id}" >${l.name}</option>
                                    
                                </c:forEach>
                                
                                
                            </select>
                        </div>
                        <div style="display: none"><input type="text" value="${requestScope.blogId}" name="URL"></div> 
                        <div class="form-group">
                            <label for="reportDetails">Details (optional):</label>
                            <textarea class="form-control" id="reportDetails" name="reportDetails" rows="3"></textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Submit Report</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="Script/Blog_Page_Script.js"></script>
    <script>
        updateCommentCount();
    </script>
</body>
</html>
