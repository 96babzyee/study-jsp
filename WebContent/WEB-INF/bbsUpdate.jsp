<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>bbsWirte</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<%
		String sessionID = null;
		if (session.getAttribute("sessionID") != null) {
			sessionID = (String) session.getAttribute("sessionID");
		}
	%>

	<div class="container">

		<div class="w3-panel">

			<button class="w3-button w3-block w3-button w3-red">게 시 판
			</button>
		</div>
		<div align="center">
			<form method="post" action="bbsUpdate.do">
				<table border=0px>
					<tr>
						<td><h5>글번호:</h5></td>
						<td colspan="4">
							<input name="bbsId" type="text" readonly
							value="${bbsUpdate.bbsId}" 
							style="width: 30%; padding: 12px 20px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
						</td>
					<tr>
					<tr>
						<td><h7>카테고리 :</h7></td>
						<td colspan="4">
							<select name="category"
							style="width: 30%; padding: 12px 20px; font-size: 18px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
								<c:if test="${bbsUpdate.bbsCategory == 'math'}">
									<option value="math" selected>수학</option>
								</c:if>
								<c:if test="${bbsUpdate.bbsCategory != 'math'}">
									<option value="math">수학</option>
								</c:if>
								<c:if test="${bbsUpdate.bbsCategory == 'enjoy'}">
									<option value="enjoy" selected>여행</option>
								</c:if>
								<c:if test="${bbsUpdate.bbsCategory == 'enjoy'}">
									<option value="enjoy">여행</option>
								</c:if>
								<option value="pic">사진</option>
								<option value="java">java</option>
								<option value="web" selected>웹프로그래밍</option>
								<option value="estate">부동산</option>
								<option value="food">음식</option>
								<option value="common">상식</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><h5>제 목 :</h5></td>
						<td colspan="4"><input name="title" type="text"
							value="${bbsUpdate.bbsTitle}"
							style="width: 90%; padding: 12px 20px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
						</td>
					<tr>
					<tr>
						<td><h5>내 용 :</h5></td>
						<td colspan="4">
							<textarea name="content" rows="20" cols="80"
								style="width: 90%; padding: 12px 20px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
								${bbsUpdate.bbsContent}
							</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="5"><br>
					</tr>
					<tr>
						<td colspan="3"></td>
						<td colspan="2"><button type="submit"
								class="w3-button w3-block w3-button w3-pale-red"
								style="width: 80%;">보내기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="w3-panel">
			<button class="w3-button w3-block w3-button w3-red">
				<a href='bbs.do'>게시판</a>
			</button>
		</div>

		<div class="w3-panel">
			<button class="w3-button w3-block w3-button w3-pale-red">
				<a href='welcome.do'>처음으로</a>
			</button>
		</div>
	</div>
</body>
</html>



