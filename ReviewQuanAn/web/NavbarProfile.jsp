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
    <style>
        
    </style>
    
    <body>
        <div class="profile-header">
                <img src="img/${sessionScope.User.avatar}" alt="Profile Picture">
                <div class="profile-info">
                    <h1>${sessionScope.User.username}</h1>
                 
                </div>
            </div>
        <div class="profile-nav">
            
            <a href="UserProfile.jsp" >Overview</a>
            <a href="UserBlogManagement">Post</a>
            <a href="UserReportManagement">Report</a>
            
        </div>
    </body>
</html>
