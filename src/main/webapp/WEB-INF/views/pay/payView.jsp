<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-17
  Time: 오후 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv_confirm.css'/>">
</head>
<body>
<div class="pv_big_box">
    <h1 class="pv_header">결제페이지</h1>
    <form>
        <div>
            <div class="pv_mth_box">
                <input type="radio" name="pay_mthd" value="card" checked><p class="pay_mth_credit">신용카드</p>
            </div>
            <div class="pv_dtl_box">
                <div class="pv_low">
                    <label class="pv_label">결제자명</label>
                    <input type="text" class="pv_no_border" name="usr_nm" value="${userDto.usr_nm}" readonly>
                </div>
                <div class="pv_low">
                    <label class="pv_label">마일리지 사용</label>
                    <input type="text" name="used_mlg" value="0" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                    <input type="hidden" name="realMlg" value="${userDto.mlg}" >
                    <p>나의 마일리지 : ${userDto.mlg}</p>
                </div>
                <div class="pv_low">
                    <label class="pv_label">여행상품금액</label>
                    <input type="text" class="pv_no_border" name="pay_ftr_prc" value="${pay_ftr_prc}" readonly>
                </div>
                <div class="pv_low">
                    <label class="pv_label">이메일</label>
                    <input type="text" class="pv_no_border" name="email" value="${userDto.email}" readonly>
                </div>

                <div class="pv_low">
                    <h3 class="pv_label">총 결제금액</h3>
                    <input type="text" class="pv_no_border" name="pay_prc" value="${pay_ftr_prc}" readonly>
                </div>
            </div>
            <div class="pv_dtl_box padding_btm">
                <h3>카드결제시 유의사항</h3>
                <p>- 결제내역이 다를 경우 담당자 또는 이지투어 고객센터로 연락주시기 바랍니다.</p>
                <p>- 무통장 입금의 입금자명과 금액을 반드시 확인하여 주시기 바랍니다.</p>
            </div>
            <div class="pv_dtl_box rc_btn_box">
                <button type="button" class="cancelBtn rc_btn">취소</button>
                <button type="button" class="payBtn rc_btn">결제하기</button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function(){
       $('.cancelBtn').on("click", function () {
           location.href = '<c:url value="/reserv/reservView?rsvt_no=${param.rsvt_no}&prd_dtl_cd=${param.prd_dtl_cd}"/>'
            });

        $('.payBtn').on("click", function(){
            let used_mlg = parseInt($('input[name="used_mlg"]').val());
            let realMlg = parseInt($('input[name="realMlg"]').val());
            if(used_mlg>realMlg){
                alert("사용가능한 마일리지를 확인해주세요");
                return;
            }

            // IMP.request_pay(param, callback) 결제창 호출
            var IMP = window.IMP;
            IMP.init("imp03490268");

            IMP.request_pay({ // param
                pg: "kakaopay.TC0ONETIME",
                pay_method: $('input[name="pay_mthd"]').val(),
                merchant_uid: generateMId(20),
                name: '${param.rsvt_no}',
                amount: 100,
                buyer_email: '${userDto.email}',
                buyer_name: '${userDto.usr_nm}',
            }, function (rsp) { // callback
                if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
                    // jQuery로 HTTP 요청
                    let payDto = {pay_no: rsp.merchant_uid
                        , rsvt_no: '${param.rsvt_no}'
                        , prd_dtl_cd: '${param.prd_dtl_cd}'
                        , imp_uid: rsp.imp_uid
                        , used_mlg:$('input[name="used_mlg"]').val()
                        , pay_ftr_prc: '${pay_ftr_prc}'};
                    jQuery.ajax({
                        url: "/pay/saveResult", // 예: https://www.myservice.com/payments/complete
                        method: "POST",
                        contentType: "application/json; charset=utf-8",
                        // dataType:"json",
                        data: JSON.stringify(payDto)
                    }).done(function (data) {
                        let result = JSON.parse(data);
                        switch(result.status) {
                            case 'failed':
                                alert('위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.');

                                break;
                            case 'success':
                                alert('결제가 정상적으로 처리되었습니다');
                                location.href = '<c:url value="/pay/confirm?rsvt_no=${param.rsvt_no}&prd_dtl_cd=${param.prd_dtl_cd}"/>';
                                break;
                        }
                    })
                    alert(rsp.merchant_uid);
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
