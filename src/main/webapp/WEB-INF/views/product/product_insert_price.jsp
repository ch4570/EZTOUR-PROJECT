<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_insert_price_style.css'/>">
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
                </ul>
                <ul>
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
            <div class="board">
                <h1>상품 가격 등록</h1>
                <div class="prd_price_input_form">
                    <form action="<c:url value='/product/insert/price'/>" method="post">
                        <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>
                        상품상세코드&nbsp;<br><input type="text" value="${param.prd_dtl_cd}" name="prd_dtl_cd" placeholder="상품상세코드" readonly="readonly" class="input_prd"/><br>
                        상품코드&nbsp;<br><input type="text" value="${param.prd_cd}" name="prd_cd" placeholder="상품코드" readonly="readonly" class="input_prd"/><br>
                        성인요금&nbsp;<br><input type="text" name="adt_prc" placeholder="성인요금" class="input_prd"/><br>
                        아동요금&nbsp;<br><input type="text" name="chd_prc" placeholder="아동요금" class="input_prd"/><br>
                        유아요금&nbsp;<br><input type="text" name="bb_prc" placeholder="유아요금" class="input_prd"/><br>
                        <input type="submit" value="전송" id="submit_btn">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
