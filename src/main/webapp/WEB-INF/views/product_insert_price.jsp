<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>상품 금액 입력</h1>
    <form action="<c:url value='/product/insert/price'/>" method="post">
        <input type="text" value="${param.prd_dtl_cd}" name="prd_dtl_cd" placeholder="상품상세코드" readonly="readonly"/><br>
        <input type="text" value="${param.prd_cd}" name="prd_cd" placeholder="상품코드" readonly="readonly"/><br>
        <input type="text" name="adt_prc" placeholder="성인요금"/><br>
        <input type="text" name="chd_prc" placeholder="아동요금"/><br>
        <input type="text" name="bb_prc" placeholder="유아요금"/><br>
        <input type="submit" value="전송">
    </form>
</body>
</html>
