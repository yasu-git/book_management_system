<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${book != null}">
                <h2>Book　詳細ページ</h2>
                <div>
                    <h2>${book.title}</h2>
                    <hr><br><br>
                    <div><img alt="book-img" src="<c:out value="${book.smallThumbnail}" />">
                    
                    </div>
                    
                    <div>
                        <h3>商品情報</h3>
                        <hr>
		                <table>
		                    <tbody>
		                        <tr>
		                            <th>著者</th>
		                            <td>${book.author1}
			                            <c:if test="${book.author2 != null}">
			                              <c:out value=",${book.author2}" />
			                            </c:if>
			                            <c:if test="${book.author3 != null}">
	                                      <c:out value=",${book.author3}" />
	                                    </c:if>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>発売日</th>
		                            <td><c:out value="${book.publishedDate}" /></td>
		                        </tr>
		                        <tr>
		                            <th>出版社</th>
		                            <td><c:out value="${book.publisher}"/></td>
		                        </tr>
		                        <tr>
		                            <th>ページ数</th>
		                            <td><c:out value="${book.pageCount}ページ"/></td>
		                        </tr>
		                        <tr>
		                            <th>定価</th>
		                            <td><c:out value="${book.listPrice}円" /></td>
		                        </tr>
		                    </tbody>
		                </table>
                    </div>
                    <div>
                        <h3>説明</h3>
	                    <hr>
                        <p><c:out value="${book.description}" /></p>
	                        <br>
	                        <table>
	                           <tbody>
		                           <tr>
                                       <th>登録日</th>
                                       <td>
                                        <fmt:formatDate value="${book.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                                       </td>
                                   </tr>
                                   <tr>
                                       <th>評価</th>
                                       <td><c:out value="${book.evaluate}" /></td>
                                   </tr>
	                           </tbody>
	                        </table>
                    </div>
                    
                </div>

                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p><a href="<c:url value="/books/edit?id=${book.id}" />">この日報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/books/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>