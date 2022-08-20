<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_detail_modify_style.css'/>">
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
                <h1>상품 상세 수정</h1>
                <div class="prd_detail_input_form">
                        <form:form action="/product/detail/modify" method="post" modelAttribute="trvPrdDtlWriteDto">
                            <form:label path="prd_dtl_cd">상품상세코드</form:label><br>
                            <form:input path="prd_dtl_cd" placeholder="상품상세코드" value="${prd_dtl.prd_dtl_cd}" cssClass="input_prd"/><br>
                            <form:errors path="prd_dtl_cd" cssClass="error_msg"/><br><br>

                            <form:label path="prd_cd">상품코드</form:label><br>
                            <form:input path="prd_cd" placeholder="상품코드" value="${prd_dtl.prd_cd}" cssClass="input_prd"/><br>
                            <form:errors path="prd_cd" cssClass="error_msg"/><br><br>

                            <form:label path="prd_nm">상품이름</form:label><br>
                            <form:input path="prd_nm" placeholder="상품이름" value="${prd_dtl.prd_nm}" cssClass="input_prd"/><br>
                            <form:errors path="prd_nm" cssClass="error_msg"/><br><br>

                            <form:label path="prd_str_prc">상품시작가격</form:label><br>
                            <form:input path="prd_str_prc" placeholder="상품시작가격" value="${prd_dtl.prd_str_prc}" cssClass="input_prd"/><br>
                            <form:errors path="prd_str_prc" cssClass="error_msg"/><br><br>

                            <form:label path="arl_nm">항공사명</form:label><br>
                            <form:input path="arl_nm" placeholder="항공사명" value="${prd_dtl.arl_nm}" cssClass="input_prd"/><br>
                            <form:errors path="arl_nm" cssClass="error_msg"/><br><br>

                            <form:label path="min_stt_cnt">최소출발인원</form:label><br>
                            <form:input path="min_stt_cnt" placeholder="최소출발인원" value="${prd_dtl.min_stt_cnt}" cssClass="input_prd"/><br>
                            <form:errors path="min_stt_cnt" cssClass="error_msg"/><br><br>

                            <form:label path="max_stt_cnt">최대출발인원</form:label><br>
                            <form:input path="max_stt_cnt" placeholder="최대출발인원" value="${prd_dtl.max_stt_cnt}" cssClass="input_prd"/><br>
                            <form:errors path="max_stt_cnt" cssClass="error_msg"/><br><br>

                            <fmt:parseDate var="dpr_date" value="${prd_dtl.dpr_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            <fmt:parseDate var="fin_date" value="${prd_dtl.fin_date}" pattern="yyyy-MM-dd HH:mm:ss"/>

                            <form:label path="dpr_date">출발일</form:label><br>
                            <input type="datetime-local" name="dpr_date" placeholder="출발일" class="input_prd" value="<fmt:formatDate value="${dpr_date}" pattern="yyyy-MM-dd HH:mm:ss"/>"/><br>
                            <form:errors path="dpr_date" cssClass="error_msg"/><br><br>

                            <form:label path="fin_date">도착일</form:label><br>
                            <input type="datetime-local" name="fin_date" placeholder="도착일" class="input_prd" value="<fmt:formatDate value="${fin_date}" pattern="yyyy-MM-dd HH:mm:ss"/>"/><br>
                            <form:errors path="fin_date" cssClass="error_msg"/><br><br>

                            <input type="submit" value="수정" id="submit_btn"/>
                        </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
