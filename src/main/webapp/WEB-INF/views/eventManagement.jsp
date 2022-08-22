<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-22(022)
  Time: 오전 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_management_img_style.css'/>">
</head>
<body>
<div class="wrap">
    <div class="content">

        <div class="nav">
            <div class="board">
                <c:forEach var="event_list" items="${event_list}">
                    <div class="board_content">
                        <div class="board_content_detail">
                            <div><span><img src="<c:url value='${event_list.img_pth}'/>" width="200px" height="200px"></span></div>
                            <div><a href="<c:url value='/event/image/read?img_pth=${event_list.img_pth}'/>"><span>${event_list.prd_nm}</span></a></div>

                        </div>
                    </div>
                </c:forEach>
                <div class="search_option_form">
                    <form action="<c:url value='/event/management/image'/>">
                        <select name="search_option" id="search_option">
                            <option value="" selected>검색옵션</option>
                        </select>
                        <input type="text" name="search_keyword" placeholder="검색어" id="search_keyword">
                        <input type="submit" id="search_btn" value="검색">
                    </form>
                </div>
                <div class="paging_list">
                    <c:if test="${paging.preView eq 'true'}">
                        <a href="<c:url value="/event/management/image?page=${paging.beginPage-1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&lt;</span></a>
                    </c:if>
                    <c:forEach var="i" begin="${paging.beginPage}" end="${paging.endPage}">
                        <a href="<c:url value='/event/management/image?page=${i}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}'/>"><span>${i}</span></a>
                    </c:forEach>
                    <c:if test="${paging.nextView eq 'true'}">
                        <a href="<c:url value="/event/management/image?page=${paging.endPage+1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&gt;</span></a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
