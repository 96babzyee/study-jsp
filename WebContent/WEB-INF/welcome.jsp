<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>home</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<%
		String sessionID = null;
		if (session.getAttribute("sessionID")!=null) {
			sessionID = (String) session.getAttribute("sessionID");
		}
	%>

	<div class="flex-w flex-str size1 overlay1">
		<div class="flex-w flex-col-sb wsize1 bg0 p-l-65 p-t-37 p-b-50 p-r-80 respon1">
			<div class="wrappic1">
				<a href="#">
					<img src="images/icons/logo.png" alt="IMG">
				</a>
			</div>		
	
			<div class="w-full flex-c-m p-t-80 p-b-90">
				<div class="wsize2">
					<h3 class="l1-txt1 p-b-34 respon3">
						Coming Soon
					</h3>
					<c:if test='${joinResult == 1}'>
						<div role="alert" style="padding: 10px; text-align: center; background-color:gray; color: white;">
						${sessionScope.sessionID}님 가입. 접속</div>
					</c:if>
					<c:if test='${loginResult == 1}'>
						<div role="alert" style="padding: 10px; text-align: center; background-color:gray; color: white;">
						${sessionScope.sessionID}님 접속</div>
					</c:if>
						
					<button><a href="bbs.do">Board</a></button>
					<button><a href="logout.do">Logout</a></button>
				</div>
			</div>
			
			<div class="flex-w">
				<a href="#" class="size3 flex-c-m how-social trans-04 m-r-15 m-b-10">
					<i class="fa fa-facebook"></i>
				</a>

				<a href="#" class="size3 flex-c-m how-social trans-04 m-r-15 m-b-10">
					<i class="fa fa-twitter"></i>
				</a>

				<a href="#" class="size3 flex-c-m how-social trans-04 m-r-15 m-b-10">
					<i class="fa fa-youtube-play"></i>
				</a>
			</div>
		</div>
			

		<div class="wsize1 simpleslide100-parent respon2">
			<!--  -->
			<div class="simpleslide100">
				<div class="simpleslide100-item bg-img1" style="background-image: url('images/bg01.jpg');"></div>
				<div class="simpleslide100-item bg-img1" style="background-image: url('images/bg02.jpg');"></div>
				<div class="simpleslide100-item bg-img1" style="background-image: url('images/bg03.jpg');"></div>
			</div>
		</div>
	</div>



	

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/tilt/tilt.jquery.min.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>