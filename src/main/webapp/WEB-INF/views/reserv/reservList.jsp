<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<style>
    .reserv_box{
        border : solid #cccccc;
    }
</style>
<body>
<div>
    <h1>예약내역</h1>
<%--    <div><span>총 ${reservList.size()}건</span></div>--%>
    <c:forEach var="reserv" items="${reservList}" begin="0" end="${reservList.size()}">
        <div class="reserv_box">
            <a href="<c:url value='/reserv/reservView?rsvt_no=${reserv.rsvt_no}&prd_dtl_cd=${reserv.prd_dtl_cd}'/> ">
                <div>
                    <span>예약번호</span>
                    <span>${reserv.rsvt_no}</span> |
                    <span>예약일</span>
                    <span>${reserv.rsvt_date}</span>
                </div>
                <div>
                    <span>${reserv.cmn_cd_rsvt_stt} </span>
                    <span>${reserv.cmn_cd_pay_stt}</span>
                </div>
                <div>
                    <h3>${reserv.prd_nm}</h3>
                    <span>${reserv.prd_dtl_desc}</span>
                </div>

                <div>${reserv.sum_prc}</div>
            </a>
        </div>
    </c:forEach>
</div>
</body>
</html>
