<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<div>
		<h1>죄송합니다.</h1>
		<h2>오류가 발생했습니다.</h2> <br>
	</div>
	<%
		String type = exception.toString();
		String msg = exception.getMessage();
		out.print("오류 종류 : "+type);
		out.print("<br/>메세지 : "+msg);
	%>
</body>
</html>