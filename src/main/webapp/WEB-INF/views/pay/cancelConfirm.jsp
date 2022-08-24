<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-23
  Time: 오후 4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <div>
        <h2>환불완료</h2>
        <div>
            <span>환불이 완료되었습니다.</span>
            <span>고객님의 다음 여행을 돕기 위해 준비하는</span>
            <span>이지투어 되겠습니다.</span>
            <span>감사합니다</span>
        </div>
        <div>사진</div>
        <button type="button" class="toMainBtn">메인으로</button>
        <button type="button" class="reservList">나의 결제/예약페이지</button>
    </div>
<script>
    $(document).ready(function(){
       $('.toMainBtn').on("click", function(){
           location.href = '<c:url value="/"/>';
       });
        $('.reservList').on("click", function(){
            location.href = '<c:url value="/reserv/list"/>';
        });
    });
</script>

</body>
</html>
