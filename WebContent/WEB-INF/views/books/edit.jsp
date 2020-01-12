<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${book != null}">
                <h2>本の評価　編集ページ</h2>
                <form method="POST" action="<c:url value='/books/update' />">
                    <c:import url="_form.jsp" />
                </form>

                <p><a href="#" id="destroy">このBookを削除する</a></p>
                <form method="POST" action="<c:url value='/books/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script src="<c:url value='/js/common.js'/>"></script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/books/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>