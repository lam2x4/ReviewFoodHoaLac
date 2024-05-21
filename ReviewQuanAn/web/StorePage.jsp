<%-- 
    Document   : StorePage
    Created on : May 21, 2024, 6:03:04 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Store Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            body {
                background-image: url('img/Screenshot 2023-03-11 181712.png');
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
                color: #333;
            }
            .store-section, .menu-section, .comments-section {
                background-color: rgba(255, 255, 255, 0.9);
                padding: 20px;
                border-radius: 10px;
                margin-bottom: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .store-section h1, .menu-section h2, .comments-section h2 {
                color: #d9534f; /* Bootstrap danger color */
            }
            .store-section img {
                max-width: 100%;
                height: auto;
                border-radius: 10px;
                margin-bottom: 20px;
            }
            .list-group-item {
                display: flex;
                align-items: center;
                border: none;
                border-bottom: 1px solid #ddd;
                padding: 15px 10px;
            }
            .list-group-item img {
                max-width: 50px;
                max-height: 50px;
                margin-right: 15px;
                border-radius: 50%;
            }
            .btn-custom {
                background-color: #d9534f;
                color: white;
                border-radius: 5px;
                padding: 10px 20px;
                margin: 5px;
            }
            .btn-custom:hover {
                background-color: #c9302c;
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Navigation Bar -->
        <%@ include file="./Header.jsp" %>
        <div class="container mt-4">
            <!-- Store Section -->
            <div class="store-section text-center">
                <img src="img/2022-12-24-10-53-02_0.png" alt="Store Image"> <!-- Replace with your store image URL -->
                <h1>Store Name</h1>
                <p>Store Address</p>
                <p>Store Description</p>
            </div>

            <!-- Menu Section -->
            <div class="menu-section">
                <h2>Menu</h2>
                <ul class="list-group">
                    <li class="list-group-item">
                        <img src="img/item1.jpg" alt="Item 1"> <!-- Replace with your menu item image URL -->
                        <span>Item 1 - $10.00</span>
                    </li>
                    <li class="list-group-item">
                        <img src="img/item2.jpg" alt="Item 2"> <!-- Replace with your menu item image URL -->
                        <span>Item 2 - $15.00</span>
                    </li>
                    <li class="list-group-item">
                        <img src="img/item3.jpg" alt="Item 3"> <!-- Replace with your menu item image URL -->
                        <span>Item 3 - $8.00</span>
                    </li>
                    <!-- More menu items can be added here -->
                </ul>
            </div>

            <!-- Comments Section -->
            <div class="comments-section">
                <h2>Comments</h2>
                <form>
                    <div class="form-group">
                        <label for="comment">Write a comment:</label>
                        <textarea class="form-control" id="comment" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-custom">Submit</button>
                </form>
                <div class="mt-4">
                    <div class="comment mb-3">
                        <strong>User 1</strong>
                        <p>This is a comment.</p>
                    </div>
                    <div class="comment mb-3">
                        <strong>User 2</strong>
                        <p>This is another comment.</p>
                    </div>
                    <!-- More comments can be added here -->
                </div>
            </div>
        </div>
        <%@ include file="./Footer.jsp" %>
    </body>
</html>
