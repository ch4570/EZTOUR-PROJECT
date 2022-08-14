<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_insert_style.css'/>">
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
                    <li class="menu_list">상품 관리</li>
                    <li class="menu_list">상품 상세 관리</li>
                    <li class="menu_list">상품 이미지 관리</li>
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
                    <h1>상품 등록</h1>
                        <div class="prd_input_form">
                            <form action="<c:url value='/product/insert'/>" method="post">
                            <input type="text"  class="input_prd" name="prd_cd" placeholder="상품코드"><br>
                            <input type="text" class="input_prd" name="dstn_cd" placeholder="여행지 코드"><br>
                            <input type="text" class="input_prd" name="cmn_cd_thm" placeholder="공통코드_테마상태"><br>
                            <input type="text" class="input_prd" name="prd_nm" placeholder="상품명"><br>
                                <textarea cols="24" rows="10" name="prd_dtl_desc" placeholder="상품 상세 설명" id="desc_area"></textarea><br>
                            <input type="text" class="input_prd" name="trv_per" placeholder="여행기간"><br>
                            <input type="text" class="input_prd" name="prd_str_prc" placeholder="상품시작가격"><br>
                            <input type="date" class="input_prd" name="dpr_str_date" placeholder="출발 시작일"><br>
                            <input type="date" class="input_prd" name="dpr_fin_date" placeholder="출발 마감"><br>
                            <input type="submit" value="상품 등록" id="submit_btn">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
