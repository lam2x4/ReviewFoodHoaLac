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
        <title>User Bookmark</title>
        <style>
            .bookmark-button {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
                border-radius: 5px;
                padding: 5px 10px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .bookmark-button:hover {
                background-color: #c3e6cb;
            }
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
            .tooltip-inner {
                white-space: pre-wrap; /* Allows for multiline tooltips */
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
                <img src="img/hinh-anh-c_ac6fe9c1-2cad-493e-8ab4-1aa7b92bc64a.jpg" alt="Placeholder Image">
                <p>${requestScope.pageUser.username} hasn't bookmarked yet</p>
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
                                    <c:if test="${requestScope.pageUser.id!=sessionScope.User.id}">                         
                                        <a href="BlogPageController?id=${i.id}" class="btn btn-primary">See Detail</a>
                                    </c:if>
                                    <c:if test="${requestScope.pageUser.id==sessionScope.User.id}">                                                          
                                        <c:choose>
                                            <c:when test="${i.id != null}">
                                                <button type="button" class="bookmark-button" id="bookmarkButton" onclick="toggleBookmark(${i.id}, '${i.title}')">
                                                    <i id="bookmarkButtonIcon" class="fas fa-bookmark"></i>
                                                    <span id="bookmarkButtonText">Unbookmark</span>
                                                </button>
                                                <a href="BlogPageController?id=${i.id}" class="btn btn-primary">See Detail</a>
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
                                    <a class="page-link" href="UserBookmark?user_id=${requestScope.pageUser.id}&&page=${page - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${requestScope.num}" var="i">
                                <li class="page-item ${i == page ? 'active' : ''}">
                                    <a class="page-link" href="UserBookmark?user_id=${requestScope.pageUser.id}&&page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${page < requestScope.num}">
                                <li class="page-item">
                                    <a class="page-link" href="UserBookmark?user_id=${requestScope.pageUser.id}&&page=${page + 1}" aria-label="Next">
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
    <script>
        function toggleBookmark(id, title) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'BookmarkController?blogId=' + id, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        console.log("Bookmark successfully removed.");
                        location.reload();
                        //updateList();
                    }
                } else {
                    console.error("Bookmark toggle failed. Status: " + xhr.status);
                }
            };
            if (confirm("Are you sure? You're unbookmarking: " + title)) {
                xhr.send('service=removeBookmark');
            } else {
                console.log("Action cancelled by user.");
            }
        }
    </script>
</html>