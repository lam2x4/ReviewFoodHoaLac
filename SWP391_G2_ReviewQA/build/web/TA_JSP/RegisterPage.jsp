<%-- 
    Document   : RegisterPage
    Created on : May 20, 2024, 8:17:41 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <img src="../Images/Background10.jpg" alt="img"/>
    <form action="../CustomerController" method="post" class="centerElement">
        <p>
            Register as:
        <select name="role">
            <option value="customer">CUSTOMER</option>
            <option value="storeManager">STORE MANAGER</option>
        </select>
        </p>
        
        <p><input type="text" name="name" placeholder="name"></p>
        <p><input type="text" name="account" placeholder="email"></p>
        <p><input type="password" name="password" placeholder="password"></p>
        <p><input type="submit" name="Register" value="Register">
            <input type="reset" name="Clear" value="Clear"> </p>

        <input type="hidden" name="service" value="register">
    </form>
</html>
