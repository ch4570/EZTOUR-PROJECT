<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/customer/customer_main.css">
</head>
<body>

<div class="inquiry_content">
    <h2>고객서비스 개선 제안</h2>
    <button type="button" id="inquiryWriteBtn" onclick="location.href='<c:url value="/customer/inquiryWrite"/>'">문의하기</button>
</div>

</body>
</html>
<script>
    let msg = "${msg}"
    if(msg=="WRT_OK") alert("등록되었습니다.");
    if(msg=="WRT_ERR") alert("등록에 실패했습니다.");
</script>