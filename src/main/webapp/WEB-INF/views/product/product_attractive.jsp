<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_attrative_style.css'/>"/>
</head>
<body>

<div class="wrap">
    <div class="content">
        <div class="tlt">
            <h1>나의 관심 상품</h1>
        </div>
        <h1>관심 상품은 최대 12개 까지 등록할 수 있으며, 30일 까지 보관됩니다.</h1>
        <button id="delete_all">전체 삭제</button><br>
        <h2>총 ${param.cnt}건</h2>
        <c:forEach items="${list}" var="list">
            <div class="product__list">
                <img src="${list.img_pth}" width="350px" height="250px">
                <div class="text">
                    <p>상품코드 ${list.prd_cd}</p>
                    <p>${list.prd_nm}</p>
                    <strong><fmt:formatNumber value="${list.prd_str_prc}" pattern="#,##0"/></strong><em> 원~</em>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
