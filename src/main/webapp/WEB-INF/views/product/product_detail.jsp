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
        <h1>프랑프랑프랑프랑스</h1>
    </div>

    <div class="detail-item__info">

        <div class="detail-item__img">
            <img src="/img/greece.jpg">
        </div>

        <div class="detail-item__view">
            <div class="detail-item__view--book">
                <span>예약현황</span>
                <span>예약 <em></em>명</span>
                <span>(남은 좌석 <em></em>석 / 최소 출발인원 <em></em>명)</span>
            </div>
            <div class="detail-item__view--rvw">
                <a>여행후기</a>
            </div>
        </div>

        <div class="detail-item__price-info">
            <div class="price-info__tit">
                <span>구분</span>
                <span>상품가격</span>
            </div>
            <div class="price-info__age1">
                <span>성인(만 12세 이상)</span>
                <span>0원</span>
            </div>
            <div class="price-info__age2">
                <span>아동(만 12세 미만)</span>
                <span>0원</span>
            </div>
            <div class="price-info__age3">
                <span>유아(24개월 미만)</span>
                <span>0원</span>
            </div>
        </div>
        <span class="price-info__not">- 유류할증료, 제세공과금은 유가와 환율에 따라 변동될 수 있습니다.</span>

        <div class="detail-item__air-info">
            <div class="air-info__air">
                <span>이용교통</span>
                <span>대한항공</span>
            </div>
            <div class="detail-item__info-box">
                <span>한국출발</span>
                <span>현지출발</span>
            </div>
            <div class="detail-item__info-box">
                <span>현지도착</span>
                <span>한국도착</span>
            </div>
        </div>


        <%--    Tap--%>
        <div class="detail-item__menu">
            <ul class="detail-item__tab">
                <li class="tab-link selected" data-tab="tab-1">여행일정</li>
                <li class="tab-link" data-tab="tab-2">예약안내사항</li>
                <li class="tab-link" data-tab="tab-3">상품문의</li>
                <li class="tab-link" data-tab="tab-4">약관정보</li>
                <li class="tab-link" data-tab="tab-5">해외안전정보</li>
            </ul>

            <div id="tab-1" class="cont_box selected">
                <div class="cont_box__tit">
                    <span>여행일정</span>
                </div>
            </div>

            <div id="tab-2" class="cont_box">
                <div class="cont_box__tit">
                    <span>예약안내사항</span>
                </div>
            </div>

            <div id="tab-3" class="cont_box">
                <div class="cont_box__tit">
                    <span>상품문의</span>
                </div>
            </div>

            <div id="tab-4" class="cont_box">
                <div class="cont_box__tit">
                    <span>약관정보</span>
                </div>
            </div>

            <div id="tab-5" class="cont_box">
                <div class="cont_box__tit">
                    <span>해외안전정보</span>
                </div>
            </div>

        </div>

    </div>

    <div class="detail-item__rev">
        <div class="rev__code">
            <span>코드</span>
            <span>44</span>
        </div>
        <div class="rev__date">
            <span>일정</span>
            <span>1박2일</span>
        </div>

        <form class="detail-item__form">
        <div class="detail-item__pay">
            <div pay_adult>
                <div class="pay__info">
                    <span>성인</span>
                    <span>1</span>
                </div>
                <div class="pay__combo-box" price="3200000">
                    <button
                            type="button"
                            class="minus-button"
                            aria-label="Decrease"
                    ><i class="fas fa-minus"></i></button>
                    <input readonly
                           type="number"
                           name="adt_quantity"
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

            <div pay_child>
                <div class="pay__info">
                    <span>아동</span>
                    <span>1</span>
                </div>

                <div class="pay__combo-box" price="3200000">
                    <button
                            type="button"
                            class="minus-button"
                            aria-label="Decrease"
                    ><i class="fas fa-minus"></i></button>
                    <input readonly
                           type="number"
                           name="chd_quantity"
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

            <div pay_baby>
                <div class="pay__info">
                    <span>유아</span>
                    <span>1</span>
                </div>

                <div class="pay__combo-box" price="1200000">
                    <button
                            type="button"
                            class="minus-button"
                            aria-label="Decrease"
                    ><i class="fas fa-minus"></i></button>
                    <input readonly
                           type="number"
                           name="bb_quantity"
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
            <div><input readonly type="number" name="total_price" value="3200000"/>원</div>
        </div>

        <div class="detail-item__rev-btn">
            <button class="rev-btn__btn" name="revBtn">예약하기</button>
        </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function(){

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
                $('.quantity').eq("0").val(parseInt(quantity)+1);
                let adultPcr = parseInt($('.pay__combo-box').eq("0").attr('price'));
                let totalPrice = parseInt($('input[name=total_price]').val());
                totalPrice = totalPrice+adultPcr;
                $('input[name=total_price]').val(totalPrice);

            }

        });

        $('.plus-button').eq("1").on("click",function (){
            const quantity = $('.quantity').eq("1").val();
            if(parseInt(quantity)+1 > 10){
                alert("예약은 최대 10명까지 가능합니다!");
            }else{
                $('.quantity').eq("1").val(parseInt(quantity)+1);
                let childPcr = parseInt($('.pay__combo-box').eq("1").attr('price'));
                let totalPrice = parseInt($('input[name=total_price]').val());
                totalPrice = totalPrice+childPcr;
                $('input[name=total_price]').val(totalPrice);
            }
        });

        $('.plus-button').eq("2").on("click",function (){
            const quantity = $('.quantity').eq("2").val();
            if(parseInt(quantity)+1 > 10){
                alert("예약은 최대 10명까지 가능합니다!");
            }else{
                $('.quantity').eq("2").val(parseInt(quantity)+1);
                let babyPcr = parseInt($('.pay__combo-box').eq("2").attr('price'));
                let totalPrice = parseInt($('input[name=total_price]').val());
                totalPrice = totalPrice+babyPcr;
                $('input[name=total_price]').val(totalPrice);
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
                }
            }
        });

    });
</script>
</body>
</html>
