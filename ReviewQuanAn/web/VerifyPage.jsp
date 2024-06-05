<%-- 
    Document   : VerifyPage
    Created on : May 22, 2024, 7:54:01 PM
    Author     : Tuan Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verification Code Input</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="Css/Verify_Page_Css.css">
        <style>
        </style>
    </head>
    <body>
        <%
            User user = (User)session.getAttribute("userToVerify");
            if(user == null){
                response.sendRedirect("RegisterPage.jsp?status=4");
            }
            else if(user.getEmail() == null){
                response.sendRedirect("AfterLogin.jsp");
            }
            else{
        %>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-md">

                        <div class="popup">
                            <span class="icon">!</span>
                            <span class="popuptext" id="popupMessage">
                                The system have saved your account information.
                                If you could not complete verification in this session, you can continue the next time you login.
                            </span>
                        </div>
                        <div class="card-header text-center">
                            <h2 class="text-center mb-4">We have sent an email to <%=user.getEmail()%> </h2>
                        </div>
                        <div class="card-body">
                            <div id="message" class="alert" style="display: none;"></div>
                            <form id="verificationForm" action="UserController?service=verify" method="post">
                                <div class="form-row justify-content-center">
                                    <input name="Vcode" type="text" maxlength="6" placeholder="Enter Verification Code" required>
                                </div>
                                <input type="submit" name="submit" value="Submit" class="btn btn-primary btn-block mt-3">
                            </form>
                            <form action="UserController" method="post" class="mt-2">
                                <input class="btn btn-secondary btn-block" type="submit" value="Resend Verify Code">
                                <input type="hidden" name="service" value="resendVerifyCode">
                            </form>
                            <!-- Back to Login Page Link -->
                            <div class="text-center mt-3">
                                <a href="LoginPage.jsp" class="btn btn-link">Back to login page</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%}%>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Script/Verify_Page_Script.js"></script>

    </body>
</html>







