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
        <a href="<c:url value='/customer/faq?faq_no=${faqDto.faq_no}&page=${page}&pageSize=${pageSize}'/>">
            번호:${faqDto.faq_no}
            문의코드:${faqDto.qna_cd}
            질문:${faqDto.faq_qna_cont}
            답변:${faqDto.faq_ans_cont}
        </a>
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

<script>
    let msg = "${msg}"
    if (msg=="Delete Success") alert("삭제 처리 되었습니다.");
    if (msg=="Delete Error") alert("삭제에 실패 했습니다.");
    if (msg=="write Success") alert("FAQ가 등록 되었습니다.");
    if (msg=="Load Error") alert("삭제되었거나 없는 FAQ 입니다.");
</script>
</body>
</html>
