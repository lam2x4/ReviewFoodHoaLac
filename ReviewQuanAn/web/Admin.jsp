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

            .border-right {
                height: 742px;
            }
            .text-ellipsis {
                display: inline-block;
                max-width: 20ch; /* Set maximum width to 20 characters */
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                vertical-align: bottom; /* Align text vertically within the table cell */
            }
            .text-info {
                word-wrap: break-word;
                max-width: 300px;
                --max-lines: 1;
                display: -webkit-box;
                overflow: hidden;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: var(--max-lines);
                white-space: normal; /* Ensure the text wraps properly */
                text-overflow: ellipsis; /* Add ellipsis for overflow text */
            }
            
            .pending {
                background-color: #fff3cd; /* Light yellow background */
                color: #856404; /* Dark yellow text */
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
                    <div class="col-md-3">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-users"></i><a href="#" style="color:#343A40">Users</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.userNumber}</h5>
                                <p class="card-text">Total registered users.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-file-alt"></i><a href="#" style="color:#343A40">Blogs</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.blogNumber}</h5>
                                <p class="card-text">Total blogs created.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fas fa-comments"></i><a href="#" style="color:#343A40">Comments</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.commentNumber}</h5>
                                <p class="card-text">Total comments made.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card text-dark bg-light mb-3 shadow-sm">
                            <div class="card-header"><i class="fa-solid fa-flag"></i><a href="#" style="color:#343A40">Reports</a></div>
                            <div class="card-body">
                                <h5 class="card-title">${requestScope.reportNumber}</h5>
                                <p class="card-text">Total reports made.</p>
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
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.BlogList}" var="i">
                                    <c:if test="${i.getIs_approved() == 0}">
                                        <tr>
                                            <td>${i.id}</td>
                                            <td><a href="AdminBlogPage?id=${i.id}" class="text-info">${i.title}</a></td>
                                            <td>${requestScope.Blog_User.get(i.user_id)}</td>
                                            <td>${i.create_date}</td>
                                            <td id="status-${i.id}" class="pending"> <i class="fas fa-circle text-warning"></i> Waiting</td>
                                            <td>
                                                <button class="btn btn-danger btn-sm reject-btn" data-toggle="modal"
                                                        data-target="#rejectModal" data-id="${i.id}" data-status="2"><i class="fas fa-ban"></i> Reject
                                                </button>
                                                <button class="btn btn-success btn-sm status-btn" data-toggle="modal"
                                                        data-target="#statusModal" data-id="${i.id}" data-status="1"><i class="fas fa-check"></i> Approve
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <!-- Add more post rows as needed -->
                            </tbody>
                        </table>
                        <!-- Status Modal -->
                        <div class="modal fade" id="statusModal" tabindex="-1" role="dialog" aria-labelledby="statusModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="statusModalLabel">Confirm Action</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="statusModalMessage">Do you want to approve this blog?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-primary" id="confirmStatusBtn">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Reject Modal -->
                        <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="rejectModalLabel">Reason for Rejection</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form id="rejectForm" action="AdminWaitingBlog" >
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="rejectReason">Reason:</label>

                                                <input type="text" class="form-control" id="blogId" name="id" hidden="true">
                                                <input type="text" class="form-control" id="status" name="status"  hidden="true">
                                                <textarea class="form-control" id="rejectReason" name="reason" rows="3"
                                                          required></textarea>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                       <!-- Pagination -->

                <div class="row">
                    <div class="col-lg-12">
                        <c:set var="page" value="${sessionScope.page}"/>
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${page > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="Admin?page=${page - 1}&&pageReport=${sessionScope.pageReport}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${sessionScope.num}" var="i">
                                    <li class="page-item ${i == page ? 'active' : ''}">
                                        <a class="page-link" href="Admin?page=${i}&&pageReport=${sessionScope.pageReport}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${page < sessionScope.num}">
                                    <li class="page-item">
                                        <a class="page-link" href="Admin?page=${page + 1}&&pageReport=${sessionScope.pageReport}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- Waiting Report -->
                <div class="card mt-4 shadow-sm">
                    <div class="card-header"><i class="fas fa-flag"></i> Waiting Report</div>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Reported By</th>
                                    <th>Blog</th>
                                    <th>Reason</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.reportList}" var="r">
                                    <c:if test="${r.is_approved == 0}">
                                        <tr>
                                            <td>${r.id}</td>
                                            <td>${requestScope.report_User.get(r.user_id)}</td>
                                            <td><a href="AdminBlogPage?id=${r.blog_id}" class="text-info">${requestScope.report_Blog.get(r.blog_id)}</a></td>
                                            <td>${requestScope.report_ReportType.get(r.type_id)}</td>
                                            <td>${r.content}</td>                                       
                                            <td>${r.create_date}</td>
                                              <td id="status-${r.id}" class="pending"> <i class="fas fa-circle text-warning"></i> Waiting</td>
                                            <td>
                                                <button class="btn btn-danger btn-sm reject-btn" data-toggle="modal"
                                                        data-target="#rejectReportModal" data-id="${r.id}" data-status="2"><i class="fas fa-ban"></i> Reject
                                                </button>
                                                <button class="btn btn-success btn-sm status-btn" data-toggle="modal"
                                                        data-target="#statusReportModal" data-id="${r.id}" data-status="1"><i class="fas fa-check"></i> Approve
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <!-- Add more report rows as needed -->
                            </tbody>
                        </table>

                        <!-- Status Report Modal -->
                        <div class="modal fade" id="statusReportModal" tabindex="-1" role="dialog" aria-labelledby="statusReportModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="statusReportModalLabel">Confirm Action</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p id="statusReportModalMessage">Do you want to approve this report?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-primary" id="confirmStatusReportBtn">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Reject Report Modal -->
                        <div class="modal fade" id="rejectReportModal" tabindex="-1" role="dialog" aria-labelledby="rejectReportModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="rejectReportModalLabel">Confirm Action</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>

                                    <div class="modal-body">
                                        <p id="statusReportModalMessage">Do you want to reject this report?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                        <button type="button" class="btn btn-primary" id="confirmRejectReportBtn">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                       <!-- Pagination -->

                <div class="row">
                    <div class="col-lg-12">
                        <c:set var="pageReport" value="${sessionScope.pageReport}"/>
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <c:if test="${pageReport > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="Admin?page=${sessionScope.page}&&pageReport=${pageReport - 1}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${sessionScope.numReport}" var="i">
                                    <li class="page-item ${i == pageReport ? 'active' : ''}">
                                        <a class="page-link" href="Admin?page=${sessionScope.page}&&pageReport=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <c:if test="${pageReport < sessionScope.numReport}">
                                    <li class="page-item">
                                        <a class="page-link" href="Admin?page=${sessionScope.page}&&pageReport=${pageReport + 1}" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
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
            $('#statusModalMessage').text(`Do you want to approve this blog?`);
        });

        // Handle confirm status button click
        $('#confirmStatusBtn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#statusModal').modal('hide');

            // Redirect to AdminWaitingBlog servlet with post id
            window.location.href = `AdminWaitingBlog?id=` + id + "&status=" + status;
        });

        // Handle reject button click
        $('.reject-btn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#blogId').val(id);
            $('#status').val(status);
        });

        // Handle status report button click
        $('.status-btn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#confirmStatusReportBtn').data('id', id);
            $('#confirmStatusReportBtn').data('status', status);
            $('#statusReportModalMessage').text(`Do you want to approve this report?`);
        });

        // Handle confirm status report button click
        $('#confirmStatusReportBtn').click(function () {
            const id = $(this).data('id');
            const status = $(this).data('status');
            $('#statusReportModal').modal('hide');

            // Redirect to AdminWaitingReport servlet with report id
            window.location.href = `AdminWaitingReport?id=` + id + "&status=" + status;
        });

        // Handle reject report button click
        $('.reject-btn').click(function () {
            const id = $(this).data('id');
            $('#confirmRejectReportBtn').data('id', id);
        });

        // Handle confirm reject report button click
        $('#confirmRejectReportBtn').click(function () {
            const id = $(this).data('id');
            const status = 2;  // Status for rejection
            $('#rejectReportModal').modal('hide');

            // Redirect to AdminWaitingReport servlet with report id and rejection status
            window.location.href = `AdminWaitingReport?id=` + id + "&status=" + status;
        });
    </script>
</html>
