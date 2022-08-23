<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-12
  Time: 오전 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h1>상품 예약하기</h1>
<p>예약하시면 빠른 시간 내에 담당 직원이 연락드리겠습니다.</p><br>
<div>
    <dl>
        <dt>행사코드</dt>
        <dd>${rid.prd_dtl_cd}</dd><!-- $ {rid.prdDtlCd}-->
    </dl>
    <div>${rid.prd_nm}</div><!--$ {rid.prdNm}-->
</div>
<div>
    약관동의
</div>
<div>
    <form action="<c:url value="/reserv/reserv"/> " method="post">
        <input type="hidden" name="prd_dtl_cd" value="${rid.prd_dtl_cd}">
        <input type="hidden" name="prd_cd" value="${rid.prd_cd}">
        <input type="hidden" name="dstn_cd" value="${rid.dstn_cd}">
        <input type="hidden" name="adt_prc" value="${rid.adt_prc}">
        <input type="hidden" name="chd_prc" value="${rid.chd_prc}">
        <input type="hidden" name="bb_prc" value="${rid.bb_prc}">
        <h2>예약정보</h2>
        <div>
            <table>
                <colgroup>
                    <col>
                </colgroup>
                <tr>
                    <th>상품명</th>
                    <td>${rid.prd_dtl_cd}</td>
                </tr>
                <tr>
                    <th>이용항공</th>
                    <td>${rid.arl_nm}</td>
                </tr>
                <tr>
                    <th>여행기간</th>
                    <td>${rid.trv_per}</td>
                </tr>
                <tr>
                    <th>일정</th>
                    <td>
                        <div>
                            <p>한국 출발</p>
                            <p>${rid.go_dpr_tm}</p> ->
                            <p>현지도착</p>
                            <p>${rid.go_arr_tm}</p>
                        </div>
                        <div>
                            <p>한국 출발</p>
                            <p>${rid.cb_dpr_tm}</p> ->
                            <p>현지도착</p>
                            <p>${rid.cb_arr_tm}</p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>성인요금</th>
                    <td>${rid.adt_prc}원[만12세 이상](기본상품가:${rid.adt_prc}원, 유류할증료:0원, 제세공과금0원)</td>
                </tr>
                <tr>
                    <th>아동요금</th>
                    <td>${rid.chd_prc}원 [만 12세 미만](기본상품가:${rid.chd_prc}원, 유류할증료: 0원,제세공과금 0원)</td>
                </tr>
                <tr>
                    <th>유아요금</th>
                    <td>${rid.bb_prc}원 [24개월 미만](기본상품가:${rid.bb_prc}원,유류할증료:0원,제세공과금 0원)</td>
                </tr>
            </table>
        </div>
        <div>
            <h2>예약자 정보</h2>
            <div>
                    <table>
                        <colgroup><col></colgroup>
                        <tr>
                            <th>대표 예약자명</th>
                            <input type="radio" name="isUsrIncluded" value="y" checked><p>본인포함</p>
                            <input type="radio" name="isUsrIncluded" value="n"><p>본인 비포함 (여행 동반자 정보는 담당자 통화 시 확인)</p>
                            <td><input type="text" name="mn_rsvt_nm" value="${userDto.usr_nm}"></td>
                        </tr>
                        <tr>
                            <th>휴대폰 번호</th>
                            <td><input type="text" name="phn" placeholder="01012341234" value="" ></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td><input type="text" name="emailFirst" value="${emailFirst}">@<input type="text" name="emailLast" value="${emailLast}"></td>
                        </tr>
                    </table>
            </div>
        </div>
        <div>
            <h2>여행자정보</h2>
            <div>
                <dl>
                    <dt>성인</dt>
                    <dd>
                        <p>만 12세 이상</p>
                        <button type="button" class="btn_minus">-</button>
                        <input type="text" name="adt_cnt" value="${param.adt_cnt}">
                        <button type="button" class="btn_plus">+</button>
                        <p>${rid.adt_prc}원</p>
                    </dd>
                </dl>
                <dl>
                    <dt>아동</dt>
                    <dd>
                        <p>만 12세 미만</p>
                        <button type="button" class="btn_minus">-</button>
                        <input type="text" name="chd_cnt" value="${param.chd_cnt}">
                        <button type="button" class="btn_plus">+</button>
                        <p>${rid.chd_prc}원</p>
                    </dd>
                </dl>
                <dl>
                    <dt>유아</dt>
                    <dd>
                        <p>만 2세 이상</p>
                        <button type="button" class="btn_minus">-</button>
                        <input type="text" name="bb_cnt" value="${param.bb_cnt}" >
                        <button type="button" class="btn_plus">+</button>
                        <p>${rid.bb_prc}원</p>
                    </dd>
                </dl>
                <dl>
                    <p>최종 합계금액</p>
                    <input type="text" name="sum_prc" class="sum_prc" value="0">원
                </dl>
            </div>
        </div>
        <div>
            <h2>기타 요청사항</h2>
            <div>
                <textarea name="req_cont" placeholder="1000자 이내로 입력해 주시기 바랍니다."></textarea>
            </div>
        </div>
        <input type="button" value="취소하기">
        <input type="submit" value="예약하기">
    </form>
</div>
<script>
    $(document).ready(function(){
        $('.btn_minus').each(function(){
           if($(this).next().val()==0){
               $(this).prop('disabled', true);
           }
        });

        let totalFee = function(){
            let adt_cnt = parseInt($('input[name="adt_cnt"]').val());
            let chd_cnt = parseInt($('input[name="chd_cnt"]').val());
            let bb_cnt = parseInt($('input[name="bb_cnt"]').val());

            let sum_prc = adt_cnt * ${rid.adt_prc} + chd_cnt * ${rid.chd_prc} + bb_cnt * ${rid.bb_prc};
            $('.sum_prc').val(sum_prc);
        };

        totalFee();

        $('.btn_plus').on('click', function(){
            let btn_plus = $(this);
            if(btn_plus.prev().val()==0){
                btn_plus.siblings('button').prop('disabled', false);
            }
            let val = parseInt(btn_plus.prev().val())+1;
            btn_plus.prev().val(val);
            totalFee();
        });

        $('.btn_minus').on('click', function(){
            let btn_minus = $(this);
            let val = parseInt(btn_minus.next().val())-1;
            btn_minus.next().val(val);
            if(btn_minus.next().val()==0) {
                btn_minus.prop('disabled', true);
            }
            totalFee();
        });
    })
    //최대인원 체크
</script>
</body>
</html>
