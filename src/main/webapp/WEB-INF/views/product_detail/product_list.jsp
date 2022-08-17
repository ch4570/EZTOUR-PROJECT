<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>상품리스트</div>
<c:forEach var="TrvPrdDetailDto" items="${list}">

    <div>
        ${TrvPrdDetailDto.img_pth}
        ${TrvPrdDetailDto.prd_nm}
        ${TrvPrdDetailDto.arl_nm}
    </div>
</c:forEach>

</body>
</html>
