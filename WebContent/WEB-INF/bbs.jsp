<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>bbs</title>
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
			<button class="w3-button w3-block w3-button w3-red">
				<a href="bbs.do">게시판</a>
			</button>
		</div>
		<div class="w3-panel">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="5%">
							<a href='bbs.do?id=BBSID'>번호</a>
						</th>
						<th width="5%">
							<a href='bbs.do?id=BBSCATEGORY'>카테고리</a>
						</th>
						<th>
							<a href='bbs.do?id=sessionID'>작성자</a>
						</th>
						<th>
							<a href='bbs.do?id=BBSTITLE'>제목</a>
						</th>
						<th width="30%">
							<a href='bbs.do?id=BBSCONTENT'>내용</a>
						</th>
						<th width="20%">
							<a href='bbs.do?id=BBSDATE'>날짜</a>
						</th>
						<th width="5%" align="left">
							<a href='bbs.do?id=BBSHIT'>조회수</a>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bbs" items="${list}" >
						<td align="right"><a href='bbsView.do?id=${bbs.bbsId}'>${bbs.bbsId}</a></td>
						<td align="right"><c:choose>
								<c:when test="${bbs.bbsCategory=='math'}">수학</c:when>
								<c:when test="${bbs.bbsCategory=='enjoy'}">여행</c:when>
								<c:when test="${bbs.bbsCategory=='pic'}">사진</c:when>
								<c:when test="${bbs.bbsCategory=='java'}">java</c:when>
								<c:when test="${bbs.bbsCategory=='web'}">웹프로그래밍</c:when>
								<c:when test="${bbs.bbsCategory=='estate'}">부동산</c:when>
								<c:when test="${bbs.bbsCategory=='food'}">음식</c:when>
								<c:when test="${bbs.bbsCategory=='common'}">상식</c:when>

							</c:choose></td>
						<td align="right">${bbs.id}</td>
						<td align="right"><a href='bbsView.do?id=${bbs.bbsId}'>
								<b>${bbs.bbsTitle}</b> </a></td>
						<td align="right"><a href='bbsView.do?id=${bbs.bbsId}'>
								<b>${bbs.bbsContent} </b></a></td>
						<td align="right">${bbs.bbsDate}</td>
						<td align="right">${bbs.bbsHit}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!--    ${startPage} / ${endPage}  controllerd에서의 값을 심어준다 -->

			<ul class="pager">
				<li class="previous">
					<c:if test='${startPage>1}'>
						<!--  test 뒷부분에 조건을 단다  -->
						<a href='bbs.do?page=${startPage-10}'>이전</a>
					</c:if>
				</li>
				<li>
					<c:forEach begin="${startPage}" end="${endPage}" var="p">
						<a href='bbs.do?page=${p}'>${p}</a>
					</c:forEach>
				</li>
				<li class="next">
					<c:if test='${hasNext==true}'>
						<a href='bbs.do?page=${startPage+10}'>다음</a>
					</c:if>
				</li>
			</ul>
		</div>
		<c:if test='${sessionID!=null}'>
			<div class="w3-panel">
				<button class="w3-button w3-block w3-button w3-pale-red">
					<a href='bbsUpload.do'>글 쓰 기 </a>
				</button>
			</div>
		</c:if>
		<c:if test='${sessionID!=null}'>
			<div class="w3-panel">
				<button class="w3-button w3-block w3-button w3-red">
					<a href='welcome.do'>처음으로</a>
				</button>
			</div>
		</c:if>
		<c:if test='${sessionID==null}'>
			<div class="w3-panel">
				<button class="w3-button w3-block w3-button w3-red">
					<a href='home.jsp'>처음으로</a>
				</button>
			</div>
		</c:if>

		<div align="center">

			<c:if test='${resultBbs >= 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-black" width="10%">
						글쓰기 성공
					</button>
				</div>
			</c:if>
			<c:if test='${resultBbs < 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-red" width="10%">
					 글쓰기 실패
					</button>
				</div>
			</c:if>
			
			<c:if test='${deleteResult >= 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-pale-red" width="10%">
						글삭제 성공
					</button>
				</div>
			</c:if>
			<c:if test='${deleteResult < 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-red" width="10%">
					 글삭제 실패
					</button>
				</div>
			</c:if>
			
				<c:if test='${updateBbsResult >= 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-pale-red" width="10%">
						글수정 성공
					</button>
				</div>
			</c:if>
			<c:if test='${updateBbsResult < 1}'>
				<div class="w3-panel">
					<button class="w3-button w3-red" width="10%">
					 글수정 실패
					</button>
				</div>
			</c:if>
		</div>
	</div>

</body>
</html>