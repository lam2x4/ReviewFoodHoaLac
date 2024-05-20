<%-- 
    Document   : LoginPage
    Created on : May 15, 2024, 5:52:48 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
        </style>
    </head>
    <body>
    <form action="../CustomerController" method="post" class="centerElement">
        <p><input type="text" name="account" placeholder="email"></p>

        <p><input type="password" name="password" placeholder="password"></p>

        <p><input type="submit" name="Login" value="Login">
            <input type="reset" name="Clear" value="Clear"> </p>

        <input type="hidden" name="service" value="login">
    </form>
    </table>
</body>
</html>
