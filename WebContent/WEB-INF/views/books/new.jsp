<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<h2>Book 新規登録ページ</h2>

		<c:if test="${errors != null}">
			<div id="flush_error">
				入力内容にエラーがあります。<br />
				<c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" />
					<br />
				</c:forEach>

			</div>
		</c:if>

		<form method="POST" action="<c:url value='/books/create' />" id="isbn_form">

			<label for="isbn">ISBN</label><br /> 
			<input id="input_isbn" type="text" name="isbn" autofocus />
			<br /> <br /> 
			<input  type="hidden" name="_token" value="${_token}" />
			<button type="submit">投稿</button>
		</form>

		<p>
			<a href="<c:url value='/books/index' />">一覧に戻る</a>
		</p>
	</c:param>
</c:import>