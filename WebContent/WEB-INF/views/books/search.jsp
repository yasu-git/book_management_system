<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <form method="POST" action="<c:url value='/books/search' />">
            <input type="text" name="keyword">
            <button type="submit">検索</button>
        </form>
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"/>
            </div>
        </c:if>

        <h2>Book管理システムへようこそ</h2>

        <h3>【自分の本　一覧】</h3>

        <table id="book_list">
            <tbody>
                <tr>
                    <th class="book_date">日付</th>
                    <th class="book_title">タイトル</th>
                    <th class="book_action">操作</th>
                </tr>

                <c:forEach var="book" items="${books}" varStatus="status">
                    <!-- class row 奇数と偶数で表示を変え見やすくする -->
                    <tr class="row${status.count % 2}">
                        <td class="book_date"><fmt:formatDate value='${book.created_at}' pattern='yyyy年MM月dd日' /></td>
                        <td class="book_title">${book.title}</td>
                        <td class="book_action"><a href="<c:url value='/books/show?id=${book.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <div id="pagination">
            （全 ${books_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((books_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/books/search?keyword={$keyword}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='/books/new' />">新規本の登録</a></p>

    </c:param>
</c:import>