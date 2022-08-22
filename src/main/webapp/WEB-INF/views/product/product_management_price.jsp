<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_management_price_style.css'/>">
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
                <div class="board_title">
                    <span>상품상세코드</span> <span>성인요금</span> <span>아동요금</span> <span>유아요금</span> <span>등록일자</span>
                </div>
                <c:forEach var="list" items="${list}">
                    <div class="board_content">
                        <div class="board_content_detail">
                            <div><a href="<c:url value='/product/price/read?prd_prc_no=${list.prd_prc_no}'/>"><span>${list.prd_dtl_cd}</span></a></div>
                            <div><span><fmt:formatNumber value="${list.adt_prc}" pattern="#,##0"/></span></div>
                            <div><span><fmt:formatNumber value="${list.chd_prc}" pattern="#,##0"/></span></div>
                            <div><span><fmt:formatNumber value="${list.bb_prc}" pattern="#,##0"/></span></div>
                            <fmt:parseDate value="${list.frs_reg_date}" var="frs_reg_date" pattern="yyyy-MM-dd"/>
                            <div><span><fmt:formatDate value="${frs_reg_date}" pattern="yyyy-MM-dd"/></span></div>
                        </div>
                    </div>
                </c:forEach>
                <div class="search_option_form">
                    <form action="<c:url value='/product/management/price'/>">
                        <select name="search_option" id="search_option">
                            <option value="" selected>검색옵션</option>
                            <option value="prd_dtl_cd">상품상세코드</option>
                            <option value="prd_cd">상품코드</option>
                        </select>
                        <input type="text" name="search_keyword" placeholder="검색어" id="search_keyword">
                        <input type="submit" id="search_btn" value="검색">
                    </form>
                </div>
                <div class="paging_list">
                    <c:if test="${paging.preView eq 'true'}">
                        <a href="<c:url value="/product/management/price?page=${paging.beginPage-1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&lt;</span></a>
                    </c:if>
                    <c:forEach var="i" begin="${paging.beginPage}" end="${paging.endPage}">
                        <a href="<c:url value='/product/management/price?page=${i}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}'/>"><span>${i}</span></a>
                    </c:forEach>
                    <c:if test="${paging.nextView eq 'true'}">
                        <a href="<c:url value="/product/management/price?page=${paging.endPage+1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&gt;</span></a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
