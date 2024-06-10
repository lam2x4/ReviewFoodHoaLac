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
        <title>Admin DashBoard</title>
        <style>
            body {
                background-color: #f8f9fa;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            #wrapper {
                display: flex;
                width: 100%;
            }

            #sidebar-wrapper {
                min-height: 100vh;
                margin-left: -250px;
                transition: margin 0.25s ease-out;
            }

            #wrapper.toggled #sidebar-wrapper {
                margin-left: 0;
            }

            #page-content-wrapper {
                width: 100%;
            }

            .sidebar-heading {
                padding: 0.875rem 1.25rem;
                font-size: 1.2rem;
                font-weight: bold;
            }

            .list-group-item {
                border: none;
            }

            .list-group-item-action {
                padding: 0.75rem 1.25rem;
            }

            .list-group-item-action:hover {
                background-color: #007bff;
                color: #ffffff;
            }


            .card {
                border-radius: 10px;
            }

            .card-header {
                font-weight: bold;
                font-size: 1.25rem;
            }

            .shadow-sm {
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075) !important;
            }

            .card-body ul {
                padding-left: 0;
            }

            .card-body ul li {
                margin-bottom: 10px;
                font-size: 16px;
            }

            .card-body ul li i {
                margin-right: 10px;
            }

            .card-title i {
                margin-right: 10px;
                color: #007bff;
            }

            .card-title {
                font-size: 2rem;
                display: flex;
                align-items: center;
            }

            .navbar-brand {
                font-size: 1.5rem;
                font-weight: bold;
            }

            .navbar-nav .nav-item .nav-link {
                font-size: 1.1rem;
                font-weight: 500;
            }

            .navbar-nav .nav-item .nav-link:hover {
                color: #007bff;
            }

            .card img {
                max-width: 100%;
                border-radius: 10px;
            }

            .border-right{
                height:742px;
            }

        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <%@include file="./AdminHeader.jsp" %>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->


            <div class="container-fluid mt-4">
                <h1 class="mb-4">Dashboard</h1>
                <div class="row">
                    <div class="col-md-4">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-users"></i><a href="#" style="color:#343A40">Users</a> </div>

                            <div class="card-body">
                                <h5 class="card-title">${requestScope.userNumber}</h5>
                                <p class="card-text">Total registered users.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-file-alt"></i><a href="#" style="color:#343A40">Posts</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.blogNumber}</h5>
                                <p class="card-text">Total posts created.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-comments"></i><a href="#" style="color:#343A40">Comments</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.commentNumber}</h5>
                                <p class="card-text">Total comments made.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Waiting Blog -->   
                <div class="card mt-4 shadow-sm">
                    <div class="card-header"><i class="fas fa-history"></i> Waiting Blog</div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Author</th>
                                    <th>Content</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.BlogList}" var="i" >
                                    <c:if test="${i.getIs_approved()==0}" > 
                                        <tr>
                                            <td>${i.id}</td>
                                            <td>${i.title}</td>
                                            <td>${requestScope.Blog_User.get(i.user_id)}</td>
                                            <td>${i.content}</td>
                                            <td>${i.create_date}</td>
                                            <td id="status-${i.id}">Waiting</td>
                                            <td>

                                                <button class="btn btn-warning btn-sm status-btn" data-toggle="modal" data-target="#statusModal" data-id="${i.id}" data-status="2"><i class="fas fa-ban"></i> Reject</button>
                                                <button class="btn btn-success btn-sm status-btn" data-toggle="modal" data-target="#statusModal" data-id="${i.id}" data-status="1"><i class="fas fa-check"></i> Approved</button>

                                            </td>

                                        </tr>
                                    </c:if> 
                                </c:forEach>
                                <!-- Add more post rows as needed -->
                            </tbody>
                        </table>
                        <!-- Status Modal -->
                        <div class="modal fade" id="statusModal" tabindex="-1" role="dialog" aria-labelledby="statusModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="statusModalLabel">Confirm Action</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="statusModalMessage">Do you want to ban this blog?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-primary" id="confirmStatusBtn">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- /#page-content-wrapper -->
        </div>
        <!-- /#wrapper -->



    </body>
    <script>
        // Handle status button click
        $('.status-btn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#confirmStatusBtn').data('id', id);
            $('#confirmStatusBtn').data('status', status);
            $('#statusModalMessage').text(`Do you want to ${status.toLowerCase()} this blog?`);
        });

        // Handle confirm status button click
        $('#confirmStatusBtn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#statusModal').modal('hide');

            // Redirect to haha servlet with post id
            window.location.href = `AdminWaitingBlog?id=` + id + "&status=" + status;
        });
    </script>
</html>
