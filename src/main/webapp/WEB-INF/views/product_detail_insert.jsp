<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="<c:url value='/product/detail/insert'/>" method="post">
    <input type="text" name="prd_dtl_cd" placeholder="상품상세코드"/><br>
    <input type="text" name="prd_cd" value="${param.prd_cd}" placeholder="상품코드" readonly="readonly"/><br>
    <input type="text" name="prd_str_prc" value="${param.prd_str_prc}" placeholder="상품시작가격" readonly="readonly"/><br>
    <input type="text" name="arl_nm" placeholder="항공사명"/><br>
    <input type="text" name="min_stt_cnt" placeholder="최소출발인원"/><br>
    <input type="text" name="max_stt_cnt" placeholder="최대출발인원"/><br>
    <input type="date" name="dpr_date" placeholder="출발일"/><br>
            <input type="submit" value="전송">
    </form>
</body>
</html>
