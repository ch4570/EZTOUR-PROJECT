<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_modify_sch_style.css'/>">
</head>
<body>
<div class="wrap">
    <div class="content">
        <h1>관리자 페이지</h1>
        <div class="nav">
            <div class="menu">
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
                    <li class="menu_list">상품 승인</li>
                    <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>상품 일정 수정</h1>
                <div class="prd_sch_input_form">
                    <form:form action="/product/schedule/modify" method="post" modelAttribute="trvSchDto">
                        <input type="hidden" value="${list.sch_no}" name="sch_no">

                        <form:label path="prd_cd">상품코드</form:label><br>
                        <form:input path="prd_cd" placeholder="상품코드" value="${list.prd_cd}" cssClass="input_prd"/><br>
                        <form:errors path="prd_cd" cssClass="error_msg"/><br><br>

                        <form:label path="trv_date">여행일차</form:label><br>
                        <form:input path="trv_date" placeholder="여행일차" value="${list.trv_date}" cssClass="input_prd"/><br>
                        <form:errors path="trv_date" cssClass="error_msg"/><br><br>

                        <form:label path="sch_ord">일정순번</form:label><br>
                        <form:input path="sch_ord" placeholder="일정순번" value="${list.sch_ord}" cssClass="input_prd"/><br>
                        <form:errors path="sch_ord" cssClass="error_msg"/><br><br>

                        <form:label path="st_nm">관광지이름</form:label><br>
                        <form:input path="st_nm" placeholder="관광지이름" value="${list.st_nm}" cssClass="input_prd"/><br>
                        <form:errors path="st_nm" cssClass="error_msg"/><br><br>

                        <form:label path="sit_sh_desc">관광지 간략설명</form:label><br>
                        <textarea name="sit_sh_desc" placeholder="관광지 간략설명" id="sit_sh_desc" cols="22" rows="5">${list.sit_sh_desc}</textarea><br>
                        <form:errors path="sit_sh_desc" cssClass="error_msg"/><br><br>

                        <form:label path="sit_lo_desc">관광지 상세설명</form:label><br>
                        <textarea name="sit_lo_desc" placeholder="관광지 상세설명" id="sit_lo_desc" cols="22" rows="5">${list.sit_lo_desc}</textarea><br>
                        <form:errors path="sit_lo_desc" cssClass="error_msg"/><br><br>

                        <form:label path="ht_inf">호텔정보</form:label><br>
                        <form:input path="ht_inf" placeholder="호텔정보" value="${list.ht_inf}" cssClass="input_prd"/><br>
                        <form:errors path="ht_inf" cssClass="error_msg"/><br><br>

                        <form:label path="brk">아침</form:label><br>
                        <form:input path="brk" placeholder="아침" value="${list.brk}" cssClass="input_prd"/><br>
                        <form:errors path="brk" cssClass="error_msg"/><br><br>

                        <form:label path="luh">점심</form:label><br>
                        <form:input path="luh" placeholder="점심" value="${list.luh}" cssClass="input_prd"/><br>
                        <form:errors path="luh" cssClass="error_msg"/><br><br>

                        <form:label path="din">저녁</form:label><br>
                        <form:input path="din" placeholder="저녁" value="${list.din}" cssClass="input_prd"/><br>
                        <form:errors path="din" cssClass="error_msg"/><br><br>

                        이동소요기간&nbsp;<br><input type="text" name="dstnc_tm" placeholder="이동소요시간" value="${list.dstnc_tm}" class="input_prd"/><br>
                        <input type="submit" value="수정" id="submit_btn"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
