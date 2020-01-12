<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:choose>
            <c:when test="${user != null}">
                <h2>ユーザー情報　編集ページ</h2>
                <p>(パスワードは変更する場合の入力してください)</p>
                <form method="post" action="<c:url value='/userRegistration/update'/>">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="#" id="destroy">このユーザー情報を削除する</a></p>
                <form method="post" action="<c:url value='/userRegistration/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" >
                </form>

                <script src="<c:url value='/js/common.js'/>"></script>
            </c:when>

            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/' />">一覧に戻る</a></p>
        <script src="<c:url value='/js/common.js'/>"></script>
    </c:param>
</c:import>