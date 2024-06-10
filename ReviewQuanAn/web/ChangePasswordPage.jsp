<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Password Change</title>
        <!-- Include Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="Css/Change_Password_Page_Css.css">
    </head>

    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-sm">
                        <div class="card-header text-center">
                            <h4>Change Password</h4>
                        </div>
                        <div class="card-body">
                            <span class="icon">!</span>
                            <div class="popup" id="passwordPopup">
                                Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter,
                                one digit, and one special character.
                            </div>
                            <div id="message" class="alert" style="display: none;"></div>
                            <form id="passwordForm" action="ChangePassword" method="post">
                                <!-- Email Input -->
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" id="email" name="email" class="form-control" placeholder="Enter your email" required>
                                </div>
                                <!-- New Password Input -->
                                <div class="form-group">
                                    <label for="newPassword">New Password:</label>
                                    <input type="password" id="newPassword" name="newPassword" 
                                           class="form-control" placeholder="Enter new password" required
                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                                           title="Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter,
                                one digit, and one special character.">
                                </div>
                                <!-- Confirm New Password Input -->
                                <div class="form-group">
                                    <label for="confirmPassword">Confirm New Password:</label>
                                    <input type="password" id="confirmPassword" name="confirmPassword" 
                                           class="form-control" placeholder="Confirm new password" oninput="validatePasswords()" 
                                           required>
                                </div>
                                <!-- Verify Code Input and Send Code Button -->
                                <div class="form-group">
                                    <label for="verifyCode">Verify Code:</label>
                                    <div class="d-flex">
                                        <input type="text" id="verifyCode" name="verifyCode" class="form-control mr-2" placeholder="Enter Verify Code">
                                        <input type="button" id="sendCodeButton" class="btn btn-primary ml-2" value="Send Code">
                                    </div>
                                </div>
                                <!-- Error Message -->
                                <div id="message1" class="message"></div>
                                <!-- Submit Button -->
                                <input id="submitForm" type="submit" name="submit1" class="btn btn-primary btn-block" value="Change Password">
                                <input id="changePassword" type="hidden" name="service" value="changePassword">
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

        <!-- Include Bootstrap JS (optional) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script src="Script/Change_Password_Page_Script.js"></script>

    </body>

</html>
