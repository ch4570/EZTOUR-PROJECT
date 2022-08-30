<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-17
  Time: 오후 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv.css'/>">
</head>
<body>
<div class="pv_big_box">
    <h1 class="pv_header">결제페이지</h1>
    <form>
        <div class="pv_sub_box">
            <div class="pv_pg_box">
                <button type="button" class="pv_pg pg_name" value="credit_card">신용카드</button>
                <button type="button" class="pv_pg pg_name" value="kakaopay">카카오페이</button>
                <button type="button" class="pv_pg pg_name" value="payco">페이코</button>
                <input type="hidden" name="prd_nm" value="${prd_nm}">
            </div>
            <div class="pv_dtl_box">
                <div class="pv_low">
                    <label class="pv_label">결제자명</label>
                    <input type="text" class="pv_large_inputbox" name="usr_nm" value="${userDto.usr_nm}" readonly>
                </div>
                <div class="pv_low">
                    <div class="pv_label">
                        <label>마일리지 사용</label>
<%--                        <span>나의 마일리지 한도 내에서 입력해주세요</span>--%>
                    </div>
                    <div class="pv_mlg_box">
                        <input type="text" name="used_mlg" class="pv_large_inputbox_white" value="0" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                        <button type="button" class="mlg_btn">사용</button>
                    </div>
                    <input type="hidden" name="realMlg" value="${userDto.mlg}">
                    <p class="pv_mlg_info">나의 마일리지 : <fmt:formatNumber value="${userDto.mlg}" type="number"/> (5,000마일리지부터 사용 가능합니다.)</p>
                </div>
                <div class="pv_low">
                    <label class="pv_label">여행상품금액</label>
<%--                    <input type="text" class="pv_large_inputbox" name="pay_ftr_prc" value="${pay_ftr_prc}" readonly>--%>
                    <input type="text" class="pv_large_inputbox" name="pay_ftr_prc" value="<fmt:formatNumber value="${pay_ftr_prc}" type="number"/>" readonly>
                </div>
                <div class="pv_low">
                    <label class="pv_label">이메일</label>
                    <input type="text" class="pv_large_inputbox" name="email" value="${userDto.email}" readonly>
                </div>

                <div class="pv_low pv_price_box reserv_top_border">
                    <h3 class="pv_price_label">총 결제금액</h3>
                    <div>
                        <div class="pay_prc pv_price"><fmt:formatNumber value="${pay_ftr_prc}" type="number"/></div>
                        <span class="pv_won">원</span>
                    </div>
                </div>
            </div>
            <div class="pv_dtl_box padding_btm">
                <span>카드결제시 유의사항</span>
                <p>- 결제내역이 다를 경우 담당자 또는 이지투어 고객센터로 연락주시기 바랍니다.</p>
                <p>- 무통장 입금의 입금자명과 금액을 반드시 확인하여 주시기 바랍니다.</p>
            </div>
            <div class="pv_dtl_box rc_btn_box">
                <button type="button" class="payBtn reserv_m_btn rc_btn_margin reserv_btn_m_black">결제하기</button>
                <button type="button" class="cancelBtn reserv_m_btn reserv_btn_m_gray">취소</button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function(){
        let pg_name = '';
       $('.cancelBtn').on("click", function () {
           location.href = '<c:url value="/reserv/reservView?rsvt_no=${param.rsvt_no}&prd_dtl_cd=${param.prd_dtl_cd}"/>'
            });

       $('.pg_name').on("click", function(){
           let pv_pgs = document.getElementsByClassName("pv_pg");
           if($(this).val()=='credit_card'){
               pg_name = 'html5_inicis.INIpayTest';
           } else if($(this).val()=='kakaopay'){
               pg_name = 'kakaopay.TC0ONETIME';
           } else {
               pg_name = 'payco.PARTNERTEST'
           }

           for(i = 0; i < pv_pgs.length; i++){
               pv_pgs[i].className = pv_pgs[i].className.replace(" pv_active", "");
           }

           $(this).addClass(" pv_active");
       });

       let checkMlg = function (){
           let used_mlg = parseInt($('input[name="used_mlg"]').val());
           let real_mlg = parseInt($('input[name="realMlg"]').val());
           let price = parseInt('${pay_ftr_prc}');

           if(used_mlg<5000 && used_mlg != 0){
               alert("5000마일리지부터 사용 가능합니다.");
               $('input[name="used_mlg"]').val(0);
               $('.pay_prc').html(price.toLocaleString());
               return false;
           }
           if(used_mlg > real_mlg){
               alert("사용가능한 마일리지를 확인해주세요");
               $('input[name="used_mlg"]').val(0);
               $('.pay_prc').html(price.toLocaleString());
               return false;
           }

           return true;
       }

       $('.mlg_btn').on("click", function(){
           let used_mlg_ref = $('input[name="used_mlg"]');
           if(checkMlg()){
               let price = parseInt(${pay_ftr_prc});
               let used_mlg = parseInt($('input[name="used_mlg"]').val());
               $('.pay_prc').html((price - used_mlg).toLocaleString());
               used_mlg_ref.addClass(" pv_background_blue");
               return;
           }

           used_mlg_ref.className = used_mlg_ref.removeClass("pv_background_blue");
       });

        $('.payBtn').on("click", function(){
            if(pg_name==''){
                alert('결제 수단을 선택해주세요');
                return;
            }

            if(!checkMlg()) return;

            let final_amount = (${pay_ftr_prc} - parseInt($('input[name="used_mlg"]').val()));

            var IMP = window.IMP;
            IMP.init("imp03490268");

            IMP.request_pay({
                pg: pg_name,
                pay_method: 'card',
                merchant_uid: generateMId(20),
                name: '[별빛이흐른다_ver1 ] 스위스일주7일 [체르마트숙박+마테호른일출+리기클래식열차/스파]',
                amount: final_amount,
                buyer_email: '${userDto.email}',
                buyer_name: '${userDto.usr_nm}',
            }, function (rsp) {
                if (rsp.success) {
                    let payDto = {pay_no: rsp.merchant_uid
                        , rsvt_no: '${param.rsvt_no}'
                        , prd_dtl_cd: '${param.prd_dtl_cd}'
                        , imp_uid: rsp.imp_uid
                        , used_mlg:$('input[name="used_mlg"]').val()
                        , pay_ftr_prc: '${pay_ftr_prc}'};
                    jQuery.ajax({
                        url: "/pay/saveResult",
                        method: "POST",
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(payDto)
                    }).done(function (data) {
                        let result = JSON.parse(data);
                        switch(result.status) {
                            case 'failed':
                                alert('위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.');
                                location.href = '<c:url value="/reserv/list"/>';
                                break;
                            case 'success':
                                alert('결제가 정상적으로 처리되었습니다');
                                location.href = '<c:url value="/pay/confirm?rsvt_no=${param.rsvt_no}&prd_dtl_cd=${param.prd_dtl_cd}"/>';
                                break;
                        }
                    })
                } else {
                    alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
                }
            });
        });

        function dec2hex (dec) {
            return dec.toString(16).padStart(2, "0")
        }

        function generateMId (len) {
            var arr = new Uint8Array((len || 40) / 2)
            window.crypto.getRandomValues(arr)
            return Array.from(arr, dec2hex).join('')
        }
    });
</script>
</body>
</html>
