<%-- 
    Document   : RegisterPage
    Created on : May 20, 2024, 8:17:41 AM
    Author     : Tuan Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.User"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Register Account</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-image: url('https://marketplace.canva.com/EAFA7Zl1wfs/1/0/1600w/canva-pastel-red-green-illustrative-element-centric-video-background-Rs7EVOqIM2c.jpg'); /* Replace with your image URL */
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
            .form-container {
                background: rgba(255, 255, 255, 1);
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-container .form-group input,
            .form-container .form-group select,
            .form-container .form-group textarea {
                border-radius: 20px;
            }
            .form-container .btn {
                border-radius: 20px;
            }
        </style>

    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <div class="form-container">
                        <h2 class="text-center mb-4">Register</h2>
                        <div id="message" class="alert" style="display: none;"></div>
                        <div class="text-center mb-3">
                            <a href="LoginPage.jsp" class="text-decoration-none">Already have an account? Login here</a>
                        </div>
                        <form id="register-form" action="UserController" method="post">
                            <div class="form-group">
                                <input type="text" name="username" class="form-control" placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <input id="password" type="password" name="password" class="form-control" placeholder="Password" oninput="EncryptPassword()" required>
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" placeholder="Email" required>
                            </div>
                            <div class="form-group">
                                <input type="text" name="phone" class="form-control" placeholder="Phone Number" pattern="^[0][0-9]{8,9}$" required>
                            </div>
                            <div class="form-group">Select Gender
                                <select name="gender" class="form-control" required>
                                    <option value="0">Male</option>
                                    <option value="1">Female</option>
                                    <option value="3">Other</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <textarea name="description" class="form-control" rows="4" placeholder="Tell us something about yourself (Optional)"></textarea>
                            </div>
                            <div class="form-group d-flex justify-content-between">
                                <input type="submit" name="Register" value="Register" class="btn btn-primary">
                                <input type="reset" name="Clear" value="Clear" class="btn btn-secondary">
                            </div>
                            <input type="hidden" name="service" value="register">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jsencrypt"></script>
        <script src="https://cdn.rawgit.com/travist/jsencrypt/master/bin/jsencrypt.min.js"></script>

        <script>
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const status = urlParams.get('status');
                const messageDiv = document.getElementById('message');

                if (status === '1') {
                    messageDiv.className = 'alert alert-success';
                    messageDiv.innerHTML = 'Email or Phone number already been registered!';
                    messageDiv.style.display = 'block';
                } else if (status === '2') {
                    messageDiv.className = 'alert alert-danger';
                    messageDiv.innerHTML = 'Account creation failed. Email or phone number might already been taken.';
                    messageDiv.style.display = 'block';
                } else if (status === '3') {
                    messageDiv.className = 'alert alert-danger';
                    messageDiv.innerHTML = 'Something went wrong!';
                    messageDiv.style.display = 'block';
                } else if (status === '4') {
                    messageDiv.className = 'alert alert-danger';
                    messageDiv.innerHTML = 'Register new account or back to login if you registered but have not verified!';
                    messageDiv.style.display = 'block';
                } else if (status === '5') {
                    messageDiv.className = 'alert alert-danger';
                    messageDiv.innerHTML = 'Catch in user controller';
                    messageDiv.style.display = 'block';
                }
            };
            function validatePassword(password) {
                const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                return pattern.test(password);
            }
        </script>
    </body>
</html>

