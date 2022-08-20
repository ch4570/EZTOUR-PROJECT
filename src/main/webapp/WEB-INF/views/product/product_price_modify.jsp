<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_price_modify_style.css'/>">
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
                    <a href="<c:url value='/product/recognize'/>"><li class="menu_list">상품 승인</li></a>
                    <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>상품 가격 수정</h1>
                <div class="prd_price_input_form">
                    <form:form action="/product/price/modify" method="post" modelAttribute="trvPrdPrcDto">
                        <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>
                        <input type="hidden" name="prd_prc_no" value="${list.prd_prc_no}"/>

                        <form:label path="prd_dtl_cd">상품상세코드</form:label><br>
                        <form:input path="prd_dtl_cd" value="${list.prd_dtl_cd}" placeholder="상품상세코드" cssClass="input_prd"/><br>
                        <form:errors path="prd_dtl_cd" cssClass="error_msg"/><br><br>

                        <form:label path="prd_cd">상품코드</form:label><br>
                        <form:input path="prd_cd" value="${list.prd_cd}" placeholder="상품코드" cssClass="input_prd"/><br>
                        <form:errors path="prd_cd" cssClass="error_msg"/><br><br>

                        <form:label path="adt_prc">성인요금</form:label><br>
                        <form:input path="adt_prc" placeholder="성인요금" cssClass="input_prd" value="${list.adt_prc}"/><br>
                        <form:errors path="adt_prc" cssClass="error_msg"/><br><br>

                        <form:label path="chd_prc">아동요금</form:label><br>
                        <form:input path="chd_prc" placeholder="아동요금" cssClass="input_prd" value="${list.chd_prc}"/><br>
                        <form:errors path="chd_prc" cssClass="error_msg"/><br><br>

                        <form:label path="bb_prc">유아요금</form:label><br>
                        <form:input path="bb_prc" placeholder="유아요금" cssClass="input_prd" value="${list.bb_prc}"/><br>
                        <form:errors path="bb_prc" cssClass="error_msg"/><br><br>

                        <input type="submit" value="수정" id="submit_btn">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>