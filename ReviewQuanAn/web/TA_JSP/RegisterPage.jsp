<%-- 
    Document   : RegisterPage
    Created on : May 20, 2024, 8:17:41 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <form action="../UserController" method="post">
                        <div class="form-group">
                            <p>Register As</p>
                            <select name="role" class="form-control" required>
                                <option value="3">Customer</option>
                                <option value="2">Store Manager</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="Name" required>
                        </div>
                        <div class="form-group">
                            <input type="text" name="username" class="form-control" placeholder="Username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" placeholder="Email" required>
                        </div>
                        <div class="form-group">
                            <input type="tel" name="phone" class="form-control" placeholder="Phone Number" required>
                        </div>
                        <div class="form-group">
                            <select name="gender" class="form-control" required>
                                <option value="" disabled selected>Select Gender</option>
                                <option value="0">Male</option>
                                <option value="1">Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <textarea name="description" class="form-control" rows="4" placeholder="Tell us something about yourself (Optional)"></textarea>
                        </div>
                        <div class="form-group d-flex justify-content-between">
                            <input type="submit" name="Register" value="Register" class="btn btn-primary"></input>
                            <input type="reset" name="Clear" value="Clear" class="btn btn-secondary"></input>
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
</body>
</html>
