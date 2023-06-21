<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2023-06-19
  Time: 오후 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value='/css/product/product_stats_style.css'/>">
</head>
<body>
<div class="wrap">
    <div class="content">
        <h1 class="head">관리자 페이지</h1>
        <div class="stats_container">
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
                    <ul>
                        <li class="prd_tlt">통계</li>
                        <a href="<c:url value='/product/stats'/>"><li class="menu_list">예약 및 구매 통계</li></a>
                    </ul>
                </div>
            </div>
            <div class="stats_charts_container">
                <c:set var="gndrAndAgePerHourReserv" value="${gndrAndAgePerHourReservStats}"/>
                <div class="stats_charts">
                    <p class="stats_charts_title">시간 별 예약자 연령대</p>
                    <c:choose>
                        <c:when test="${gndrAndAgePerHourReserv == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <div class="stats_box">
                                <canvas id="chart_reserv_age"></canvas>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">시간 별 예약자 성별</p>
                    <c:choose>
                        <c:when test="${gndrAndAgePerHourReserv == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <div class="stats_box">
                                <canvas id="chart_reserv_gndr"></canvas>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:set var="gndrAndAgePerHourPay" value="${gndrAndAgePerHourPayStats}"/>
                <div class="stats_charts">
                    <p class="stats_charts_title">시간 별 구매자 연령대</p>
                    <c:choose>
                        <c:when test="${gndrAndAgePerHourPay == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <div class="stats_box">
                                <canvas id="chart_pay_age"></canvas>
                            </div>
                    </c:otherwise>
                </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">시간 별 구매자 성별</p>
                    <c:choose>
                        <c:when test="${gndrAndAgePerHourPay == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <div class="stats_box">
                                <canvas id="chart_pay_gndr"></canvas>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">예약상품 TOP 5</p>
                    <c:choose>
                        <c:when test="${topFiveReservList == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="reservPrd" items="${topFiveReservList}" begin="0" end="${topFiveReservList.size()}" varStatus="status">
                            <div class="stats_box stats_toplist">
                                <span class="stats_toplist_index stats_toplist_el">${status.count}</span>
                                <div class="stats_toplist_prdcd stats_toplist_el">${reservPrd.prd_dtl_cd}</div>
                                <div class="stats_toplist_nm stats_toplist_el">
                                    <a href="<c:url value='/product/detail?prd_dtl_cd=${reservPrd.prd_dtl_cd}'/> ">
                                        ${reservPrd.prd_nm}
                                    </a>
                                </div>
                                <div class="stats_toplist_count stats_toplist_el">
                                    <fmt:parseNumber var="cnt" value="${reservPrd.num}" integerOnly="true" />
                                    ${cnt}건
                                </div>
                            </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">구매 상품 TOP 5</p>
                    <c:choose>
                        <c:when test="${topFivePayList == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="payPrd" items="${topFivePayList}" begin="0" end="${topFivePayList.size()}" varStatus="status">
                                <div class="stats_box stats_toplist">
                                    <span class="stats_toplist_index stats_toplist_el">${status.count}</span>
                                    <div class="stats_toplist_prdcd stats_toplist_el">${payPrd.prd_dtl_cd}</div>
                                    <div class="stats_toplist_nm stats_toplist_el">
                                        <a href="<c:url value='/product/detail?prd_dtl_cd=${payPrd.prd_dtl_cd}'/> ">
                                                ${payPrd.prd_nm}
                                        </a>
                                    </div>
                                    <div class="stats_toplist_count stats_toplist_el">
                                        <fmt:parseNumber var="cnt" value="${payPrd.num}" integerOnly="true" />
                                            ${cnt}건
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">예약후 구매할 확률이 가장 높은 상품 TOP 5</p>
                    <c:choose>
                        <c:when test="${topFivePrdLikelyPay == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="prdLikelyPay" items="${topFivePrdLikelyPay}" begin="0" end="${topFivePrdLikelyPay.size()}" varStatus="status">
                                <div class="stats_box stats_toplist">
                                    <span class="stats_toplist_index stats_toplist_el">${status.count}</span>
                                    <div class="stats_toplist_prdcd stats_toplist_el">${prdLikelyPay.prd_dtl_cd}</div>
                                    <div class="stats_toplist_nm stats_toplist_el">
                                        <a href="<c:url value='/product/detail?prd_dtl_cd=${prdLikelyPay.prd_dtl_cd}'/> ">
                                                ${prdLikelyPay.prd_nm}
                                        </a>
                                    </div>
                                    <div class="stats_toplist_count stats_toplist_el">
                                            ${prdLikelyPay.num}%
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="stats_charts">
                    <p class="stats_charts_title">여행 인원 통계</p>
                    <c:choose>
                        <c:when test="${trvlrCntStatsJson == null}">
                            <div class="stats_info_nodata">데이터를 가져올 수 없습니다. 관리자에게 문의해주세요</div>
                        </c:when>
                        <c:otherwise>
                            <div class="stats_box stats_piecharts">
                                <canvas id="chart_trvlr_cnt"></canvas>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    // $(document).ready(function (){
    //
    // });
    const ageReservChart = document.getElementById('chart_reserv_age');
    const gndrReservChart = document.getElementById('chart_reserv_gndr');
    const agePayChart = document.getElementById('chart_pay_age');
    const gndrPayChart = document.getElementById('chart_pay_gndr');
    const trvlrCntChart = document.getElementById('chart_trvlr_cnt');

    const gndrAndAgeReservList = ${gndrAndAgePerHourReserv};
    const gndrAndAgePayList = ${gndrAndAgePerHourPay};
    const trvlrCntObj = ${trvlrCntStatsJson};

    new Chart(gndrReservChart, {
        type: 'line',
        data: {
            labels: Array.from(gndrAndAgeReservList, obj => obj.hh + "시"),
            datasets: [{
                label: '남성',
                data: Array.from(gndrAndAgeReservList, obj => obj.maleCnt),
                fill: false,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            },{
                label: '여성',
                data: Array.from(gndrAndAgeReservList, obj => obj.femaleCnt),
                fill: false,
                backgroundColor: 'rgba(255, 205, 86, 0.2)',
                borderColor: 'rgb(255, 205, 86)',
                tension: 0.1
            }]
        }
    });

    new Chart(ageReservChart, {
        data: {
            labels: Array.from(gndrAndAgeReservList, obj => obj.hh + "시"),
            datasets: [{
                type: 'bar',
                label: '10대 이하',
                data: Array.from(gndrAndAgeReservList, obj => obj.teensCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '20대',
                data: Array.from(gndrAndAgeReservList, obj => obj.twentiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '30대',
                data: Array.from(gndrAndAgeReservList, obj => obj.thirtiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '40대',
                data: Array.from(gndrAndAgeReservList, obj => obj.fortiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '50대',
                data: Array.from(gndrAndAgeReservList, obj => obj.fiftiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '60대 이상',
                data: Array.from(gndrAndAgeReservList, obj => obj.overSixtiesCnt),
                borderWidth: 1
            },{
                type: 'line',
                label: '시간별 총합',
                data: Array.from(gndrAndAgeReservList, obj => obj.teensCnt
                    + obj.twentiesCnt + obj.thirtiesCnt + obj.fortiesCnt + obj.fiftiesCnt + obj.overSixtiesCnt)
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    new Chart(gndrPayChart, {
        type: 'line',
        data: {
            labels: Array.from(gndrAndAgePayList, obj => obj.hh + "시"),
            datasets: [{
                label: '남성',
                data: Array.from(gndrAndAgePayList, obj => obj.maleCnt),
                fill: false,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            },{
                label: '여성',
                data: Array.from(gndrAndAgePayList, obj => obj.femaleCnt),
                fill: false,
                backgroundColor: 'rgba(255, 205, 86, 0.2)',
                borderColor: 'rgb(255, 205, 86)',
                tension: 0.1
            }]
        }
    });

    new Chart(agePayChart, {
        data: {
            labels: Array.from(gndrAndAgePayList, obj => obj.hh + "시"),
            datasets: [{
                type: 'bar',
                label: '10대 이하',
                data: Array.from(gndrAndAgePayList, obj => obj.teensCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '20대',
                data: Array.from(gndrAndAgePayList, obj => obj.twentiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '30대',
                data: Array.from(gndrAndAgePayList, obj => obj.thirtiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '40대',
                data: Array.from(gndrAndAgePayList, obj => obj.fortiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '50대',
                data: Array.from(gndrAndAgePayList, obj => obj.fiftiesCnt),
                borderWidth: 1
            },{
                type: 'bar',
                label: '60대 이상',
                data: Array.from(gndrAndAgePayList, obj => obj.overSixtiesCnt),
                borderWidth: 1
            },{
                type: 'line',
                label: '시간별 총합',
                data: Array.from(gndrAndAgePayList, obj => obj.teensCnt
                    + obj.twentiesCnt + obj.thirtiesCnt + obj.fortiesCnt + obj.fiftiesCnt + obj.overSixtiesCnt)
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    new Chart(trvlrCntChart,{
        type: 'doughnut',
        data: {
            labels: Array.from(Object.keys(trvlrCntObj)),
            datasets: [{
                label: '여행자 수',
                data: Array.from(Object.values(trvlrCntObj)),
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(75, 192, 192)',
                    'rgb(255, 205, 86)',
                    'rgb(54, 162, 235)',
                    'rgb(105,54,235)',
                    'rgb(121,157,72)',
                    'rgb(243,170,222)',
                    'rgb(201, 203, 207)',
                ]
            }]
        },
        options: {}
    })
</script>
</body>
</html>
