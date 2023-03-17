<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/product/product_detail.css">
</head>
<body>

<div class="detail-item__main">

    <div class="detail-item__title">
        <h1>${prdDto.prd_nm}</h1>
    </div>

    <div class="detail-item__info">

        <div class="detail-item__img">
            <img src="${prdDto.img_pth}">
        </div>

        <div class="detail-item__view">
            <div class="detail-item__view--book">
                <span>예약현황</span>
                <span>예약 <em>${prdDto.pr_rsvt_cnt}</em>명</span>
                <span>(남은 좌석 <em>${prdDto.max_stt_cnt-prdDto.pr_rsvt_cnt}</em>석 / 최소 출발인원 <em>${prdDto.min_stt_cnt}</em>명)</span>
            </div>
            <div class="detail-item__view--rvw">
                <a href="<c:url value="/review/list" />" >여행후기</a>
            </div>
        </div>

        <div class="detail-item__price-info">
            <div class="price-info__tit">
                <span>구분</span>
                <span>상품가격</span>
            </div>
            <div class="price-info__age1">
                <span>성인(만 12세 이상)</span>
                <span><fmt:formatNumber value="${prdDto.adt_prc}" pattern="#,##0"/><em>원</em></span>
            </div>
            <div class="price-info__age2">
                <span>아동(만 12세 미만)</span>
                <span><fmt:formatNumber value="${prdDto.chd_prc}" pattern="#,##0"/><em>원</em></span>
            </div>
            <div class="price-info__age3">
                <span>유아(24개월 미만)</span>
                <span><fmt:formatNumber value="${prdDto.bb_prc}" pattern="#,##0"/><em>원</em></span>
            </div>
        </div>
        <span class="price-info__not">- 유류할증료, 제세공과금은 유가와 환율에 따라 변동될 수 있습니다.</span>

        <div class="detail-item__air-info">
            <div class="air-info__air">
                <span>이용교통</span>
                <span>${prdDto.arl_nm}</span>
            </div>

            <div class="detail-item__info-box">
                <div class="dom_dpr_date">
                    <span>한국출발</span>
                    <span>
                        <fmt:formatDate value="${prdDto.dom_dpr_date}" pattern="yyyy년 MM월 dd일 (EE) HH:mm"/>
                    </span>
                </div>
                <div class="loc_dpr_date">
                    <span>현지출발</span>
                    <span>
                        <fmt:formatDate value="${prdDto.loc_dpr_date}" pattern="yyyy년 MM월 dd일 (EE) HH:mm"/>
                    </span>
                </div>
            </div>
            <div class="detail-item__info-box">
                <div class="loc_fin_date">
                    <span>현지도착</span>
                    <span>
                        <fmt:formatDate value="${prdDto.loc_fin_date}" pattern="yyyy년 MM월 dd일 (EE) HH:mm"/>
                    </span>
                </div>
                <div class="dom_fin_date">
                    <span>한국도착</span>
                    <span>
                        <fmt:formatDate value="${prdDto.dom_fin_date}" pattern="yyyy년 MM월 dd일 (EE) HH:mm"/>
                    </span>
                </div>
            </div>

        </div>


        <%--Tap--%>
        <div class="detail-item__menu">
            <div class="ul-wrap">
                <ul class="detail-item__tab">
                    <li class="tab-link selected" data-tab="tab-1">예약안내사항</li>
                    <li class="tab-link" data-tab="tab-2">여행일정</li>
                    <li class="tab-link" data-tab="tab-3">상품문의</li>
                    <li class="tab-link" data-tab="tab-4">약관정보</li>
                    <li class="tab-link" data-tab="tab-5">해외안전정보</li>
                </ul>
            </div>

            <div id="tab-1" class="cont_box selected">
                <div class="cont_box__tit">
                    <span>예약안내사항</span>
                </div>

                <div class="rev-notice">
                    <div class="rev-notice__pre-notice item-cell">
                        <div class="pre-notice__tit">
                            <span>여행전 안내사항</span>
                        </div>
                        <div class="pre-notice__con">
                            <p>① 호텔은 동급의 다른 호텔로 변경 될 수 있습니다. <br>
                                ② 현지 사정 및 휴관일로 인해 입장을 하지 못할 경우 다른 관광지로 대체 됩니다.<br>
                                ③ 현지의 예고없는 기차스케줄 변동으로 일정의 순서 및 관광지에 대한 변동이 있을 수 있습니다.<br>
                                이점 양지하여 주시기 바랍니다.<br>
                                ④ 국적기인 경우, 대한항공 편과 아시아나 항공 편은 출발 7일 전 서로 대체될 수 있습니다.</p>
                        </div>
                    </div>
                    <div class="rev-notice__deposit item-cell">
                        <div class="deposit__tit">
                            <span>입금계좌 안내</span>
                        </div>
                        <div>
                            <span>신한은행 111-123-987654 / (주)세븐일레븐</span>
                        </div>
                    </div>
                    <div class="rev-notice__etc item-cell">
                        <div class="etc__tit">
                            <span>기타사항</span>
                        </div>
                        <div class="etc__con">
                            <p>★여행 약관 12조★<br>
                                국외여행을 실시함에 있어서 이용운송 숙박 기관에 지급하여야 할 요금이 계약 체결시 보다 5%이상 증감 하거나 여행 요금에 적용된 외화 환율이 계약 체결시보다 2%이상 증감한 경우 여행업자 또는 여행자는 증감된 금액범위 내에서여행요금의 증감을 상대방에게 청구 할 수 있습니다.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div id="tab-2" class="cont_box">
                <div class="cont_box__tit">
                    <span class="">여행일정</span>
                </div>

                <div class="luggage">
                    <p class="luggage__tit">수화물 허용량 안내(이코노미 클래스 성인,인천-해당 항공사 허브공항-2016년 6월 기준~)</p>
                    <span>${prdDto.arl_nm}</span>
                    <div class="rev-notice">
                        <div class="rev-notice__pre-notice item-cell">
                            <div class="pre-notice__tit">
                                <span>위탁 수화물 허용량</span>
                            </div>
                            <div class="pre-notice__con">
                                <p>일반석 : 크기 : 3면의 합 158cm 이내, 23kg이하(1개)-미주/브라질 제외, 23kg이하(2개)-미주
                            </div>
                        </div>
                        <div class="rev-notice__deposit item-cell">
                            <div class="deposit__tit">
                                <span>추가 시 요금(무게기준)</span>
                            </div>
                            <div>
                                <span></span>
                            </div>
                        </div>
                        <div class="rev-notice__etc item-cell">
                            <div class="etc__tit">
                                <span>기내 반입 허용 사이즈</span>
                            </div>
                            <div class="etc__con">
                                <p>인당 10kg / 3면의 합 115cm 이내, 표준규격 : 가로 40cm * 세로 55cm * 높이 20cm</p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="whole-schedule">

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>1일차</span>
                        </div>
                        <div class="schedule__cont air_schedule">
                            <div class="start_schedule">
                                <i class="fas fa-plane-departure"></i>
                                <div class="start_schedule-info">
                                    <span><fmt:formatDate value="${prdDto.dom_dpr_date}" pattern="HH:mm"/></span>
                                    <span><fmt:formatDate value="${prdDto.dom_dpr_date}" pattern="yyyy년 MM월 dd일 (EE)"/></span>
                                    <span>인천(ICN) 출발</span>
                                </div>
                            </div>

                            <div class="arrive_schedule">
                                <i class="fas fa-plane-arrival"></i>
                                <div class="arrive_schedule-info">
                                    <span><fmt:formatDate value="${prdDto.loc_fin_date}" pattern="HH:mm"/></span>
                                    <span><fmt:formatDate value="${prdDto.loc_fin_date}" pattern="yyyy년 MM월 dd일 (EE)"/></span>
                                    <span>취리히(ZRH) 도착</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>2일차</span>
                        </div>
                        <div class="schedule__cont">
                            <div class="schedule__cont--tit">
                                <span>리기산</span>
                                <p>-알프스의 여왕 리기산 산악열차 탑승, 아름다운 알프스의 파노라마를 감상 후 하산</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/1_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/1_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/1_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>유럽에서 산악열차가 처음 만들어진 곳이며 루체른호(湖)에서의 보트를 비롯해 산악열차, 케이블카 등이 연결됩니다. 아름다운 경관으로 인해 ‘산들의 여왕’이라고 불립니다.</p>
                            </div>

                            <div class="schedule__cont--tit tit-second">
                                <span>체르마트</span>
                                <p>-마음까지 깨끗해지는 청정도시 체르마트</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/6_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/6_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/6_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>마테호른 기슭에 위치한 체르마트는 청정 도시로 유명하며, 마을에서는 마테호른 봉우리가 보이는데 일출과 일몰때 더욱 아름다운 풍경을 감상할수있습니다. 산으로 둘러쌓여 있어 맑은 공기는 물론 조용하기까지 한 이 도시는 일년 내내 눈으로 덮혀있어 스위스다운 스위스를 보여줍니다.</p>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>3일차</span>
                        </div>
                        <div class="schedule__cont">
                            <div class="schedule__cont--tit">
                                <span>칼트바트 미네랄 온천 & 스파</span>
                                <p>-간단한 하이킹 + "칼트바트 미네랄 온천 & 스파" + 알프스 뷰 감상하기</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/2_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/2_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/2_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>※ 개별 수영복 및 타월은 준비 바랍니다.<br>
                                    (래쉬가드 등 수영복 재질로 이루어진 복장 모두 가능하나 면티,면바지,레깅스 등 스포츠 의류 불가)<br>
                                    현지에서 대여할시 (수영복 20프랑, 타월(대형) 5프랑) 별도이며 금액 등은 변동될 수 있습니다.</p>
                            </div>

                            <div class="schedule__cont--tit tit-second">
                                <span>마테호른</span>
                                <p>-피라미드형태의 특이한 형태의 알프스산맥</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/7_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/7_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/7_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>스위스와 이태리 국경에 위치한 산으로 체르마트 마을 남쪽에 위치하고 있습니다.</p>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>4일차</span>
                        </div>
                        <div class="schedule__cont">
                            <div class="schedule__cont--tit">
                                <span>루체른 호수</span>
                                <p>-스위스 중부 루체른에 있는 호수</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/3_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/3_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/3_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>눈 덮인 알프스 산봉우리를 배경으로 요정 동화에나 나올 법한 작은 탑과 지붕 있는 중세의 나무 다리가 즐비한 루체른은 지구상에서 가장 로맨틱하고 우아한 도시 중의 하나입니다.</p>
                            </div>

                            <div class="schedule__cont--tit tit-second">
                                <span>라보 계단식 와이너리</span>
                                <p>-유네스코 세계유산으로 더욱 주목받는 라보지역</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/8_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/8_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/8_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>끝없이 이어진 포도밭과 바다같은 호수 파노라마와 유네스코 세계유산으로 더욱 주목받는 라보지역 계단식 와이너리를 감상합니다.</p>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>5일차</span>
                        </div>
                        <div class="schedule__cont">
                            <div class="schedule__cont--tit">
                                <span>카펠교 & 워터 타워</span>
                                <p>-스위스 루체른주 루체른 로이스강에 있는 다리</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/4_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/4_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/4_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>유럽에서 가장 오래되고 가장 긴 나무다리로 길이가 200m에 이른다. 우아한 형태로 루체른의 상징으로 불립니다.</p>
                            </div>

                            <div class="schedule__cont--tit tit-second">
                                <span>프라우뮌스터</span>
                                <p>-853년 독일 국왕 루이스에 의해 건립된 곳</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/9_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/9_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/9_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>로마네스크 양식과 아치형 복도의 아름다운 구조를 이루고있습니다.</p>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>6일차</span>
                        </div>
                        <div class="schedule__cont">
                            <div class="schedule__cont--tit">
                                <span>빈사의 사자상</span>
                                <p>-스위스 용병을 상징</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/5_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/5_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/5_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>빈사의 사자상은 스위스 루체른시에 있는 것으로 스위스 용병을 상징하는 것으로 가난하였던 스위스의 아픈 역사를 느끼게 하는 조각상입니다.</p>
                            </div>

                            <div class="schedule__cont--tit tit-second">
                                <span>몽트뢰</span>
                                <p>-레만 호수를 가장 아름답게 볼 수 있는 호반도시</p>
                            </div>
                            <div class="schedule__cont--img">
                                <img class="schedule__img" src="/img/product_detail/10_1.jpg">
                                <img class="schedule__img" src="/img/product_detail/10_2.jpg">
                                <img class="schedule__img" src="/img/product_detail/10_3.jpg">
                            </div>
                            <div class="schedule__cont--detail">
                                <p>헤밍웨이, 채플린, 바바라 헨드릭과 같은 대스타들이 살았던 그림같이 아름다운 작은 마을로 산책하기 좋은 마을입니다.</p>
                            </div>
                        </div>
                    </div>

                    <div class="nth-day">
                        <div class="schedule__tit">
                            <span>7일차</span>
                        </div>
                        <div class="schedule__cont air_schedule">
                            <div class="start_schedule">
                                <i class="fas fa-plane-departure"></i>
                                <div class="start_schedule-info">
                                    <span><fmt:formatDate value="${prdDto.loc_dpr_date}" pattern="HH:mm"/></span>
                                    <span><fmt:formatDate value="${prdDto.loc_dpr_date}" pattern="yyyy년 MM월 dd일 (EE)"/></span>
                                    <span>취리히(ZRH) 출발</span>
                                </div>
                            </div>

                            <div class="arrive_schedule">
                                <i class="fas fa-plane-arrival"></i>
                                <div class="arrive_schedule-info">
                                    <span><fmt:formatDate value="${prdDto.dom_fin_date}" pattern="HH:mm"/></span>
                                    <span><fmt:formatDate value="${prdDto.dom_fin_date}" pattern="yyyy년 MM월 dd일 (EE)"/></span>
                                    <span>인천(ICN) 도착</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="tab-3" class="cont_box">
                <div class="cont_box__tit">
                    <span>상품문의</span>
                </div>

                <div class="go-qna">
                    <div class="go-qna__con">
                        <p>궁금하신 사항을 문의해 주시면 친절하게 알려드리겠습니다.😊</p>
                        <span>질문과 답변은 비공개로 운영됩니다.</span>
                    </div>
                    <div class="go-qna__btn">
                        <a href="#">
                            <i class="fas fa-edit"></i>
                            <span>문의하기</span>
                        </a>
                    </div>
                </div>

            </div>

            <div id="tab-4" class="cont_box">
                <div class="cont_box__tit">
                    <span>약관정보</span>
                </div>

                <div class="conditions-info">
                    <div class="conditions-info__tit item-cell">
                        <span>특별약관</span>
                    </div>
                    <div class="conditions-info__con item-cell">
                        <p>★ 특별 약관에 의한 국외여행 배상기준 ★ <br>
                            ＊본 상품은 데포짓 상품(항공료 선지급)으로 예약금 입금 시점부터 취소 수수료가 발생하는 특약 상품입니다.<br>
                            또한 항공사 공지로 D-14일전 or 선발권이 진행 될 수 있으며 발권이후 취소시 항공 취소 수수료 100%+표준약관 수수료가 적용됩니다. (예외 적용되는 경우 없음)<br>

                            예약일 ~ 출발일 30일 전 취소:예약금 환불 불가<br>
                            출발일 29 ~ 20일 전 취소: 예약금 환불 불가+여행경비의 10% 배상<br>
                            출발일 19 ~ 10일 전 취소: 예약금 환불 불가+여행경비의 15% 배상<br>
                            출발일 09 ~ 08일 전 취소: 예약금 환불 불가여행경비의 20% 배상<br>
                            출발일 07 ~ 01일 전 취소: 여행경비의 30% 배상+발권이후 항공료 100%<br>
                            출발일 당일 취소 : 여행경비의 50% 배상+발권이후 항공료 100%
                        </p>
                    </div>
                </div>
            </div>

            <div id="tab-5" class="cont_box">
                <div class="cont_box__tit">
                    <span>해외안전정보</span>
                </div>
                <div class="gogo">
                    <div class="gogo__img">
                        <img src="/img/product/logo_prohibition.svg">
                    </div>
                    <div class="gogo__con">
                        <p>외교부는 해외에서 우리 국민에 대한 사건·사고 피해를 예방하고 우리 국민의 안전한 해외 거주·체류 및 방문을 도모하기 위해 2004년부터 ‘여행경보제도’(<a class="gogo__con-a" href="https://www.0404.go.kr" target="_blank">https://www.0404.go.kr</a>)를 운영해 오고 있습니다.</p>
                        <p>여행경보는 발령대상 국가(지역)의 위협수준에 따라 1~4단계로 구분되며, 상황에 따라서 상시적으로 변경이 되고 있기 때문에 출국 전 반드시 여행목적지(국가 또는 지역)의 여행경보를 확인 하시기 바랍니다.</p>
                        <a class="gogo__btn" href="https://www.0404.go.kr/dev/issue_current.mofa" target="_blank">해외안전정보확인<i class="fas fa-angle-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="detail-item__rev-wrap">
        <div class="detail-item__rev">
            <div class="rev__code">
                <span>코드</span>
                <span>${prdDto.prd_dtl_cd}</span>
            </div>
            <div class="rev__date">
                <span>일정</span>
                <span>${prdDto.trv_dtl_per}</span>
            </div>

            <form class="detail-item__form" action="/reserv/reserv" method="get">
                <input type="hidden" value="${prdDto.prd_dtl_cd}" name="prd_dtl_cd"/>
                <div class="detail-item__pay">
                    <div class="pay_adult pay-margin">
                        <div class="pay__info">
                            <span>성인</span>
                            <span><fmt:formatNumber value="${prdDto.adt_prc}" pattern="#,##0"/><em>원</em></span>
                        </div>
                        <div class="pay__combo-box" price="${prdDto.adt_prc}">
                            <button
                                    type="button"
                                    class="minus-button"
                                    aria-label="Decrease"
                            ><i class="fas fa-minus"></i></button>
                            <input readonly
                                   type="text"
                                   name="adt_cnt"
                                   class="quantity"
                                   min="0"
                                   max="10"
                                   value="1"
                            />
                            <button
                                    type="button"
                                    class="plus-button"
                                    aria-label="Increase"
                            ><i class="fas fa-plus"></i></button>
                        </div>
                    </div>

                    <div class="pay_child pay-margin">
                        <div class="pay__info">
                            <span>아동</span>
                            <span><fmt:formatNumber value="${prdDto.chd_prc}" pattern="#,##0"/><em>원</em></span>
                        </div>

                        <div class="pay__combo-box" price="${prdDto.chd_prc}">
                            <button
                                    type="button"
                                    class="minus-button"
                                    aria-label="Decrease"
                            ><i class="fas fa-minus"></i></button>
                            <input readonly
                                   type="text"
                                   name="chd_cnt"
                                   class="quantity"
                                   min="0"
                                   max="10"
                                   value="0"
                            />
                            <button
                                    type="button"
                                    class="plus-button"
                                    aria-label="Increase"
                            ><i class="fas fa-plus"></i></button>
                        </div>
                    </div>

                    <div class="pay_baby pay-margin">
                        <div class="pay__info">
                            <span>유아</span>
                            <span><fmt:formatNumber value="${prdDto.bb_prc}" pattern="#,##0"/><em>원</em></span>
                        </div>

                        <div class="pay__combo-box" price="${prdDto.bb_prc}">
                            <button
                                    type="button"
                                    class="minus-button"
                                    aria-label="Decrease"
                            ><i class="fas fa-minus"></i></button>
                            <input readonly
                                   type="text"
                                   name="bb_cnt"
                                   class="quantity"
                                   min="0"
                                   max="10"
                                   value="0"
                            />
                            <button
                                    type="button"
                                    class="plus-button"
                                    aria-label="Increase"
                            ><i class="fas fa-plus"></i></button>
                        </div>
                    </div>
                </div>

                <div class="detail-item__total-pay">
                    <span>최종 합계금액</span>
                    <div><input type="hidden" readonly name="total_price" value="${prdDto.adt_prc}"/></div>
                    <span id="total_price">${prdDto.adt_prc}</span><em>원</em>
                </div>
            </form>
            <div class="detail-item__rev-btn">
                <button class="rev-btn__btn" name="revBtn">예약하기</button>
            </div>
        </div>
    </div>
    <div id="login-confirm" class="login-confirm__modal">
        <div class="login-confirm__modal-content">
           <div class="login-confirm__container">
               <span class="login-confirm__close" title="Close Modal">x</span>
                <p class="login-confirm__comment">로그인 후 예약을 진행하시겠습니까?</p>
                <div class="clearfix">
                    <button type="button" class="login-confirm__btn login-confirm__btn--guest">비회원예약</button>
                    <button type="button" class="login-confirm__btn login-confirm__btn--login">로그인</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function totalPriceFormatter(total_price){
        return total_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g,',');
    }


    $(document).ready(function(){

        function presentTotalPrice(total_price){
            total_price = totalPriceFormatter(total_price);
            $('#total_price').text(total_price);
        }

        presentTotalPrice($('#total_price').text());

        // 예약 가능한 인원 수
        const revPerCnt = parseInt(${prdDto.max_stt_cnt-prdDto.pr_rsvt_cnt});

        // 현재 페이지 총 예약 인원 수
        let perTotCnt = (parseInt($('.quantity').eq('0').val())+parseInt($('.quantity').eq('1').val())+parseInt($('.quantity').eq('2').val()));

        if(perTotCnt < 0){
            perTotCnt = 0;
            return;
        }


        $('ul.detail-item__tab li').click(function(){
            const tab_id = $(this).attr('data-tab');

            $('ul.detail-item__tab li').removeClass('selected');
            $('.cont_box').removeClass('selected');

            $(this).addClass('selected');
            $("#"+tab_id).addClass('selected');
        })



        $('.plus-button').eq("0").on("click",function (){
            const quantity = $('.quantity').eq("0").val();

            if(parseInt(quantity)+1 > 10){
                $('.plus-button').eq("0").attr('disabled',true);
                $('.plus-button').eq("0").css("background-color","#f7f7f7");
                $('.plus-button > i').eq("0").css("display","none");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.minus-button').eq("0").attr('disabled',false);
                    $('.minus-button').eq("0").css("background-color","");
                    $('.minus-button > i').eq("0").css("display","");
                    $('.quantity').eq("0").val(parseInt(quantity)+1);
                    let adultPcr = parseInt($('.pay__combo-box').eq("0").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+adultPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt += 1;
                    presentTotalPrice(totalPrice);
                }
            }
        });

        $('.plus-button').eq("1").on("click",function (){
            const quantity = $('.quantity').eq("1").val();

            if(parseInt(quantity)+1 > 10){
                $('.plus-button').eq("1").attr('disabled',true);
                $('.plus-button').eq("1").css("background-color","#f7f7f7");
                $('.plus-button > i').eq("1").css("display","none");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.minus-button').eq("1").attr('disabled',false);
                    $('.minus-button').eq("1").css("background-color","");
                    $('.minus-button > i').eq("1").css("display","");
                    $('.quantity').eq("1").val(parseInt(quantity)+1);
                    let childPcr = parseInt($('.pay__combo-box').eq("1").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+childPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt +=1;
                    presentTotalPrice(totalPrice);
                }

            }
        });

        $('.plus-button').eq("2").on("click",function (){
            const quantity = $('.quantity').eq("2").val();
            if(parseInt(quantity)+1 > 10){
                $('.plus-button').eq("2").attr('disabled',true);
                $('.plus-button').eq("2").css("background-color","#f7f7f7");
                $('.plus-button > i').eq("2").css("display","none");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.minus-button').eq("2").attr('disabled',false);
                    $('.minus-button').eq("2").css("background-color","");
                    $('.minus-button > i').eq("2").css("display","");
                    $('.quantity').eq("2").val(parseInt(quantity)+1);
                    let babyPcr = parseInt($('.pay__combo-box').eq("2").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+babyPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt +=1;
                    presentTotalPrice(totalPrice);
                }

            }
        });

        $('.minus-button').eq("0").on("click",function (){
            const quantity = $('.quantity').eq("0").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                $('.minus-button').eq("0").attr('disabled',true);
                $('.minus-button').eq("0").css("background-color","#f7f7f7");
                $('.minus-button > i').eq("0").css("display","none");
            }else{
                $('.plus-button').eq("0").attr('disabled',false);
                $('.plus-button').eq("0").css("background-color","");
                $('.plus-button > i').eq("0").css("display","");
                $('.quantity').eq("0").val(parseInt(quantity)-1);
                let adultPcr = parseInt($('.pay__combo-box').eq("0").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-adultPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                    presentTotalPrice(totalPrice);
                }
            }
        });

        $('.minus-button').eq("1").on("click",function (){
            const quantity = $('.quantity').eq("1").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                $('.minus-button').eq("1").attr('disabled',true);
                $('.minus-button').eq("1").css("background-color","#f7f7f7");
                $('.minus-button > i').eq("1").css("display","none");
            }else{
                $('.plus-button').eq("1").attr('disabled',false);
                $('.plus-button').eq("1").css("background-color","");
                $('.plus-button > i').eq("1").css("display","");
                $('.quantity').eq("1").val(parseInt(quantity)-1);
                let childPcr = parseInt($('.pay__combo-box').eq("1").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-childPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                    presentTotalPrice(totalPrice);
                }
            }
        });

        $('.minus-button').eq("2").on("click",function (){
            const quantity = $('.quantity').eq("2").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                $('.minus-button').eq("2").attr('disabled',true);
                $('.minus-button').eq("2").css("background-color","#f7f7f7");
                $('.minus-button > i').eq("2").css("display","none");
            }else{
                $('.plus-button').eq("2").attr('disabled',false);
                $('.plus-button').eq("2").css("background-color","");
                $('.plus-button > i').eq("2").css("display","");
                $('.quantity').eq("2").val(parseInt(quantity)-1);
                let babyPcr = parseInt($('.pay__combo-box').eq("2").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-babyPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                    presentTotalPrice(totalPrice);
                }
            }
        });

        $('.rev-btn__btn').on("click",function (){
            const quantity = parseInt($('.quantity').eq("0").val());
            const userDto = "${sessionScope.userDto.usr_id}";
            if(quantity==0){
                alert("성인이 한명 꼭 포함 되어야 합니다. 확인 후 다시 예약을 진행해주세요");
            }else{
                if(userDto === "" || userDto == null){
                    document.getElementById('login-confirm').style.display='block';
                }else{
                    $("form").submit();
                }
            }
        });

        $('.login-confirm__close').on('click', function () {
            document.getElementById('login-confirm').style.display='none';
        });

        $('.login-confirm__btn--guest').on('click', function(){
            location.href = "<c:url value='/reserv/reserv'/>";
            $("form").submit();
            return;
        });

        $('.login-confirm__btn--login').on('click', function(){
            location.href = "<c:url value='/user/login'/>";
        })


    });
</script>
</body>
</html>