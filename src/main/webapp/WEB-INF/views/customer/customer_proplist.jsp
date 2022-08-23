<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EZTour</title>
    <link rel="stylesheet" href="/css/customer/customer_main.css">
</head>
<body>

<div class="inquiry_content">
    <h2>1:1문의</h2>
    <table>
        <tr>
            <th class="no">번호</th>
            <th class="title">제목</th>
            <th class="writer">이름</th>
            <th class="regdate">등록일</th>
        </tr>
        <tr>
            <td>${list.prop_no}</td>
            <td>${list.prop_ttl}</td>
            <td>${list.usr_id}</td>
            <td>${list.reg_date}</td>
        </tr>
    </table>
    <br>
    <button type="button" id="writeBtn" onclick="location.href='<c:url value="/customer/prop"/>'">제안하기</button>
</div>
</body>
</html>
