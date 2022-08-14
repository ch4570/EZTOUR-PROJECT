<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>예약내역</h1>
    <c:forEach var="item" items="reservList" begin="0" end="${reservList.size()}">

    </c:forEach>
</div>
</body>
</html>
