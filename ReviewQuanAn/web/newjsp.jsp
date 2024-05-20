<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Page</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <link rel="stylesheet" href="styles.css">
  <style>
      /* Define your styles here */
body {
  font-family: Arial, sans-serif;
}

.carousel-item img {
  height: 400px;
  object-fit: cover;
}

.product-info {
  padding: 0 20px;
}

.product-title {
  font-size: 24px;
  font-weight: bold;
}

.product-price {
  font-size: 20px;
  font-weight: bold;
}

.rating {
  margin-top: 10px;
}

.rating i {
  font-size: 18px;
  color: #ffc107;
}

.section-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.product-description {
  font-size: 16px;
}

.product-options {
  margin-top: 20px;
}

.btn-primary {
  background-color: #ff572

  </style>
</head>
<body>
  <div class="container-fluid">
    <!-- Product Details Section -->
    <div class="row">
      <div class="col-lg-6">
        <!-- Product Image Gallery -->
        <div id="productImageCarousel" class="carousel slide" data-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="https://via.placeholder.com/800x600" class="d-block w-100" alt="Product Image">
            </div>
            <!-- Add more carousel items for additional images -->
          </div>
          <a class="carousel-control-prev" href="#productImageCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#productImageCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
      <div class="col-lg-6">
        <!-- Product Information -->
        <div class="product-info mt-4 mt-lg-0">
          <h2 class="product-title">Áo Thun Nam Nữ Teelab Local Brand</h2>
          <p class="product-price text-danger">₫199,000</p>
          <div class="rating">
            <i class="fas fa-star text-warning"></i>
            <i class="fas fa-star text-warning"></i>
            <i class="fas fa-star text-warning"></i>
            <i class="fas fa-star text-warning"></i>
            <i class="fas fa-star-half-alt text-warning"></i>
            <span>(200 đánh giá)</span>
          </div>
          <div class="product-options mt-3">
            <!-- Product options and details here -->
            <button class="btn btn-primary btn-lg"><i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Product Description Section -->
    <div class="row mt-5">
      <div class="col-lg-12">
        <h3 class="section-title">Mô tả sản phẩm</h3>
        <p class="product-description">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi. Integer sodales magna eu lorem lacinia feugiat. 
          Fusce non libero lacinia, aliquet est non, varius purus. Vestibulum bibendum turpis eu dui fringilla maximus. 
          Donec fringilla sapien nec elit fringilla, vel ultricies dolor aliquet. Suspendisse id neque id arcu fermentum eleifend.
        </p>
      </div>
    </div>
    <!-- Product Reviews Section -->
    <div class="row mt-5">
      <div class="col-lg-12">
        <h3 class="section-title">Đánh giá sản phẩm</h3>
        <!-- Add review section here -->
      </div>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
