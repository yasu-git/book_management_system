<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <div id="book_main">
            <c:choose>
                <c:when test="${book != null}">
                    <div class="clearfix">
                        <h2>Book 詳細ページ</h2>

                        <h2>${book.title}</h2>
                        <hr>
                        <br>
                        <br>
                        <div id="main_book_left">
                            <img alt="book-img"
                                src="<c:out value="${book.smallThumbnail}" />">
                        </div>

                        <div id="main_book_right">
                            <h3>商品情報</h3>
                            <hr>
                            <table>
                                <tbody>
                                    <tr>
                                        <th>著者</th>
                                        <td>${book.author1}<c:if test="${book.author2 != null}">
                                                <c:out value=",${book.author2}" />
                                            </c:if> <c:if test="${book.author3 != null}">
                                                <c:out value=",${book.author3}" />
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>発売日</th>
                                        <td>
                                            <c:choose>
                                                <c:when test="${book.publishedDate == null}">
                                                     情報なし
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatDate value="${book.publishedDate}" pattern="yyyy年MM月dd日" />
                                                </c:otherwise>
                                            </c:choose>


                                        </td>
                                        <!-- <td><c:out value="${book.publishedDate}" /></td> -->

                                    </tr>
                                    <tr>
                                        <th>出版社</th>
                                        <td><c:out value="${book.publisher}" /></td>
                                    </tr>
                                    <tr>
                                        <th>ページ数</th>
                                        <td><c:out value="${book.pageCount}ページ" /></td>
                                    </tr>
                                    <tr>
                                        <th>定価</th>
                                        <td>
                                            <c:choose>
                                                <c:when test="${book.listPrice != 0 }">
                                                    <c:out value="${book.listPrice}円" />
                                                </c:when>
                                                <c:otherwise>
                                                    情報なし
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>ISBNコード</th>
                                        <td><c:out value="${book.isbn}" /></td>
                                    </tr>
                                    <tr>
                                        <th>販売サイト</th>
                                        <td><a href="<c:out value='${book.infoLink}' />">Google Play</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>


                    <div>

                        <h3>説明</h3>
                        <hr>
                        <p>
                            <c:out value="${book.description}" />
                        </p>
                        <br>

                        <table>
                            <tbody>
                                <tr>
                                    <th>登録日</th>
                                    <td><fmt:formatDate value="${book.created_at}"
                                            pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
                                </tr>
                                <tr>
                                    <th>評価</th>
                                    <td><c:out value="${book.evaluate}" /></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <c:if test="${sessionScope.login_user.id == book.user.id}">
                        <p>
                            <a href="<c:url value="/books/edit?id=${book.id}" />">この本の評価を編集する</a>
                        </p>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <h2>お探しのデータは見つかりませんでした。</h2>
                </c:otherwise>
            </c:choose>
            <c:if test="${sessionScope.login_user != null}">
                <!-- 管理者だけ表示する -->
                <c:if test="${sessionScope.login_user.admin_flag == 1}">
                    <a href="<c:url value="/books/index" />">Book管理一覧に戻る</a>&nbsp;
              </c:if>
            </c:if>

            <p>
                <a href="<c:url value='/' />">一覧に戻る</a>
            </p>
        </div>
    </c:param>
</c:import>