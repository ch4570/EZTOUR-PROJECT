<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv_admin.css'/>">
</head>
<body>
<%--<div class="wrap">--%>
<%--    <div class="content">--%>
<div class="reserv_wrap">
    <nav class="reserv_admin_menu">
        <ul>
            <li class="prd_tlt">상품 등록 관리</li>
            <a href="<c:url value='/product/insert'/>"><li class="menu_list">상품 등록</li></a>
            <a href="<c:url value='/product/detail/insert'/>"><li class="menu_list">상품 상세 등록</li></a>
            <a href="<c:url value='/product/insert/image'/>"><li class="menu_list">상품 이미지 등록</li></a>
            <a href="<c:url value='/product/insert/schedule'/>"><li class="menu_list">상품 일정 등록</li></a>
            <a href="<c:url value='/product/insert/price'/>"><li class="menu_list">상품 가격 등록</li></a>
            <a href="<c:url value='/product/schedule/image/insert'/>"><li class="menu_list">상품 일정 사진 등록</li></a>
        </ul>
        <ul>
            <li class="prd_tlt">상품 등록 현황 관리</li>
            <a href="<c:url value='/product/management'/>"><li class="menu_list">상품 관리</li></a>
            <a href="<c:url value='/product/management/detail'/>"><li class="menu_list">상품 상세 관리</li></a>
            <a href="<c:url value='/product/management/image'/>"><li class="menu_list">상품 이미지 관리</li></a>
            <a href="<c:url value='/product/management/schedule'/>"><li class="menu_list">상품 일정 관리</li></a>
            <a href="<c:url value='/product/management/price'/>"><li class="menu_list">상품 가격 관리</li></a>
            <a href="<c:url value='/product/management/schedule/image'/>"><li class="menu_list">상품 일정 사진 관리</li></a>
        </ul>
        <ul>
            <li class="prd_tlt">상품 승인 관리</li>
            <a href="<c:url value='/product/recognize'/>"><li class="menu_list">상품 승인</li></a>
            <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
        </ul>
        <ul>
            <li class="prd_tlt">고객 관리</li>
            <li class="menu_list">고객 서비스 제안 관리</li>
            <li class="menu_list">1:1 문의 관리</li>
        </ul>
        <ul>
            <li class="prd_tlt">통계</li>
            <a href="<c:url value='/product/stats'/>"><li class="menu_list">예약 및 구매 통계</li></a>
        </ul>
    </nav>
    <div class="reserv_admin_box">
        <div class="board">
            <div class="board_headbox">
                <div class="col1">예약번호</div> <div class="col2">대표자 이름</div> <div class="col3">전화번호</div> <div class="col4">예약날짜</div> <div class="col5">승인/반려</div>
            </div>
            <div class="board_body_box">
                <c:forEach var="usrInfo" items="${list}">
                    <div class="board_body">
                        <div class="apprv_no col1">${usrInfo.rsvt_no}</div>
                        <div class="apprv_nm col2">${usrInfo.mn_rsvt_nm}</div>
                        <div class="apprv_phn col3">${usrInfo.phn}</div>
                        <div class="apprv_date col4"><fmt:formatDate value="${usrInfo.rsvt_date}" pattern="yyyy/MM/dd hh:mm:ss"/>
                        </div>
                        <div class="apprv_btn_box col5">
                            <button class="apprBtn black_btn">승인</button>
                            <button class="returnBtn white_btn">반려</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="pagingbox">
                <c:if test="${ph.showPrev eq 'true'}">
                <a href="<c:url value="/reserv/admin?page=${ph.beginPage-1}"/>">
                    </c:if>
                    <span>&lt;</span>
                    <c:if test="${ph.showPrev eq 'true'}">
                </a>
                </c:if>

                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                    <a href="<c:url value='/reserv/admin?page=${i}'/>"><span class="reserv_admin_paging">${i}</span></a>
                </c:forEach>

                <c:if test="${ph.showNext eq 'true'}">
                <a href="<c:url value="/reserv/admin?page=${ph.endPage+1}"/>">
                    </c:if>
                    <span>&gt;</span>
                    <c:if test="${ph.showNext eq 'true'}">
                </a>
                </c:if>
            </div>
        </div>
    </div>
<%--        // 끝--%>
</div>
<%--<script src="https://code.jquery.com/jquery-latest.min.js"></script>--%>
<script>
    $(document).ready(function(){
        const RESERV_APPV = '6B';
        const RESERV_RETURNED = '6C';


       $('.apprBtn').on("click", function(){
           let reservDto = {
               rsvt_no: $(this).parent().siblings('.apprv_no').text(),
               cmn_cd_rsvt_stt: RESERV_APPV,
               cmn_cd_pay_stt: '',
               page: '${ph.page}',
               pageSize: '${ph.pageSize}'
           }

           jQuery.ajax({
               url: "/reserv/updateStt",
               method: "POST",
               contentType: "application/json; charset=utf-8",
               data: JSON.stringify(reservDto)
           }).done(function (data){
               let result = JSON.parse(data);
               switch(result.status){
                   case 'FAILED':
                       alert("승인 및 반려가 불가합니다. 예약번호와 상태값을 확인해주세요");
                       break;
                   case 'SUCCESS':
                       let page = parseInt(result.page);
                       let pageSize = parseInt(result.pageSize);
                       alert("상태가 '승인'으로 업데이트 되었습니다");
                       location.href = '<c:url value="/reserv/admin"/>'+'?page='+page+'&pageSize='+pageSize;
               }
           })
       });

       $('.returnBtn').on("click", function(){
           let reservDto = {
               rsvt_no: $(this).parent().siblings('.apprv_no').text(),
               cmn_cd_rsvt_stt: RESERV_APPV,
               cmn_cd_pay_stt: ''
           }

           jQuery.ajax({
               url: "/reserv/updateStt",
               method: "POST",
               contentType: "application/json; charset=utf-8",
               data: JSON.stringify(reservDto)
           }).done(function (data){
               let result = JSON.parse(data);
               switch(result.status){
                   case 'FAILED':
                       alert("승인 및 반려가 불가합니다. 예약번호와 상태값을 확인해주세요");
                       break;
                   case 'SUCCESS':
                       alert("상태가 '반려'로 업데이트 되었습니다");
                       location.href = '<c:url value="/reserv/admin"/>';
               }
           })
        });
    });
</script>
</body>
</html>
