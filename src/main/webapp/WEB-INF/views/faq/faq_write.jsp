<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:choose>
        <c:when test="${empty faqDto.faq_no}">
            <form id="formWrite" name="formWrite" method="post" action="/customer/faqWrite" >
        </c:when>
        <c:when test="${!empty faqDto.faq_no}">
<%--            <form id="formWrite" name="formWrite" method="post" >--%>
        </c:when>
    </c:choose>
        <div class="form-wrap">

<%--            <div class="board-header">--%>
<%--                <input type="hidden" name="faq_no" value="${faqDto.faq_no}">--%>
<%--            </div>--%>

            <div class="category">
                <h3>카테고리</h3>
                <select name="qna_cd">
                    <option>예약문의</option>
                    <option>결제문의</option>
                    <option>취소문의</option>
                    <option>기타문의</option>
                </select>
            </div>

            <div class="board-content">
                <h3 class="label">질문</h3>
                <input type="text" name="faq_qna_cont" value="${faqDto.faq_qna_cont}" placeholder="질문을 입력하세요" required>
                <h3 class="label">답변</h3>
                <input type="text" name="faq_ans_cont" value="${faqDto.faq_ans_cont}" placeholder="답변을 입력하세요" required>
            </div>

            <div class="board-btn">
                <c:choose>
                    <c:when test="${empty faqDto.faq_no}">
                        <button id="save-btn">등록</button>
                        <%--                    <input type="button" id="save-btn" value="등록" />--%>
                    </c:when>
                    <c:when test="${!empty faqDto.faq_no}">
                        <%--                    <input type="submit" id="modify-btn" value="수정" />--%>
                        <button id="modify-btn">수정</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form>

</body>
</html>
