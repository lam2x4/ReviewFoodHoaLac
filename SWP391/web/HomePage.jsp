<%-- 
    Document   : HomePage
    Created on : May 14, 2024, 4:28:34 PM
    Author     : lam1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            header{
                margin-bottom: 10px;
            }
        </style>

    </head>

    <body>
        <!-- Header begin -->
        <header >
            <!-- Navigation bar -->
            <nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
                <!-- Brand -->
                <a class="navbar-brand" href="#"> 
                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPrBt0UbsRdMwTbJ0L4X4QIrbzl6hKLBUlPhQfk0GiGw&s" class="rounded-circle" width="100px"></a>

                <!-- Toggler/collapsibe Button -->
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Navbar links -->

                <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item nav-justified">
                            <a class="nav-link " href="#">Trang chủ</a>
                        </li>

                        <li class="nav-item nav-justified">
                            <a class="nav-link " href="#">Quán ăn</a>
                        </li>
                        <li class="nav-item nav-justified ">
                            <a class="nav-link" href="#">Liên hệ</a>
                        </li>


                    </ul>
                    <form class="form-inline search-justified" action="#">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search">
                        <button class="btn btn-success" type="submit">Tìm kiếm</button>
                    </form>
                </div>

                <div class="collapse navbar-collapse justify-content-center" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#" > Đăng quán ăn    <i class="fa-solid fa-pen"></i> </a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#" > Đăng nhập   <i class="fa-solid fa-right-to-bracket"></i> </a>

                        </li>
                        <li class="nav-item">
                            <a class="nav-link login-register-justified " href="#">Đăng ký   <i class="fa-solid fa-feather"></i></a>
                        </li>

                    </ul>
                </div>
            </nav>

            <!--Body begin-->

        </header>
        <div class="container text-center"><h3>Các quán ăn </h3> </div>

        <div class="container" >
            <div class="row">
                <div class="col"> Mình sẽ post thông tin quán ở đây  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
            </div>
            <div class="row">
                <div class="col"> Mình sẽ post thông tin quán ở đây  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
            </div>
            <div class="row">
                <div class="col"> Mình sẽ post thông tin quán ở đây  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
            </div>
            <div class="row">
                <div class="col"> Mình sẽ post thông tin quán ở đây  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
                <div class="col"> gege  </div>
            </div>




        </div>

        <footer  >
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
