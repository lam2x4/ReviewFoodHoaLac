<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>User Management</title>
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

            .approved {
                background-color: #d4edda; /* Light green background */
                color: #155724; /* Dark green text */
            }

            .pending {
                background-color: #fff3cd; /* Light yellow background */
                color: #856404; /* Dark yellow text */
            }

            .rejected {
                background-color: #f8d7da; /* Light red background */
                color: #721c24; /* Dark red text */
            }

            .banned {
                background-color: #ffca28; /* Light red background */
                color: #721c24; /* Dark red text */
            }

        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <%@include file="./AdminHeader.jsp" %>



            <div class="container-fluid mt-4">
                <h1 class="mb-4">User Management</h1>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>UserName</th>                                   
                                <th>Email</th>
                                <th>Phone</th>                                   
                                <th>Gender</th>
                                <th>Role</th> 
                                <th>Status</th> 

                            </tr>
                        </thead>

                        <tbody>
                            <!-- Example user row -->
                            <c:forEach items="${requestScope.list}" var="i">
                                <tr>
                                    <td>${i.id}</td>                                       
                                    <td>${i.username}</td>

                                    <td>${i.email}</td>
                                    <td>${i.phone}</td>
                                    <c:if test="${i.gender==1}">
                                        <td>Male</td>
                                    </c:if>
                                    <c:if test="${i.gender==0}">
                                        <td>Female</td>
                                    </c:if>

                                    <c:if test="${i.role_id==1}">
                                        <td>Admin</td>
                                    </c:if>
                                    <c:if test="${i.role_id==2}">
                                        <td>User</td>
                                    </c:if>

                                    <c:if test="${i.verify_status==1}">
                                        <td class ="approved"> <i class="fas fa-circle text-success"></i>Verified</td>
                                    </c:if>
                                    <c:if test="${i.verify_status==0}">
                                        <td class="rejected">  <i class="fas fa-circle text-danger"></i>Not Verified</td>
                                    </c:if>


                                    </td>

                                </tr>
                            </c:forEach>
                            <!-- Add more user rows as needed -->
                        </tbody>

                    </table>
                </div>
                <!-- Pagination -->

                <div class="row">
                    <div class="col-lg-12">
                        <c:set var="page" value="${requestScope.page}"/>
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${page > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="AdminUserManagement?page=${page - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${requestScope.num}" var="i">
                                    <li class="page-item ${i == page ? 'active' : ''}">
                                        <a class="page-link" href="AdminUserManagement?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${page < requestScope.num}">
                                    <li class="page-item">
                                        <a class="page-link" href="AdminUserManagement?page=${page + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>


        </div>
        <!-- /#page-content-wrapper -->
    </div>
    <!-- /#wrapper -->



    <!-- Delete User Modal -->
    <div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteUserModalLabel">Confirm Delete</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete this user?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Delete</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Sidebar toggle
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });

        // Modal population for edit
        $('.edit-btn').click(function () {
            const id = $(this).data('id');
            const username = $(this).data('username');
            const email = $(this).data('email');
            const role = $(this).data('role');
            const status = $(this).data('status');

            $("#editUserModal #username").val(username);
            $("#editUserModal #email").val(email);
            $("#editUserModal #role").val(role);
            $("#editUserModal #status").val(status);
        });

        // Form validation
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                var forms = document.getElementsByClassName('needs-validation');
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();

        // Delete confirmation modal
        $('.delete-btn').click(function () {
            const userEmail = $(this).data('email');
            const id = $(this).data('id');
            $('#confirmDeleteBtn').data('email', userEmail);
            $('#confirmDeleteBtn').data('id', id);
        });

        // Handle delete confirmation
        $('#confirmDeleteBtn').click(function () {
            const userEmail = $(this).data('email');
            const id = $(this).data('id');
            // Perform the delete action (e.g., AJAX request or form submission)
            console.log('Deleting user with Email:', userEmail);
            $('#deleteUserModal').modal('hide');

            // Redirect to homepage.jsp with email parameter after hiding the modal
            $('#deleteUserModal').on('hidden.bs.modal', function () {
                window.location.href = 'AdminDeleteUser?id=' + id;
            });
        });
    </script>
</body>
</html>
