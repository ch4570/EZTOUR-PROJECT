<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<c:url value='/product/insert'/>" method="post">
        <input type="text" name="prd_cd" placeholder="상품코드"><br>
        <input type="text" name="dstn_cd" placeholder="여행지 코드"><br>
        <input type="text" name="cmn_cd_thm" placeholder="공통코드_테마상태"><br>
        <input type="text" name="prd_nm" placeholder="상품명"><br>
        <input type="text" name="prd_dtl_desc" placeholder="상품상세설명"><br>
        <input type="text" name="trv_per" placeholder="여행기간"><br>
        <input type="text" name="prd_str_prc" placeholder="상품시작가격"><br>
        <input type="date" name="dpr_str_date" placeholder="출발 시작일"><br>
        <input type="date" name="dpr_fin_date" placeholder="출발 마감"><br>
        <input type="submit" value="상품 등록">
    </form>
</body>
</html>
