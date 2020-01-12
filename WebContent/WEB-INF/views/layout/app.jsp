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
<link rel="stylesheet" href="<c:url value='/css/books.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/' />">Book管理システム</a></h1>	&nbsp;&nbsp;&nbsp;
                <!-- loginしている場合表示 -->
                <c:if test="${sessionScope.login_user != null}">

                    <!-- 管理者だけ表示する -->
                    <c:if test="${sessionScope.login_user.admin_flag == 1}">
                        <a href="<c:url value='/users/index' />">ユーザー管理</a>&nbsp;
                    </c:if>
                    <c:if test="${sessionScope.login_user.admin_flag == 1}">
                        <a href="<c:url value='/books/index' />">Book管理</a>&nbsp;
                    </c:if>


                </c:if>

            </div>

            <c:if test="${sessionScope.login_user != null}">
                 <div id="user_name">
                 <c:if test="${sessionScope.login_user.admin_flag == 0}">
                        <a href="<c:url value='/userRegistration/edit' />">アカウント変更</a>&nbsp;&nbsp;&nbsp;

                </c:if>
                    <c:out value="${sessionScope.login_user.userName}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='/logout' />">ログアウト</a>
                 </div>
            </c:if>

        </div>



        <div id="content">
              ${param.content}
        </div>

        <div id="footer">
              &copy; by yasui.
        </div>
    </div>
</body>
</html>