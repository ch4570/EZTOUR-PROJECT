<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
  <link rel="stylesheet" href="/css/event/event_board.css" />
  <title>Title</title>
</head>
<body>
  <div>
    ${faqDto.qna_cd}
    ${faqDto.faq_qna_cont}
    ${faqDto.faq_ans_cont}
  </div>
      <form class="hidden-form" id="form">
        <input type="hidden" name="event_id" value="${faqDto.qna_cd}">
        <input type="hidden" name="event_title" value="${faqDto.faq_qna_cont}" >
        <input type="hidden" name="writer" value="${faqDto.faq_ans_cont}" >

        <button type="button" id="listBtn" class="list-btn">목록으로 돌아가기</button>
        <button type="button" id="modifyBtn" class="modify-btn">수정</button>
        <button type="button" id="removeBtn" class="remove-btn">삭제</button>
      </form>

</body>
<script>
  $(document).ready(function () {
    $('#listBtn').on("click", function () {
      location.href = "<c:url value='/customer/faqList?page=${page}&pageSize=${pageSize}' />";
    });

    $('#removeBtn').on("click", function () {
      if(!confirm("삭제한 글은 복구할 수 없습니다. 해당 글을 삭제 하시겠습니까?")) return;
      let form = $('#form');
      form.attr("action", "<c:url value='/customer/removeFaq?faq_no=${faqDto.faq_no}&page=${page}&pageSize=${pageSize}' />");
      form.attr("method", "post");
      form.submit();
    });

    $('#modifyBtn').on("click", function () {
      location.href = "<c:url value='/customer/faqWrite?faq_no=${faqDto.faq_no}&page=${page}&pageSize=${pageSize}' />";
    });
  });

  let msg = "${msg}"
  if (msg=="Modify Success") alert("FAQ가 수정되었습니다.");
</script>
</html>