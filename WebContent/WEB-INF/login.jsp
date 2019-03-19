<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>login</title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
    integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <style>
    .btn_go {
      background-color: #007bff;
      border-color: #007bff;
    }

    .AllContainer {
      height: 800px;
    }

    .loginContainer {
      margin: 100px auto;
    }
  </style>
</head>

<body>
  <!-- Page Content -->
  <div class="container AllContainer">

    <!-- Join -->
    <div class="container loginContainer">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
          <div class="card card-signin flex-row my-5">
            <div class="card-img-left d-none d-md-flex">
              <!-- Background image for card set in CSS! -->
            </div>
            <div class="card-body">
              <h5 class="card-title text-center">Login</h5>
              <form class="form-login" action="login.do" method="post">
                <div class="form-label-group">
                  <input type="text" name="id" class="form-control" placeholder="UserID" required autofocus>
                  <label></label>
                </div>
                <div class="form-label-group">
                  <input type="password" name="password" class="form-control" placeholder="Password" required>
                  <label></label>
                </div>
                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Login</button>
                <a class="d-block text-center mt-2 small" href="home.jsp">Home</a>
                <a class="d-block text-center mt-2 small" href="join.do">Join</a>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
    integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
    integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
    crossorigin="anonymous"></script>

</body>

</html>