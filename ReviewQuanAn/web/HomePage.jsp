<%-- 
    Document   : HomePage
    Created on : May 14, 2024, 4:28:34 PM
    Author     : lam1
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            .card-title {
                word-wrap: break-word;
                --max-lines: 2;
                display: -webkit-box;
                overflow: hidden;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: var(--max-lines);
                white-space: normal; /* Ensure the text wraps properly */
                text-overflow: ellipsis; /* Add ellipsis for overflow text */
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
                                <c:choose>
                                    <c:when test="${requestScope.blog_image.size() > 1}">
                                        <img class="card-img-top" src="img/${requestScope.blog_image.get(i).get(1).getLink()}" alt="Card image">
                                    </c:when>
                                    <c:otherwise>
                                        <img class="card-img-top" src="img/6c58498e-b7b1-4a27-8596-c8ec9bceda2c_png-transparent-default-avatar-thumbnail.png" alt="Default image">
                                    </c:otherwise>
                                </c:choose>
                                <div class="card-body">
                                    <h4 class="card-title">${i.title}</h4>

                                    <div class="d-flex justify-content-between align-items-center mt-2">
                                        <div class="text-muted" >${i.create_date}</div>
                                        <c:choose>
                                            <c:when test="${i.id != null}">
                                                <a href="BlogPageController?id=${i.id}" class="btn btn-primary">See Detail</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="BlogPageController" class="btn btn-primary">See Detail</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>


                <!-- Pagination -->

                <div class="row">
                    <div class="col-lg-12">
                        <c:set var="page" value="${requestScope.page}"/>
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${page > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="home?page=${page - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${requestScope.num}" var="i">
                                    <li class="page-item ${i == page ? 'active' : ''}">
                                        <a class="page-link" href="home?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${page < requestScope.num}">
                                    <li class="page-item">
                                        <a class="page-link" href="home?page=${page + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>


            </div>
        </section>



    </body>

    <%@include file="./Footer.jsp" %>


</body>

</html>
