<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/rvw/rvwList.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="wrap">
    <div class="content" style="padding-top: 100px;">
        <div class="tit">
            <h1>Review</h1>
            <p style="font-size: 13px; padding-top: 20px; color: #666666">이지투어 고객님들의 소중한 후기</p>
        </div>
        <div style="display: flex; flex-direction: column; align-items: center">
            <div class="navi" style="display: flex;">
                <div class="menu" style="margin-top : 80px;">
                    <div class="nation_1">
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list'/>'">전체</span>
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list${ph.sc.getQueryString("A")}'/>'">동남아</span>
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list${ph.sc.getQueryString("B")}'/>'">동아시아</span>
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list${ph.sc.getQueryString("C")}'/>'">동유럽</span>
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list${ph.sc.getQueryString("D")}'/>'">서유럽</span>
                        <span class="menu_name" onclick="location.href='<c:url value='/review/list${ph.sc.getQueryString("E")}'/>'">오세아니아</span>
                    </div>
                    <!--div class="nation_2">
                        <span class="menu_name" onclick="">남아프리카</span>
                        <span class="menu_name" onclick="">북아프리카</span>
                        <span class="menu_name" onclick="">중남미</span>
                        <span class="menu_name" onclick="">동남아</span>
                        <span class="menu_name" onclick="">북유럽</span>
                        <span class="menu_name" onclick="">중동</span>
                    </div-->
                </div>
            </div>

            <div class="searchBox" style="display: flex">
                <form action="<c:url value="/review/list"/>" class="search-form" method="get">
                    <select class="searchOption" name="option" style="text-align: center; height: 46px;">
                        <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>제목+내용</option>
                        <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목만</option>
                        <option value="W" ${ph.sc.option=='W' ? "selected" : ""}>작성자</option>
                    </select>

                    <input class="searchInput" type="text" name="keyword" style="padding-left: 20px" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                    <input class="searchButton sz-inp st-lblue btn_summit" style="height: 45px" type="submit" value="검색">
                </form>
            </div>

            <div style="display: flex; justify-content: space-between; align-items: center; width: 900px; margin-top: 50px">
                <a class="totalCnt" style="font-weight: bold; color: #666666">
                    총 ${totalCnt}건
                </a>

                <div style="display: flex;">
                    <a class="sort_name" href="<c:url value='/review/list${ph.sc.getQueryString("","N")}'/>">최신순 | </a>
                    <a class="sort_name" href="<c:url value='/review/list${ph.sc.getQueryString("","O")}'/>">&nbsp;오래된순 | </a>
                    <!--a class="sort_name" href="<c:url value='/review/list${ph.sc.getQueryString("","L")}'/>">가장 많은 좋아요</a-->
                    <a class="sort_name" href="<c:url value='/review/list${ph.sc.getQueryString("","V")}'/>">&nbsp;가장 많은 조회수</a>
                </div>
            </div>
        </div>

        <!-- 후기 목록 시작 -->
        <div class="reviewArea" style="display: flex; flex-direction: column">
            <c:forEach var="rvwDto" items="${list}">
                <section class="reviewDetail">
                    <%--<a href="<c:url value='/review/read${ph.sc.queryString}&rvw_no=${rvwDto.rvw_no}'/>">리뷰보기</a>--%>
                    <div class="review-section" style="display: flex; justify-content: space-between; height: 180px; width: 1000px;">
                        <div class="reviewInfo" style="display: flex; flex-direction: column; justify-content: space-evenly; padding-left: 20px; width:700px;">
                            <div class="reviewTitle">
                                <br>
                                <a href="<c:url value='/review/read${ph.sc.queryString}&rvw_no=${rvwDto.rvw_no}'/>">
                                    <p class="reviewTitle-rvw_ttl" style="font-size: 20px; font-weight: bold;"><c:out value="${rvwDto.rvw_ttl}"/></p>
                                </a>
                            </div>
                            <div class="reviewContent">
                                <a href="<c:url value='/review/read${ph.sc.queryString}&rvw_no=${rvwDto.rvw_no}'/>">
                                    <p class="reviewContent-rvw_cont" style="font-size: 15px">
                                    <c:choose>
                                        <c:when test="${fn:length(rvwDto.rvw_cont) > 80}">
                                            <c:out value="${fn:substring(rvwDto.rvw_cont,0,79)}"/>....
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${rvwDto.rvw_cont}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    </p>
                                </a>
                            </div>
                            <div style="display: flex; justify-content: space-between; color: #999; font-size: 14px; font-weight: bold; width: 160px;">
                                <div class="reviewWriter">
                                    <span class="reviewWriter-wrt_nm">${rvwDto.wrt_nm}</span>
                                </div>
                                <div class="reviewRegisterDate">
                                    <span class="reviewRegisterDate-rvw_reg_date"><fmt:formatDate value="${rvwDto.rvw_reg_date}" pattern="yyyy-MM-dd"/></span>
                                </div>
                            </div>
                            <div class="reviewLikeViewCnt">
                                <br>
                                <span class="reviewLikeViewCnt-lk_cnt-rvw_cont" style="margin-left: 50px">조회수 ${rvwDto.rvw_vcnt}</span>
                                <br>
                                <br>
                                <!--span class="reviewLikeViewCnt-lk_cnt-rvw_cont" style="margin-left: 50px">좋아요 ${rvwDto.lk_cnt}</span-->
                            </div>
                        </div>
                        <div class="image-box" style="background-image: url(../..${rvwDto.img_pth})">
                        </div>
                    </div>
                </section>
            </c:forEach>

            <div class="pagingContainer">
                <div class="paging">
                    <c:if test="${totalCnt==null || totalCnt==0}"> <!-- 페이지 핸들러추후 작업 필요-->
                        <div> 게시물이 없습니다. </div>
                    </c:if>
                    <c:if test="${totalCnt!=null && totalCnt!=0}">
                        <c:if test="${ph.showPrev}">
                            <a class="page" href="<c:url value="/review/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                        </c:if>
                        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                            <a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/review/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                        </c:forEach>
                        <c:if test="${ph.showNext}">
                            <a class="page" href="<c:url value="/review/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                        </c:if>
                    </c:if>
                </div>
            </div>

            <div>
                <c:choose>
                    <c:when test="${loginId==''}">
                        <button type="button" class="button-27" role="button" id="loginWriteBtn" style="width: 100px; height: 49px;">작성하기</button>
                    </c:when>
                    <c:when test="${loginId!=''}">
                        <button type="button" class="button-27" role="button" id="writeBtn" style=" width: 100px; height: 49px;">작성하기</button>
                    </c:when>
                </c:choose>
            </div>

        </div>
        <!-- 후기 목록 끝 -->

    </div>
</div>
<script>
    $(document).ready(function (){

        $('#writeBtn').on("click", function(){
            location.href = "<c:url value='/review/write'/>";
        });

        $('#loginWriteBtn').on("click", function(){
            if(!confirm("후기 작성하기는 로그인이 필요합니다. 회원가입 하시겠어요?")) return;
            location.href = "<c:url value='/user/login'/>";
        });

    });
</script>
</body>
</html>