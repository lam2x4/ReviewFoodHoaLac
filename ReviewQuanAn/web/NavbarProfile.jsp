<%-- 
    Document   : NavbarProfile
    Created on : Jun 27, 2024, 11:40:52 PM
    Author     : lam1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <div class="profile-header">
            <img src="img/${requestScope.user.avatar}" alt="Profile Picture">
            <div class="profile-info">
                <h1>${requestScope.user.username}</h1>

            </div>
        </div>
        <div class="profile-nav">
         
            <a href="UserBlogManagement?user_id=${requestScope.user.id}">Blog</a>
            <a href="UserReportManagement?user_id=${requestScope.user.id}">Report</a>
            <a href="UserCommentManagement?user_id=${requestScope.user.id}">Comment</a>
            <a href="UserBookmark?user_id=${requestScope.user.id}">Bookmark</a>
        </div>
    </body>
</html>
