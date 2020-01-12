<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError}">
            <div id="flush_error">
                ユーザーIDかパスワードが間違っています。
            </div>
        </c:if>

        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>

        <h2>ログイン</h2>

        <form method="post" action="<c:url value='/login' />">
            <label for="userId">ユーザーID</label><br />
            <input type="text" name="userId" value="${userId}" />
            <br /><br />

            <label for="password">パスワード</label><br />
            <input type="password" name="password" />
            <br /><br />

            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>

        <br><br><br>
        <p><a href="<c:url value='/userRegistration/new' />">ユーザー登録ページ</a></p>
    </c:param>
</c:import>