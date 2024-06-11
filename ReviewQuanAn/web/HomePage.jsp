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
            .card{
                width:350px;
            }
        </style>

    </head>

    <body>
        <!-- Header begin -->
        <%@include file="./Header.jsp" %>
    <body>


        <!-- Hero Section -->
        <div class="hero text-white text-center py-5">
            <div class="container">
                <h1 class="display-3">Welcome to Review Food Store in Hoa Lac</h1>
                <p class="lead">Your source for the latest reviews and blog posts</p>
                <a href="#" class="btn btn-primary btn-lg">Learn More</a>
            </div>
        </div>

        <!-- Latest Articles Section -->
        <section class="latest-articles py-5">
            <div class="container">
                <div class="row mb-4">
                    <div class="col-md-6">
                        <form action="Search" method="post">
                            <input name="search" type="text" class="form-control" placeholder="Search for articles...">
                            <input type="submit" name="search" value="Search">
                            <input type="hidden" name="submit" value="search">
                        </form>
                    </div>
                    <div class="col-md-6 text-right">
                        <select class="form-control w-50 d-inline">
                            <option value="">Filter by category</option>
                            <option value="food">Food</option>
                            <option value="travel">Travel</option>
                            <option value="lifestyle">Lifestyle</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${requestScope.list}" var="i">
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <img class="card-img-top" src="https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNDEzNjJ8MHwxfGFsbHwyfHx8fHx8fHwxNjIwODkwNTI5&ixlib=rb-1.2.1&q=80&w=400" alt="Card image">
                                <div class="card-body">
                                    <h4 class="card-title">${i.title}</h4>
                                    <p class="card-text">${i.content}</p>
                                    <a href="BlogPageController?id=${i.id}" class="btn btn-primary">See Detail</a>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>
                <!-- Pagination -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        </section>


        <!-- Bootstrap and FontAwesome scripts -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

    <%@include file="./Footer.jsp" %>


</body>
</html>
