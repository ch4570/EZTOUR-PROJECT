<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/customer/customer_main.css">
</head>
<body>

<div class="inquiry_content">
    <h2>고객서비스 개선 제안</h2>
<%--    1:1 고객문의 테이블--%>
    <table>
        <tr>
            <th class="no">번호</th>
            <th class="title">제목</th>
            <th class="writer">이름</th>
            <th class="regdate">등록일</th>
            <th class="viewcnt">조회수</th>
        </tr>
<%--        items ="${list}"를 어디서 가져와?--%>

            <tr>
                <td>${customer.qna_no}</td>
                <td>${customer.qna_ttl}</td>
                <td>${customer.usr_id}</td>
                <td>${customer.frs_reg_date}</td>
                <td>${customer.view_cnt}</td>
            </tr>
    </table>
    <br>
<%--    네비게이션 바--%>
    <div>
        <c:if test="${ph.showPrev}">
            <a href="<c:url value='/customer/inquiryList?page=${cph.beginPage-1}&pageSize=${ph.pageSize}'/>">$lt;</a>
        </c:if>
        <c:forEach var="i" begin="${cph.beginPage}" end="${ph.endPage}">
            <a href="<c:url value='/customer/inquiryList?page={i}&pageSize=${ph.Size}'/>">${i}</a>
        </c:forEach>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/customer/inquiryList?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
        </c:if>
    </div>
<%--    네비게이션 바 끝--%>
<%--    1:1 고객문의 테이블 끝--%>
    <button type="button" id="inquiryWriteBtn" onclick="location.href='<c:url value="/customer/inquiryWrite"/>'">문의하기</button>
</div>
</body>
</html>
<script>
    let msg = "${msg}"
    if(msg=="WRT_OK") alert("등록되었습니다.");
    if(msg=="WRT_ERR") alert("등록에 실패했습니다.");
</script>