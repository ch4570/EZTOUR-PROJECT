<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                    <li class="menu_list">상품 일정 관리</li>
                    <li class="menu_list">상품 가격 관리</li>
                    <li class="menu_list">상품 일정 사진 관리</li>
                </ul>
                <ul>
                    <li class="prd_tlt">상품 승인 관리</li>
                    <li class="menu_list">상품 승인</li>
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
                        <form action="<c:url value='/product/detail/modify'/>" method="post">
                        상품상세코드&nbsp;<br><input type="text" name="prd_dtl_cd" placeholder="상품상세코드" value="${prd_dtl.prd_dtl_cd}" class="input_prd"/><br>
                        상품코드&nbsp;<br><input type="text" name="prd_cd" value="${prd_dtl.prd_cd}" placeholder="상품코드" class="input_prd"/><br>
                        상품이름&nbsp;<br><input type="text" name="prd_nm" placeholder="상품이름" class="input_prd" value="${prd_dtl.prd_nm}"/><br>
                        상품시작가격&nbsp;<br><input type="text" name="prd_str_prc"  placeholder="상품시작가격" class="input_prd" value="${prd_dtl.prd_str_prc}"/><br>
                        항공사명&nbsp;<br><input type="text" name="arl_nm" placeholder="항공사명" class="input_prd" value="${prd_dtl.arl_nm}"/><br>
                        최소출발인원&nbsp;<br><input type="text" name="min_stt_cnt" placeholder="최소출발인원" class="input_prd" value="${prd_dtl.min_stt_cnt}"/><br>
                        최대출발인원&nbsp;<br><input type="text" name="max_stt_cnt" placeholder="최대출발인원" class="input_prd" value="${prd_dtl.max_stt_cnt}"/><br>
                        <fmt:parseDate var="dpr_date" value="${prd_dtl.dpr_date}" pattern="yyyy-MM-dd"/>
                        출발일&nbsp;<br><input type="date" name="dpr_date" placeholder="출발일" class="input_prd" value="<fmt:formatDate value="${dpr_date}" pattern="yyyy-MM-dd"/>"/><br>
                            <input type="submit" value="수정" id="submit_btn"/>
                        </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
