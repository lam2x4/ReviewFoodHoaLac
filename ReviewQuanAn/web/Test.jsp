<%-- 
    Document   : Test
    Created on : Jun 2, 2024, 9:14:08 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Password Encryption with bcryptjs</title>
    </head>
    <body>
        <h1>Register</h1>
        <form id="registerForm" action="EncryptPassword" method="post">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Register</button>
        </form>

    </body>
</html>

