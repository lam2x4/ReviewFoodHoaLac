<%-- 
    Document   : Header
    Created on : May 21, 2024, 6:09:02 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>       
        <script src="https://kit.fontawesome.com/0e60f78292.js" crossorigin="anonymous"></script>
        <title>Home Page </title>
        <style>
            .nav-justified {
                font-size: 19px;

            }
            .login-register-justified{
                margin-right:20px;

            }
            .search-justified{
                padding-left:10%;
            }
            .col{
                border-style: hidden;
                background-color: lavender;
                margin-top:5%;
                margin-right: 5%
            }
            footer{
                background-color: #3f3f3f;
                color:#d5d5d5;
            }
        </style>
    </head>

    <body>
        <footer>
            <div class="container-fluid" style="margin-top:5%" >

                <div class="row text-center " >
                    <div class="col-md-4"><h3> Thành viên </h3></div> 
                    <div class="col-md-4"><h3> Giới thiệu</h3></div>   
                </div>
                <div class="row text-center " >
                    <div class="col-md-4"><p> Trịnh Hoàng Việt <p></div>    
                    <div class="col-md-6"> <p>Địa chỉ: Hòa Lạc , Thạch Thất , Hà Nội. Số điện thoại hỗ trợ:  03234123423 - Email:haha@gmail.com</p></div>
                </div>
                <div class="row text-center " >
                    <div class="col-md-4"><p> Triệu Bảo Lâm<p></div> 
                    <div class="col-md-7"> <p>Đây là website review quán ăn hàng đầu Hòa Lạc . Với mong ước giúp các bạn tìm được những quán ăn chất lượng</p></div>

                </div>
                <div class="row text-center " >
                    <div class="col-md-4"><p> Tuấn Anh <p></div>              
                </div>
                <div class="row text-center " >
                    <div class="col-md-4"><p> Hoàng Anh <p></div>              
                </div>
                <div class="row text-center " >
                    <div class="col-md-4"><p> Vũ Trung Kiên <p></div>              
                </div>
            </div>
        </footer>
    </body>
</html>
