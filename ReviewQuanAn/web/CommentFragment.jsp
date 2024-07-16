<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.*, java.util.Vector"%>

<%Vector<Comment> comments = (Vector<Comment>) request.getAttribute("blogComments");
    Vector<String> avatars = (Vector<String>) request.getAttribute("commentAvatars");
    Vector<String> convertedDates = (Vector<String>)request.getAttribute("commentsDates");
    String loggedInUsername = (String)request.getAttribute("commentUsername");%>
<%for(int i = 0; i < comments.size(); i++){%>
<input type="hidden" id="commentId-<%=comments.get(i).getId()%>" value="<%=comments.get(i).getId()%>">
<div class="comment" id="comment-<%=comments.get(i).getId()%>">
    <div class="thumbnail">
        <a class="toProfile">
            <img src="img/<%=avatars.get(i)%>" alt="Profile Picture" class="profile-pic">
        </a>
    </div>
    <div class="comment-body">
        <p>
            <a href="UserBlogManagement?user_id=<%=comments.get(i).getUser_id()%>" class="profile-link"><%=comments.get(i).getUsername()%></a>
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
            <button class="rating" id="like-button-<%=comments.get(i).getId()%>" 
                    aria-pressed="<%if(loggedInUsername == null){%>false<%}else{comm_inter_type.get(i).equals("like");}%>" <%if(loggedInUsername != null){%> 
                    onclick="toggleCommentLike(<%=comments.get(i).getId()%>)"<%}%>>
                <%if(loggedInUsername != null && comm_inter_type.get(i).equals("like")){%><i class="fa-solid fa-thumbs-up"></i><%}else{%><i class="fa-regular fa-thumbs-up"></i><%}%>
            </button>
            <span id="likeCommentCount-<%=comments.get(i).getId()%>"><%=comments.get(i).getLikes()%> likes</span>
            <button class="rating" id="dislike-button-<%=comments.get(i).getId()%>" 
                    aria-pressed="<%if(loggedInUsername == null){%>false<%}else{comm_inter_type.get(i).equals("dislike");}%>" <%if(loggedInUsername != null){%> 
                    onclick="toggleCommentDislike(<%=comments.get(i).getId()%>)"<%}%>>
                <%if(loggedInUsername != null && comm_inter_type.get(i).equals("dislike")){%><i class="fa-solid fa-thumbs-down"></i><%}else{%><i class="fa-regular fa-thumbs-down"></i><%}%>
            </button>
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
