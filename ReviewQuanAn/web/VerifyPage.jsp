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
        <style>
            body {
                background-color: #f8f9fa; /* Soft background color */
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Soft and rounded font */
            }

            .container {
                margin-top: 50px;
            }

            h2 {
                color: #007bff; /* Blue heading */
                font-weight: bold;
            }

            .form-control {
                text-align: center;
                max-width: 50px;
                height: 50px;
                font-size: 24px;
                border-radius: 20px; /* Rounder corners */
                border: 2px solid #007bff; /* Blue border */
                margin: 0 5px;
            }

            .form-control:focus {
                outline: none;
                border-color: #0056b3; /* Darker blue on focus */
            }

            .btn-resend {
                background-color: #ffc107; /* Yellow button */
                color: #212529;
                border: none;
                border-radius: 20px; /* Rounder corners */
                padding: 10px 20px;
                font-size: 16px;
                margin-top: 20px;
                transition: background-color 0.3s ease;
            }

            .btn-resend:hover {
                background-color: #ffca28; /* Lighter yellow on hover */
            }

            .btn-submit {
                background-color: #007bff; /* Blue submit button */
                color: #fff;
                border: none;
                border-radius: 20px; /* Rounder corners */
                padding: 10px 20px;
                font-size: 16px;
                margin-top: 20px;
                transition: background-color 0.3s ease;
            }

            .btn-submit:hover {
                background-color: #0056b3; /* Darker blue on hover */
            }

            .error-message {
                color: #dc3545; /* Red color for error message */
                font-size: 14px;
                margin-top: 10px;
            }
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
        <script>
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const status = urlParams.get('status');
                const messageDiv = document.getElementById('message');

                if (status === '1') {
                    messageDiv.className = 'alert alert-success';
                    messageDiv.innerHTML = 'Verify code is incorrect!';
                    messageDiv.style.display = 'block';
                }
                else if (status === '4') {
                    messageDiv.className = 'alert alert-success';
                    messageDiv.innerHTML = 'Something went wrong. Try again';
                    messageDiv.style.display = 'block';
                }
            };
        </script>

    </body>
</html>







