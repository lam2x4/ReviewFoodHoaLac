<%-- 
    Document   : ChangeInformation
    Created on : Jun 8, 2024, 2:08:57 PM
    Author     : kyler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-5 mb-5">
            <h1 style="margin-bottom: 40px">Change Information Profile</h1>
            <form action="change-information" method="post">
                <input value="${user.accountId}" name="id" hidden="">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail4" style="font-weight: bold">Email</label>
                        <input type="email" class="form-control" id="inputEmail4" placeholder="Email" value="${user.email}" name="email">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputUsername4" style="font-weight: bold">Username: </label>
                        <input type="text" class="form-control" id="inputUsername4" placeholder="Username" value="${user.username}" name="username">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="fullname" style="font-weight: bold">Fullname</label>
                        <input type="text" class="form-control" id="fullname" name="fullname" value="${user.fullname}">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputPassword4" style="font-weight: bold">Password</label>
                        <input type="text" class="form-control" id="inputPassword4" placeholder="Password" value="${user.password}" name="password">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="phone" style="font-weight: bold">Phone</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="address" style="font-weight: bold">Address: </label>
                        <input type="text" class="form-control" id="address" placeholder="Adderss" value="${user.address}" name="address">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlFile1" style="font-weight: bold">Image</label>
                        <input type="file" class="form-control-file" accept="image/*" onchange="loadFile(event)" id="exampleFormControlFile1" name="image">
                        <img src="img/${user.avatar}" id="output" style="width: 200px;height: 200px;object-fit: cover"/>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary updateBtn">Save</button>
            </form>
        </div>
    </body>
</html>
