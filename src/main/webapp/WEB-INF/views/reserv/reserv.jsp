<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-12
  Time: 오전 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv.css'/>">
</head>
<body>
<script>
    let msg = '${param.msg}';
    if(msg=='GST_ONLY1PRD') { alert('비회원 예약은 최대 한 개까지 가능합니다.'); }
    if(msg=='RSVT_FAILED') { alert('유효하지 않은 상품입니다. 5555-0000으로 예약 및 결제 문의바랍니다.'); }
    if(msg=='RSVT_WRONG') { alert('예약처리 중 문제가 발생했습니다. 다시 예약해주시기 바랍니다.'); }
</script>
    <div class="ri_biggest_box">
        <div class="ri_header_box">
            <h1 class="ri_header">상품 예약하기</h1>
            <p class="ri_header_sub">예약하시면 빠른 시간 내에 담당 직원이 연락드리겠습니다.</p><br>
        </div>
        <div class="ri_rsvt_info_box">
            <div class="ri_rsvt_info_img" style="background-image: url('${rid.img_pth}')"></div>
            <div class="ri_rsvt_info_subbox">
                <dl>
                    <dt>행사코드</dt>
                    <dd>${rid.prd_dtl_cd}</dd><!-- $ {rid.prdDtlCd}-->
                </dl>
                <div>${rid.prd_nm}</div><!--$ {rid.prdNm}-->
            </div>
        </div>
        <div class="ri_agree_box">
            <h2 class="ri_sub_header">약관동의</h2>
            <div>
                <label class="ri_agree_checkbox">
                    <input class="ri_agree_check" type="checkbox" name="agree">
                    <span>약관 전체 동의</span>
                </label>
                <span class="ri_agree_info">약관 전문을 모두 확인하셔야 예약이 완료됩니다.</span>
            </div>
            <div class="ri_agree_checklist_box">

            </div>
        </div>
        <div class="ri_reserv_info_box">
            <form id="form">
                <input type="hidden" name="prd_dtl_cd" value="${rid.prd_dtl_cd}">
                <input type="hidden" name="prd_cd" value="${rid.prd_cd}">
                <input type="hidden" name="dstn_cd" value="${rid.dstn_cd}">
                <input type="hidden" name="adt_prc" value="${rid.adt_prc}">
                <input type="hidden" name="chd_prc" value="${rid.chd_prc}">
                <input type="hidden" name="bb_prc" value="${rid.bb_prc}">
                <h2 class="ri_sub_header">예약정보</h2>
                <div class="ri_reserv_info_body">
                    <table class="ri_reserv_info_table">
<%--                        <colgroup>--%>
<%--                            <col>--%>
<%--                        </colgroup>--%>
                        <tr class="ri_table_row">
                            <th>상품명</th>
                            <td>${rid.prd_dtl_cd}</td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>이용항공</th>
                            <td>${rid.arl_nm}</td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>여행기간</th>
                            <td>${rid.trv_per}</td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>일정</th>
                            <td>
                                <dl class="ri_arl_line">
                                    <dt>한국 출발</dt>
    <%--                                <p><fmt:formatDate value="${rid.go_dpr_tm}" timeStyle="" />${rid.go_dpr_tm}</p> --%>
                                    <dd>${rid.dom_dpr_date}</dd>
                                    <dt>현지도착</dt>
                                    <dd>${rid.loc_fin_date}</dd>
                                </dl>
                                <dl class="ri_arl_line">
                                    <dt>한국 출발</dt>
                                    <dd>${rid.loc_dpr_date}</dd> ->
                                    <dt>현지도착</dt>
                                    <dd>${rid.dom_fin_date}</dd>
                                </dl>
                            </td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>성인요금</th>
    <%--                        <fmt:formatNumber value="${rid.adt_prc}" pattern="#,###"/>--%>
                            <td><fmt:formatNumber value="${rid.adt_prc}" type="number"/>원[만12세 이상](기본상품가:<fmt:formatNumber value="${rid.adt_prc}" type="number"/>원, 유류할증료:0원, 제세공과금0원)</td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>아동요금</th>
                            <td><fmt:formatNumber value="${rid.chd_prc}" type="number"/>원 [만 12세 미만](기본상품가:<fmt:formatNumber value="${rid.chd_prc}" type="number"/>원, 유류할증료: 0원,제세공과금 0원)</td>
                        </tr>
                        <tr class="ri_table_row">
                            <th>유아요금</th>
                            <td><fmt:formatNumber value="${rid.bb_prc}" type="number"/>원 [24개월 미만](기본상품가:<fmt:formatNumber value="${rid.bb_prc}" type="number"/>원,유류할증료:0원,제세공과금 0원)</td>
                        </tr>
                    </table>
                </div>
                <div class="ri_trvlr_box">
                    <h2 class="ri_sub_header">예약자 정보</h2>
                    <div>
                            <table class="ri_trvlr_table">
                                <tr class="ri_trvlr_row">
                                    <th class="ri_trvlr_col1">대표 예약자명</th>
                                    <td class="ri_trvlr_col2">
                                        <input type="text" class="ri_trvlr_inputbox" name="mn_rsvt_nm" value="${userDto.usr_nm}">
                                        <input type="radio" name="isUsrIncluded" value="y" checked><span>본인포함</span>
                                        <input type="radio" name="isUsrIncluded" value="n"><span>본인 비포함 (여행 동반자 정보는 담당자 통화 시 확인)</span>
                                    </td>
                                </tr>
                                <tr class="ri_trvlr_row">
                                    <th class="ri_trvlr_col1">휴대폰 번호</th>
                                    <td class="ri_trvlr_col2">
                                        <input type="text" class="ri_trvlr_inputbox" name="phn" placeholder="01012341234" value="${userDto.phn}" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                    </td>
                                </tr>
                                <tr class="ri_trvlr_row">
                                    <th class="ri_trvlr_col1">이메일</th>
                                    <td class="ri_trvlr_col2"><input type="text" class="ri_trvlr_inputbox" name="emailFirst" value="${emailFirst}"><span class="ri_at">@</span><input type="text" class="ri_trvlr_inputbox" name="emailLast" value="${emailLast}"></td>
                                </tr>
                            </table>
                    </div>
                </div>
                <div class="ri_trvlrinfo_box">
                    <h2 class="ri_sub_header">여행자정보</h2>
                    <div class="ri_trvlrinfo_subbox">
                        <dl class="ri_trvlrinfo_row">
                            <dt class="ri_trvlrinfo_col1">성인</dt>
                            <dd class="ri_trvlrinfo_col2">
                                <span>만 12세 이상</span>
                                <div>
                                    <button type="button" class="btn_minus">-</button>
                                    <input type="text" name="adt_cnt" value="${empty param.adt_cnt ? 0 : param.adt_cnt}" readonly oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                    <button type="button" class="btn_plus">+</button>
                                </div>
                                <span>${rid.adt_prc}원</span>
                            </dd>
                        </dl>
                        <dl class="ri_trvlrinfo_row">
                            <dt class="ri_trvlrinfo_col1">아동</dt>
                            <dd class="ri_trvlrinfo_col2">
                                <span>만 12세 미만</span>
                                <div>
                                    <button type="button" class="btn_minus">-</button>
                                    <input type="text" name="chd_cnt" pattern="[0-9]+" readonly value="${empty param.chd_cnt ? 0 : param.chd_cnt}" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                    <button type="button" class="btn_plus">+</button>
                                </div>
                                <span>${rid.chd_prc}원</span>
                            </dd>
                        </dl>
                        <dl class="ri_trvlrinfo_row">
                            <dt class="ri_trvlrinfo_col1">유아</dt>
                            <dd class="ri_trvlrinfo_col2">
                                <span>만 2세 이상</span>
                                <div>
                                    <button type="button" class="btn_minus">-</button>
                                    <input type="text" name="bb_cnt" pattern="[0-9]+" readonly value="${empty param.bb_cnt ? 0 : param.bb_cnt}" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                    <button type="button" class="btn_plus">+</button>
                                </div>
                                <span>${rid.bb_prc}원</span>
                            </dd>
                        </dl>
                        <dl class="ri_trvlrinfo_row">
                            <p class="ri_trvlrinfo_col1">최종 합계금액</p>
                            <div class="ri_trvlrinfo-totalbox">
                            <input type="text" name="sum_prc" class="sum_prc ri_trvlrinto_total" readonly value="0">원
                            </div>
                        </dl>
                    </div>
                </div>
                <div class="ri_etc">
                    <h2 class="ri_sub_header">기타 요청사항</h2>
                    <div>
                        <textarea name="req_cont" class="ri_etc_text" placeholder="1000자 이내로 입력해 주시기 바랍니다."></textarea>
                    </div>
                </div>
                <div class="ri_btn_box">
                    <input type="button" value="취소하기">
                    <input type="button" class="submit" value="예약하기">
                </div>
            </form>
        </div>
    </div>
    <script>
        $(document).ready(function(){
            $('.btn_minus').each(function(){
               if($(this).next().val()==0){
                   $(this).prop('disabled', true);
               }
            });

            let totalFee = function(){
                let adtCntRef = $('input[name="adt_cnt"]').val();
                let chdCntRef = $('input[name="chd_cnt"]').val();
                let bbCntRef = $('input[name="bb_cnt"]').val();

                let adt_cnt = parseInt(adtCntRef === '' ? 0 : adtCntRef);
                let chd_cnt = parseInt(chdCntRef === '' ? 0 : chdCntRef);
                let bb_cnt = parseInt(bbCntRef === '' ? 0 : bbCntRef);

                let sum_prc = adt_cnt * ${rid.adt_prc} + chd_cnt * ${rid.chd_prc} + bb_cnt * ${rid.bb_prc};
                $('.sum_prc').val(sum_prc);
            };

            totalFee();

            $('.submit').on('click', function(){
                let name = $('input[name="mn_rsvt_nm"]').val();
                let phn = $('input[name="phn"]').val();
                let emailFirst = $('input[name="emailFirst"]').val();
                let emailLast = $('input[name="emailLast"]').val();
                let agree = $('input[name="agree"]').val();

                if(!$('input[name="agree"]').is(':checked')){
                    alert('약관을 동의해주세요.');
                    return;
                }

                if(name==='') {
                    alert('성함 입력바랍니다.');
                    return;
                }
                if(phn==='') {
                    alert('휴대전화번호 입력바랍니다.');
                    return;
                }
                if(phn.length!=11){
                    alert('휴대전화번호를 확인해주세요');
                    return;
                }
                if(emailFirst===''||emailLast==='') {
                    alert('이메일 입력바랍니다.');
                    return;
                }

                let adt_cnt = parseInt($('input[name="adt_cnt"]').val());
                if($('input[name="adt_cnt"]').val() === '' || adt_cnt <= 0){
                    alert("예약은 성인이 필수로 들어가야 합니다.");
                    return;
                }


                let form = $('#form');
                form.attr("action", '<c:url value="/reserv/reserv"/>');
                form.attr("method", "post");
                form.submit();
            });


            $('.btn_plus').on('click', function(){
                let btn_plus = $(this);
                let valueRef = btn_plus.prev().val();

                //0명이면 minus버튼 disable처리
                if(valueRef==0){
                    btn_plus.siblings('button').prop('disabled', false);
                }

                //plus버튼 누르면 값 +1
                let val = 0;
                if(valueRef !== ''){
                    val = parseInt(valueRef)+1;
                } else {
                    val = 1;
                }
                //값 초기화
                btn_plus.prev().val(val);
                //total값 수정
                totalFee();
            });

            $('.btn_minus').on('click', function(){
                let btn_minus = $(this);
                let valueRef = btn_minus.next().val()

                let val = parseInt(valueRef)-1;
                btn_minus.next().val(val);

                if(valueRef==='1') {
                    btn_minus.prop('disabled', true);
                }
                totalFee();
            });


        })
        //최대인원 체크
    </script>
</body>
</html>
