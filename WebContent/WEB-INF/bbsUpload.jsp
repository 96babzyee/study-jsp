<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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
			<button class="w3-button w3-block w3-button w3-red">게 시 판</button>
		</div>
		<div align="center">
			<form method="post" enctype="multipart/form-data" action="bbsUpload.do">
				<table border=0px>
					<tr>
						<td><h7>카테고리 :</h7></td>
						<td colspan="4"><select name="category"
							style="width: 30%; padding: 12px 20px; font-size: 18px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
								<option value="math">수학</option>
								<option value="enjoy">여행</option>
								<option value="pic" selected>사진</option>
								<option value="java">java</option>
								<option value="web">웹프로그래밍</option>
								<option value="estate">부동산</option>
								<option value="food">음식</option>
								<option value="common">상식</option>
						</select></td>
					</tr>
					<tr>
						<td><h5>제 목 :</h5></td>
						<td colspan="4"><input name="title" type="text"
							style="width: 90%; padding: 12px 20px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;">
						</td>
					<tr>
					<tr>
						<td><h5>내 용 :</h5></td>
						<td colspan="4"><textarea name="content" rows="20" cols="80"
								style="width: 90%; padding: 12px 20px; margin: 10px 0px; background-color: white; box-sizing: border-box; border: 2px solid black;"></textarea>
						</td>
					</tr>
					<tr>
						<script type="text/javascript">
							var uf = "";
							function file_add(size, ext) {
								var filecountTemp = parseInt(document.getElementById("file_cnt").value);
								var parents = document.getElementById("file_add_form");
								var br = document.createElement("br");
								br.setAttribute("id", "br" + (filecountTemp + 1));
								parents.appendChild(br);
								if (filecountTemp == 30) {
									alert("파일업로드는 최대 30개까지 가능합니다"); //< --- 이 부분 지우면 무한대로 추가됨.
									return;
								} else {
									var obj = document.createElement("input");
									obj.setAttribute("type", "file");
									obj.setAttribute("size", size);
									obj.setAttribute("name", "bbs_file" + (filecountTemp + 1));
									obj.setAttribute("id", "file" + (filecountTemp + 1));
									parents.appendChild(obj);
								}
								document.getElementById("file_cnt").value = filecountTemp + 1;
							}
							function file_delete() {
								var filecountTemp = parseInt(document.getElementById("file_cnt").value);
								var parents = document.getElementById("file_add_form");
								var obj = document.getElementById('file' + filecountTemp);
								var br = document.getElementById('br' + filecountTemp);
								parents.removeChild(obj);

								document.getElementById("file_cnt").value = filecountTemp - 1;
							}
						</script>
						
						<td colspan="3"></td>
						<td colspan="2">
							<div class="w3-button w3-block w3-button w3-pale-red"
								style="width: 30%;">
								<input type="hidden" id="file_cnt" name="file_cnt" value="1"
									style=""> <a
									href="javascript:file_add(50, 'class=input_write');"><b>첨부파일추가</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:file_delete();"><b>첨부파일삭제</b></a>
							</div>
						</td>
					</tr>
					<td colspan="3"></td>
					<td colspan="2">
						<div id='file_add_form'
							class="w3-button w3-block w3-button w3-red"
							style="width: 80%;">
							<input type=file name="bbs_file1" size="50" class="input_write">
						</div>
					</td>
					<tr>
						<td colspan="5"><br>
					</tr>
					<tr>
						<td colspan="3"></td>
						<td colspan="2"><button type="submit"
								class="w3-button w3-block w3-button w3-pale-red"
								style="width: 80%;">보내기</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="w3-panel">
			<button class="w3-button w3-block w3-button w3-red">
				<a href="bbs.do">게시판</a></button>
		</div>
		<div class="w3-panel">
			<button class="w3-button w3-block w3-button w3-pale-red">
				<a href="welcome.do">처음으로</a></button>
		</div>
	</div>
</body>
</html>


