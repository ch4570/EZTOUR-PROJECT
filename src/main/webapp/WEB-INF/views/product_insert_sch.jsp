<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>상품 일정 입력</h1>
    <form action="<c:url value='/product/insert/schedule'/>" method="post">
        <input type="text" name="prd_cd" placeholder="상품코드" value="${param.prd_cd}"/><br>
        <input type="text" name="trv_date" placeholder="여행일차"/><br>
        <input type="text" name="sch_ord" placeholder="일정순번"><br>
        <input type="text" name="st_name" placeholder="관광지이름"/><br>
        <input type="text" name="sit_sh_desc" placeholder="관광지 간략설명"/><br>
        <input type="text" name="sit_lo_desc" placeholder="관광지 상세설명"/><br>
        <input type="text" name="ht_inf" placeholder="호텔정보"/><br>
        <input type="text" name="brk" placeholder="아침"/><br>
        <input type="text" name="luh" placeholder="점심"/><br>
        <input type="text" name="din" placeholder="저녁"/><br>
        <input type="text" name="dstnc_tm" placeholder="이동소요시간"/><br>
        <input type="submit" value="전송"/>
    </form>
</body>
</html>
