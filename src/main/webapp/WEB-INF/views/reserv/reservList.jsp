<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv.css'/>">
</head>
<body>
    <div class="rl_list_box">
        <h1 class="reserv_main_header">예약내역</h1>
        <div class="rl_total_cnt">총 ${totalCnt}건</div>
        <c:forEach var="reserv" items="${reservList}" begin="0" end="${reservList.size()}">
        <div class="rl_list">
            <a href="<c:url value='/reserv/reservView?rsvt_no=${reserv.rsvt_no}&prd_dtl_cd=${reserv.prd_dtl_cd}'/> ">
                <div class="rsvt_img_box">
<%--                    <img src="http://8080${reserv.img_pth}"/>--%>
                </div>
                <div class="rl_content_box">
                    <div class="rl_info_box">
                        <span class="rl_info_label">예약번호</span>
                        <span class="rl_info">${reserv.rsvt_no}</span> |
                        <span class="rl_info_label">예약일</span>
                        <span class="rl_info"><fmt:formatDate value="${reserv.rsvt_date}" pattern="yyyy-MM-dd"/></span>
                    </div>
                    <div class="rl_stt_box">
                        <c:choose>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6A'}">
                                <span class="rl_rsvt_stt">예약접수</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6B'}">
                                <span class="rl_rsvt_stt">예약승인</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6C'}">
                                <span class="rl_rsvt_stt">예약반려</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6D'}">
                                <span class="rl_rsvt_stt">예약취소</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6E'}">
                                <span class="rl_rsvt_stt">예약완료</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6F'}">
                                <span class="rl_rsvt_stt">예약불가</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_rsvt_stt eq '6G'}">
                                <span class="rl_rsvt_stt">예약기타상태</span>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7A'}">
                                <span class="rl_pay_stt">결제대기</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7B'}">
                                <span class="rl_pay_stt">결제취소</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7C'}">
                                <span class="rl_pay_stt">결제완료</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7D'}">
                                <span class="rl_pay_stt">결제실패</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7E'}">
                                <span class="rl_pay_stt">결제준비중</span>
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7F'}">
                                <span class="rl_pay_stt">결제오류</span><!--결제위조시도(금액)-->
                            </c:when>
                            <c:when test="${reserv.cmn_cd_pay_stt eq '7G'}">
                                <span class="rl_pay_stt">결제오류</span><!--결제위조시도(마일리지)-->
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="rl_title_box">
                        <h3>${reserv.prd_nm}</h3>
                        <span>${reserv.prd_dtl_desc}</span>
                    </div>

                    <div class="rl_prc"><fmt:formatNumber value="${reserv.sum_prc}" type="number"/><span>원</span></div>
                </div>
            </a>
        </div>
        </c:forEach>

        <div class="rl_paging_box">
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/reserv/list?page=${ph.beginPage - 1}&pageSize=${ph.pageSize}'/>" class="arrow_black">
            </c:if>
                <span>&#60;</span>
            <c:if test="${ph.showPrev}">
                </a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}" varStatus="status">
                <a href="<c:url value='/reserv/list?page=${i}&pageSize=${ph.pageSize}'/>">
                    <span>${i}</span>
                </a>
            </c:forEach>
            <c:if test="${ph.showNext}">
                <a href="<c:url value='/reserv/list?page=${ph.endPage + 1}&pageSize=${ph.pageSize}'/>" class="arrow_black">
            </c:if>
                <span>&#62;</span>
            <c:if test="${ph.showNext}">
                </a>
            </c:if>
        </div>
    </div>
</body>
</html>
