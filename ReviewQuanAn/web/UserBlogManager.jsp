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
        <title>Blog Management</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                margin: 0;
                padding: 0;
            }
            .profile-header {
                display: flex;
                align-items: center;
                padding: 20px;
                background-color: #fff;
                border-bottom: 1px solid #ddd;
            }
            .profile-header img {
                border-radius: 50%;
                width: 80px;
                height: 80px;
                margin-right: 20px;
            }
            .profile-header .profile-info {
                display: flex;
                flex-direction: column;
            }
            .profile-header .profile-info h1 {
                margin: 0;
                font-size: 1.8em;
            }
            .profile-header .profile-info p {
                margin: 0;
                color: #555;
            }
            .profile-nav {
                display: flex;
                justify-content: center;
                background-color: #fff;
                border-bottom: 1px solid #ddd;
            }
            .profile-nav a {
                padding: 15px 20px;
                text-decoration: none;
                color: #555;
                border-bottom: 3px solid transparent;
                transition: border-color 0.3s;
            }
            .profile-nav a:hover {
                border-bottom: 3px solid #0079d3;
                color: #0079d3;
                text-decoration: none;
            }
            .profile-nav .active {
                border-bottom: 3px solid #0079d3;
                color: #0079d3;
            }
            .content {
                text-align: center;
                padding: 50px;
                background-color: #fff;
            }
            .content img {
                width: 100px;
                margin-bottom: 20px;
            }
            .content p {
                font-size: 1.2em;
                color: #555;
            }
            .create-post-btn {
                display: flex;
                justify-content: center;
                padding: 20px;
                background-color: #fff;
                border-top: 1px solid #ddd;
            }
            .create-post-btn button {
                background-color: #0079d3;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                font-size: 1em;
            }
            .create-post-btn button:hover {
                background-color: #005bb5;
            }
            .tooltip-inner {
                white-space: pre-wrap; /* Allows for multiline tooltips */
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <!-- Sidebar -->
        <%@include file="./Header.jsp" %>
        <%@include file="./NavbarProfile.jsp" %>

        <c:if test="${requestScope.list.size()==0}">
            <div class="content" style="height:450px"> 
                <img src="img/cute-kawaii-mushroom-chibi-mascot-cartoon-style-vector.jpg" alt="Placeholder Image">
                <p>${requestScope.user.username} hasn't posted yet</p>
            </div>
        </c:if>

        <div class="container mt-4">
            <div class="row">
                <c:forEach items="${requestScope.list}" var="i">
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <c:choose>
                                <c:when test="${requestScope.blog_image.get(i).size() > 1}">
                                    <img class="card-img-top" src="img/${requestScope.blog_image.get(i).get(1).getLink()}" alt="Card image">
                                </c:when>
                                <c:otherwise>
                                    <img class="card-img-top" src="img/6c58498e-b7b1-4a27-8596-c8ec9bceda2c_png-transparent-default-avatar-thumbnail.png" alt="Default image">
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <h4 class="card-title">${i.title}</h4>

                                <div class="d-flex justify-content-between align-items-center mt-2">
                                    <div class="text-muted">${i.create_date}</div>
                                    <c:if test="${requestScope.user.id==sessionScope.User.id}"> 
                                        <c:choose>
                                            <c:when test="${i.is_approved ==2}">
                                                <button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="right" title='This blog is rejected by admin.Reason:${i.reason_reject}'>

                                                    Rejected
                                                </button>
                                            </c:when>
                                            <c:when test="${i.is_approved ==0}">
                                                <button type="button" class="btn btn-warning" data-toggle="tooltip" data-placement="right" title='Waiting for admin approve'>

                                                    Waiting
                                                </button>
                                            </c:when>

                                            <c:when test="${i.is_approved ==4}">
                                                <button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="right" title='Waiting for admin approve'>

                                                    Hidden
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="right" title="This blog is approved by admin">
                                                    Approved
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                    <c:if test="${requestScope.user.id!=sessionScope.User.id}">                         

                                        <a href="BlogPageController?id=${i.id}" class="btn btn-primary">See Detail</a>

                                    </c:if>
                                    <c:if test="${requestScope.user.id==sessionScope.User.id}">                                                          

                                        <c:choose>
                                            <c:when test="${i.id != null}">
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        Actions
                                                    </button>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" href="BlogPageController?id=${i.id}">See Detail</a>
                                                        <a class="dropdown-item" href="BlogEdit?blogId=${i.id}">Reapply Blog</a>
                                                        <c:if test="${i.is_approved==4}">
                                                            <a class="dropdown-item" href="UserUnremoveBlog?user_id=${user.id}&&id=${i.id}">Active Blog</a>
                                                        </c:if>
                                                        <c:if test="${i.is_approved!=4}">
                                                            <a class="dropdown-item" href="UserRemoveBlog?user_id=${user.id}&&id=${i.id}">Hide Blog</a>
                                                        </c:if>


                                                    </div>
                                                </div>
                                            </c:when>

                                        </c:choose> 
                                    </c:if>
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
                                    <a class="page-link" href="UserBlogManagement?user_id=${requestScope.user.id}&&page=${page - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.num}" var="i">
                                <li class="page-item ${i == page ? 'active' : ''}">
                                    <a class="page-link" href="UserBlogManagement?user_id=${requestScope.user.id}&&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${page < requestScope.num}">
                                <li class="page-item">
                                    <a class="page-link" href="UserBlogManagement?user_id=${requestScope.user.id}&&page=${page + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="./Footer.jsp" %>
    </body>
</html>