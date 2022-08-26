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
                    <li class="tab-link selected" data-tab="tab-1">여행일정</li>
                    <li class="tab-link" data-tab="tab-2">예약안내사항</li>
                    <li class="tab-link" data-tab="tab-3">상품문의</li>
                    <li class="tab-link" data-tab="tab-4">약관정보</li>
                    <li class="tab-link" data-tab="tab-5">해외안전정보</li>
                </ul>
            </div>

            <div id="tab-1" class="cont_box selected">
                <div class="cont_box__tit">
                    <span class="">여행일정</span>
                </div>

                <div class="whole-schedule">

                </div>
            </div>

            <div id="tab-2" class="cont_box">
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
                <span>${prdDto.trv_per}</span>
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
                                   type="number"
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
                                   type="number"
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
                                   type="number"
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
                    <div><input readonly type="number" name="total_price" value="${prdDto.adt_prc}"/>원</div>
                </div>
            </form>
            <div class="detail-item__rev-btn">
                <button class="rev-btn__btn" name="revBtn">예약하기</button>
            </div>
        </div>
    </div>

</div>

<script>
    $(document).ready(function(){

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
                alert("예약은 최대 10명까지 가능합니다!");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.quantity').eq("0").val(parseInt(quantity)+1);
                    let adultPcr = parseInt($('.pay__combo-box').eq("0").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+adultPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt += 1;
                }

            }

        });

        $('.plus-button').eq("1").on("click",function (){
            const quantity = $('.quantity').eq("1").val();
            if(parseInt(quantity)+1 > 10){
                alert("예약은 최대 10명까지 가능합니다!");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.quantity').eq("1").val(parseInt(quantity)+1);
                    let childPcr = parseInt($('.pay__combo-box').eq("1").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+childPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt +=1;
                }

            }
        });

        $('.plus-button').eq("2").on("click",function (){
            const quantity = $('.quantity').eq("2").val();
            if(parseInt(quantity)+1 > 10){
                alert("예약은 최대 10명까지 가능합니다!");
            }else{
                if((perTotCnt+1) > revPerCnt){
                    alert("최대 예약 가능 인원을 초과하였습니다.");
                }else{
                    $('.quantity').eq("2").val(parseInt(quantity)+1);
                    let babyPcr = parseInt($('.pay__combo-box').eq("2").attr('price'));
                    let totalPrice = parseInt($('input[name=total_price]').val());
                    totalPrice = totalPrice+babyPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt +=1;
                }

            }
        });

        $('.minus-button').eq("0").on("click",function (){
            const quantity = $('.quantity').eq("0").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                alert("예약인원은 0명보다 작을 수 없습니다.");
            }else{
                $('.quantity').eq("0").val(parseInt(quantity)-1);
                let adultPcr = parseInt($('.pay__combo-box').eq("0").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-adultPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                }
            }
        });

        $('.minus-button').eq("1").on("click",function (){
            const quantity = $('.quantity').eq("1").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                alert("예약인원은 0명보다 작을 수 없습니다.");
            }else{
                $('.quantity').eq("1").val(parseInt(quantity)-1);
                let childPcr = parseInt($('.pay__combo-box').eq("1").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-childPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                }
            }
        });

        $('.minus-button').eq("2").on("click",function (){
            const quantity = $('.quantity').eq("2").val();
            let totalPrice = parseInt($('input[name=total_price]').val());
            if(parseInt(quantity)-1 < 0){
                alert("예약인원은 0명보다 작을 수 없습니다.");
            }else{
                $('.quantity').eq("2").val(parseInt(quantity)-1);
                let babyPcr = parseInt($('.pay__combo-box').eq("2").attr('price'));
                if(totalPrice==0){
                    return;
                }else{
                    totalPrice = totalPrice-babyPcr;
                    $('input[name=total_price]').val(totalPrice);
                    perTotCnt -=1;
                }
            }
        });

        $('.rev-btn__btn').on("click",function (){
            const quantity = parseInt($('.quantity').eq("0").val());
            const userDto = "${sessionScope.userDto.usr_id}";
            if(quantity==0){
                alert("성인이 한명 꼭 포함 되어야 합니다. 확인 후 다시 예약을 진행해주세요");
                return;
            }else{
                if(userDto == "" || userDto == null){
                    alert("로그인 하셔야 예약 서비스 이용이 가능합니다.");
                    let question = confirm("로그인을 하시겠습니까?");
                    if(question){
                        location.href = "<c:url value='/user/login'/>";
                    }else{
                        location.href = "<c:url value='/reserv/reserv'/>";
                        $("form").submit();
                        return;
                    }
                }else{
                    $("form").submit();
                }
            }
        });

    });
</script>
</body>
</html>