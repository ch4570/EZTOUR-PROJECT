<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach  var="faqDto" items="${list}">
<div>
${faqDto.qna_cd}
</div>
</c:forEach>

<div class="page-nav">
    <c:if test="${ph.showPrev}">
        <a href="<c:url value='/customer/faqList?page=${ph.beginPage-1}&pageSize=${ph.pageSize}' />"><i class="fa-solid fa-angle-left"></i></a>
    </c:if>
    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
        <a href="<c:url value='/customer/faqList?page=${i}&pageSize=${ph.pageSize}' />">${i}</a>
    </c:forEach>
    <c:if test="${ph.showNext}">
        <a href="<c:url value='/customer/faqList?page=${ph.endPage+1}&pageSize=${ph.pageSize}' />"><i class="fa-solid fa-angle-right"></i></a>
    </c:if>
</div>

    <a href="/customer/faqWrite">등록</a>
    <a href="/customer/faqModify">수정</a>

</body>
</html>
