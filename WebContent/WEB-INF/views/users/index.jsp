<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="${content}">
        <c:if test="${flush != null}">
            <div id = "flush_sccess">
                <c:out value="${flush}"/>
            </div>
        </c:if>
        
        <h2>ユーザー　一覧</h2>
        
        <table id="user_list">
            <tbody>
	            <tr>
	                <th>userID</th>
	                <th>ユーザー名</th>
	                <th>操作</th>
	            </tr>
	            <tr>
		            <c:forEach var="user" items="${users}" varStatus="status">
		                <td><c:out value="${user.userId}"/></td>
		                <td><c:out value="${user.userName}"/></td>
		                <td><a href="<c:url value='/users/show?id=${user.id}' />">詳細を表示</a></td>
		            </c:forEach>
	            </tr>
            </tbody>
        </table>
        
        <div id="pagination">
            (全 ${users_count} 件) <br>
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) +1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        
        <p><a href="<c:url value='/user/new' />">新規ユーザー登録</a></p>
        
    </c:param>
</c:import>