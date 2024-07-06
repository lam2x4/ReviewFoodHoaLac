<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        .profile-header img {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            margin-right: 20px;
        }
        .profile-header h1 {
            margin: 0;
        }
        .nav-tabs {
            margin-bottom: 20px;
        }
        .no-posts {
            text-align: center;
            margin-top: 50px;
        }
        .no-posts img {
            width: 100px;
            height: auto;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Profile Header -->
        <div class="profile-header">
            <img src="https://via.placeholder.com/100" alt="Profile Picture">
            <div>
                <h1>No-Butterscotch-1291</h1>
                <p>u/No-Butterscotch-1291</p>
            </div>
        </div>

        <!-- Navigation Tabs -->
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" href="#">Overview</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Posts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Comments</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Saved</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Hidden</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Upvoted</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Downvoted</a>
            </li>
        </ul>

        <!-- No Posts Section -->
        <div class="no-posts">
            <img src="https://www.redditstatic.com/desktop2x/img/snoo@2x.png" alt="No Posts">
            <p>u/No-Butterscotch-1291 hasn't posted yet</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
