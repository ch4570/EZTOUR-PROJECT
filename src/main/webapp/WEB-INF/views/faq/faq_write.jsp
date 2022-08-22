<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="/css/customer/faq_write.css">
    <title>Title</title>
</head>
<body>

    <c:choose>
        <c:when test="${empty faqDto.faq_no}">
            <form id="formWrite" name="formWrite" method="post" >
        </c:when>
        <c:when test="${!empty faqDto.faq_no}">
            <form id="formMod" name="formMod" method="post" >
                <input type="hidden" name="faq_no" value="${faqDto.faq_no}">
        </c:when>
    </c:choose>
        <div class="form-wrap">

            <div class="category">
                <h3>카테고리</h3>
                <select name="qna_cd">
                    <option>${faqDto.qna_cd}</option>
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
                    </c:when>
                    <c:when test="${!empty faqDto.faq_no}">
                        <button id="modify-btn">수정</button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form>


<script>
    $(document).ready(function () {

        $("#save-btn").click(function() {
            let form = $("#formWrite");
            form.attr("action", "/customer/faqWrite?page=${page}&pageSize=${pageSize}");
            form.submit();
        })

        $("#modify-btn").click(function () {
            let form = $("#formMod");
            form.attr("action", "/customer/modify?faq_no=${faqDto.faq_no}&page=${page}&pageSize=${pageSize}");
            form.submit();
        });
    });

    let msg = "${msg}"
    if (msg=="Write Error") alert("FAQ 등록에 실패했습니다. 다시 시도해 주세요.");
    if (msg=="Modify Error") alert("FAQ 수정에 실패했습니다. 다시 시도해 주세요.");
</script>

</body>
</html>
