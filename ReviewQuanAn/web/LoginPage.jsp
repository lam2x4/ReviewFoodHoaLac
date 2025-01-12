<%-- 
    Document   : LoginPage
    Created on : May 15, 2024, 5:52:48 PM
    Author     : Tuan Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="Css/Login_Page_Css.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="form-container">
                    <h2 class="text-center mb-4">Login</h2>
                    <div id="message" class="alert" style="display: none;"></div>
                    <form action="UserController" method="post">
                        <div class="form-group">
                            <input type="text" name="account" class="form-control" placeholder="Email or Username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Password" required>
                            <a href="ChangePasswordPage.jsp" class="forgot-password">Forgot Password?</a>
                        </div>
                        <div class="form-group d-flex justify-content-between">
                            <input type="submit" name="Login" value="Login" class="btn btn-primary"></input>
                            <input type="reset" name="Clear" value="Clear" class="btn btn-secondary"></input>
                        </div>
                        <input type="hidden" name="service" value="login">
                    </form>
                    <div class="register-link">
                        <p>Don't have an account? <a href="RegisterPage.jsp">Register here</a></p> <!-- Adjust the href to your actual registration page -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="Script/Login_Page_Script.js"></script>
</body>
</html>
