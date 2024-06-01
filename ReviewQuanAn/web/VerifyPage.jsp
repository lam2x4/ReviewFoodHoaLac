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
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2 class="text-center mb-4">We have sent an email to <%=(String)user.getEmail()%></h2>
                    <div id="message" class="alert" style="display: none;"></div>
                    <form id="verificationForm" action="UserController?service=verify" method="post">
                        <div class="form-row justify-content-center">
                            <input name="Vcode" type="text" maxlength="6" required>
                        </div>
                        <input type="submit" name="submit" value="submit" class="btn btn-submit btn-block">
                    </form>
                    <button class="btn btn-resend btn-block">Resend Verification Code</button>
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







