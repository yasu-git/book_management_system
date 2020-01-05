<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Book管理システム</title>
<link rel="stylesheet" href="<c:url value='/css/reset.css' />">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="header_menu">
				<h1>
					<a href="<c:url value='/' />">Book管理システム</a>
				</h1>
				&nbsp;&nbsp;&nbsp;
			</div>
		</div>

		<div id="content">${param.content}</div>

		<div id="footer">&copy; by yasui.</div>
	</div>
</body>
</html>