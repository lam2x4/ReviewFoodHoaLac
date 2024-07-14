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
        <%Vector<Comment> comments = (Vector<Comment>)request.getAttribute("blogComments");
          Vector<Images> imgs = (Vector<Images>)request.getAttribute("blogPictures");
          Vector<String> avatars = (Vector<String>)request.getAttribute("commentAvatars");
          Vector<String> convertedDates = (Vector<String>)request.getAttribute("commentsDates");
          String loggedInUsername = (String)request.getAttribute("commentUsername");%>
        <div class="post-container">
            <div class="post-header">
                <img src="<%=(String)request.getAttribute("profPic")%>" alt="Profile Picture" class="profile-pic">
                <div class="user-info">
                    <h2><a href="" class="profile-link"><%=(String)request.getAttribute("username")%></a></h2>
                    <p id="blogPublishDate"><%=(String)request.getAttribute("publishDate")%></p>
                </div>
            </div>
            <h2 class="post-title"><%=(String)request.getAttribute("blogTitle")%></h2>
            <p class="post-content"><%=(String)request.getAttribute("blogContent")%></p>
            <div class="post-images" id="post-images">
                <%for(int i = 0; i < imgs.size(); i++){%>
                <img src="<%=imgs.get(i).getLink()%>" id="image" class="post-image" onclick="openModal();currentSlide(<%=i + 1%>)">
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
                <input type="hidden" id="blogLikes" value="<%=(int)request.getAttribute("blogLikes")%>">
                <span id="likeCount" class="count"><i class="fa-solid fa-thumbs-up"></i> Likes: <%=(int)request.getAttribute("blogLikes")%></span>
                <span id="commentCount" class="count"><i class="fa-solid fa-comment"></i> Comments: 0</span>
            </div>
            <hr>
            <div class="rate-share">
                <div class="rating">
                    <%String post_interaction_type = (String)request.getAttribute("postLikeInteractionType") != null ? (String)request.getAttribute("postLikeInteractionType") : "nothing";%>
                    <button class="button likeBtn" role="button" id="like-button" aria-pressed="false">Like</button>
                    <button class="button dislikeBtn" role="button" id="dislike-button" aria-pressed="false">Dislike</button>
                </div>
                <form action="upload?bid=<%=(String)request.getAttribute("blogId")%>" method="post">
                    <input type="submit" class="button" role="button" value="Share">
                    <input type="hidden" name="service" value="repost">
                </form>
            </div>
            <hr>
            <div class="comment-section">
                <div class="sort-options">
                    <label for="sort-comments"><i class="fa-solid fa-sort"></i> Sort by: </label>
                    <select id="sort-comments" name="sort-comments" onchange="sortComments()">
                        <option value="date">Date</option>
                        <option value="popular">Popular</option>
                    </select>
                </div>
                <div class="comment-box" id="comment-box">
                    <img src="<%=(String)request.getAttribute("commentProfPic")%>" alt="Profile Picture" class="profile-pic" id="UserPP">
                    <input type="hidden" id="Username" value="<%=(String)request.getAttribute("commentUsername")%>">
                    <input type="hidden" id="BlogId" value="<%=(String)request.getAttribute("blogId")%>">
                    <form id="commentForm">
                        <textarea type="text" name="comment-input" id="comment-input" class="glowing-input" placeholder="Add a comment..."></textarea>
                        <div class="buttons">
                            <button class="button" type="button" id="cancel-button">Cancel</button>
                            <button class="button" type="submit" name="btnsubmit" value="add-comment" id="add-comment-button">Add Comment</button>
                        </div>
                        <input type="hidden" name="service" value="addComment">
                    </form>
                </div>
                <div class="comment-list" id="comment-list">
                    <%for(int i = 0; i < comments.size(); i++){%>
                    <input type="hidden" id="commentId-<%=comments.get(i).getId()%>" value="<%=comments.get(i).getId()%>">
                    <div class="comment" id="comment-<%=comments.get(i).getId()%>">
                        <div class="thumbnail">
                            <a class="toProfile">
                                <img src="<%=avatars.get(i)%>" alt="Profile Picture" class="profile-pic">
                            </a>
                        </div>
                        <div class="comment-body">
                            <p>
                                <a href="" class="profile-link"><%=comments.get(i).getUsername()%></a>
                                <span class="comment-date"><%=convertedDates.get(i)%></span>
                            </p>
                            <p style="word-wrap: break-word;">
                                <span id="commentContent-<%=comments.get(i).getId()%>"><%=comments.get(i).getContent()%></span>
                                <textarea class="editCommentInput" id="editCommentInput-<%=comments.get(i).getId()%>" style="display: none;"></textarea>
                            </p>
                            <div id="editCommentControls-<%=comments.get(i).getId()%>" style="display: none;">
                                <button class="save-button button" onclick="saveEditedComment(<%=comments.get(i).getId()%>)">Save</button>
                                <button class="cancel-button button" onclick="cancelEditComment(<%=comments.get(i).getId()%>)">Cancel</button>
                            </div>
                            <div class="comment-actions">
                                <%Vector<String> comm_inter_type = (Vector<String>)request.getAttribute("commentsLikeInteractionType");%>
                                <input type="hidden" id="commentLikes-<%=comments.get(i).getId()%>" value="<%=comments.get(i).getLikes()%>">
                                <button class="rating" id="like-button-<%=comments.get(i).getId()%>" aria-pressed="false"><i class="fa-regular fa-thumbs-up"></i></button>
                                <span id="likeCommentCount-<%=comments.get(i).getId()%>"><%=comments.get(i).getLikes()%> likes</span>
                                <button class="rating" id="dislike-button-<%=comments.get(i).getId()%>" aria-pressed="false"><i class="fa-regular fa-thumbs-down"></i></button>
                            </div>
                            <%if(loggedInUsername != null && loggedInUsername.equals(comments.get(i).getUsername())){%>
                            <div id="editOptionsDiv-<%=comments.get(i).getId()%>">
                                <button class="menu-btn"><i class="fa-solid fa-ellipsis-vertical"></i></button>
                                <ul class="menu-options" id="editOptions-<%=comments.get(i).getId()%>" style="display: none;">
                                    <li onclick="toggleEditComment(<%=comments.get(i).getId()%>, '<%=comments.get(i).getContent()%>')"><i class="fa-regular fa-pen-to-square"></i>Edit</li>
                                    <li onclick="deleteComment(<%=comments.get(i).getId()%>)"><i class="fa-solid fa-trash"></i>Delete</li>
                                </ul>
                            </div>
                            <%}%>
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
            <%if(loggedInUsername == null){%>
                                        const commentBox = document.getElementById('comment-box');
                                        commentBox.style.display = "none";
            <%}else{%>
                                        const likeBtn = document.getElementById('like-button');
                                        likeBtn.onclick = toggleLike;
                                        likeBtn.setAttribute('aria-pressed', <%=post_interaction_type.equals("like")%>);

                                        const dislikeBtn = document.getElementById('dislike-button');
                                        dislikeBtn.onclick = toggleDislike;
                                        dislikeBtn.setAttribute('aria-pressed', <%=post_interaction_type.equals("dislike")%>);

            <%Vector<String> comm_inter_type = (Vector<String>)request.getAttribute("commentsLikeInteractionType");%>
            <%for(int i = 0; i < comments.size(); i++){%>
                                        //toggle comment like btn
                                        var commentLikeBtn = document.getElementById('like-button-<%=comments.get(i).getId()%>');
                                        commentLikeBtn.onclick = function () {
                                            toggleCommentLike(<%=comments.get(i).getId()%>);
                                        };
                                        commentLikeBtn.setAttribute('aria-pressed', <%=comm_inter_type.get(i).equals("like")%>);

            <%if(comm_inter_type.get(i).equals("like")){%>
                                        commentLikeBtn.innerHTML = '<i class="fa-solid fa-thumbs-up"></i>';
            <%}else{%>
                                        commentLikeBtn.innerHTML = '<i class="fa-regular fa-thumbs-up"></i>';
            <%}%>
                                        //toggle comment dislike btn
                                        var commentDisikeBtn = document.getElementById('dislike-button-<%=comments.get(i).getId()%>');
                                        commentDisikeBtn.onclick = function () {
                                            toggleCommentDislike(<%=comments.get(i).getId()%>);
                                        };
                                        commentDisikeBtn.setAttribute('aria-pressed', <%=comm_inter_type.get(i).equals("dislike")%>);

            <%if(comm_inter_type.get(i).equals("dislike")){%>
                                        commentDisikeBtn.innerHTML = '<i class="fa-solid fa-thumbs-down"></i>';
            <%}else{%>
                                        commentDisikeBtn.innerHTML = '<i class="fa-regular fa-thumbs-down"></i>';
            <%}%>
            <%}%>
            <%}%>
        </script>
    </body>
</html>
