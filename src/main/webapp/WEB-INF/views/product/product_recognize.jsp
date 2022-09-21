<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_recognize_style.css'/>">
</head>
<body>

<div class="admin__wrap">
    <div class="admin__main">
        <div class="admin__side-menu">

            <div class="side-menu">
                <ul class="side-menu__one">
                    <li class="prd_tlt">상품 등록 관리</li>
                    <a href="<c:url value='/product/insert'/>"><li class="menu_list">상품 등록</li></a>
                    <a href="<c:url value='/product/detail/insert'/>"><li class="menu_list">상품 상세 등록</li></a>
                    <a href="<c:url value='/product/insert/image'/>"><li class="menu_list">상품 이미지 등록</li></a>
                    <a href="<c:url value='/product/insert/schedule'/>"><li class="menu_list">상품 일정 등록</li></a>
                    <a href="<c:url value='/product/insert/price'/>"><li class="menu_list">상품 가격 등록</li></a>
                    <a href="<c:url value='/product/schedule/image/insert'/>"><li class="menu_list">상품 일정 사진 등록</li></a>
                </ul>
                <ul class="side-menu__two">
                    <li class="prd_tlt">상품 등록 현황 관리</li>
                    <a href="<c:url value='/product/management'/>"><li class="menu_list">상품 관리</li></a>
                    <a href="<c:url value='/product/management/detail'/>"><li class="menu_list">상품 상세 관리</li></a>
                    <a href="<c:url value='/product/management/image'/>"><li class="menu_list">상품 이미지 관리</li></a>
                    <a href="<c:url value='/product/management/schedule'/>"><li class="menu_list">상품 일정 관리</li></a>
                    <a href="<c:url value='/product/management/price'/>"><li class="menu_list">상품 가격 관리</li></a>
                    <a href="<c:url value='/product/management/schedule/image'/>"><li class="menu_list">상품 일정 사진 관리</li></a>
                </ul>
                <ul class="side-menu__three">
                    <li class="prd_tlt">상품 승인 관리</li>
                    <a href="<c:url value='/product/recognize'/>"><li class="menu_list">상품 승인</li></a>
                    <a href="<c:url value='/reserv/admin'/>"><li class="menu_list">예약 승인</li></a>
                </ul>
                <ul class="side-menu__four">
                    <li class="prd_tlt">고객 관리</li>
                    <li class="menu_list">고객 서비스 제안 관리</li>
                    <li class="menu_list">1:1 문의 관리</li>
                </ul>
            </div>
        </div>

        <div class="admin__content">

            <div class="admin__tit">
                <span>상품 승인</span>
            </div>

            <div class="admin__board">
                <div class="admin__board--tit">
                    <div class="admin__board--tit_pcode">상품코드</div>
                    <div class="admin__board--tit_tcode">테마코드</div>
                    <div class="admin__board--tit_name">상품이름</div>
                    <div class="admin__board--tit_price">시작가격</div>
                    <div class="admin__board--tit_state">활성화상태</div>
                </div>
                <c:forEach var="prd_list" items="${prd_list}">
                    <div class="board_content">
                        <div class="board_content_detail">
                            <div class="admin__board--tit_pcode">
                                <span>${prd_list.prd_cd}</span>
                            </div>
                            <div class="admin__board--tit_tcode">
                                <span>${prd_list.cmn_cd_thm}</span>
                            </div>
                            <div class="admin__board--tit_name">
                                <a href="<c:url value='/product/recognize/read?prd_cd=${prd_list.prd_cd}'/>">
                                    <span>${prd_list.prd_nm}</span>
                                </a>
                            </div>
                            <div class="admin__board--tit_price">
                                <span><fmt:formatNumber value="${prd_list.prd_str_prc}" pattern="#,##0"/></span>
                            </div>
                            <div class="admin__board--tit_state">
                                <span style="color:${prd_list.act_yn == 1 ? 'blue' : 'red'}">${prd_list.act_yn == 1 ? '활성화' : '비활성화'}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="search_option_form">
                <form action="<c:url value='/product/recognize'/>">
                    <select name="search_option" id="search_option">
                        <option value="" selected>검색옵션</option>
                        <option value="prd_cd">상품코드</option>
                        <option value="prd_nm" >상품이름</option>
                    </select>
                    <input type="text" name="search_keyword" placeholder="검색어" id="search_keyword">
                    <input type="submit" id="search_btn" value="검색">
                </form>
            </div>

            <div class="paging_list">
                <c:if test="${paging.preView eq 'true'}">
                    <a href="<c:url value="/product/recognize?page=${paging.beginPage-1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&lt;</span></a>
                </c:if>
                <c:forEach var="i" begin="${paging.beginPage}" end="${paging.endPage}">
                    <a href="<c:url value='/product/recognize?page=${i}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}'/>"><span>${i}</span></a>
                </c:forEach>
                <c:if test="${paging.nextView eq 'true'}">
                    <a href="<c:url value="/product/recognize?page=${paging.endPage+1}&search_keyword=${paging.search_keyword}&search_option=${paging.search_option}"/>"><span>&gt;</span></a>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
