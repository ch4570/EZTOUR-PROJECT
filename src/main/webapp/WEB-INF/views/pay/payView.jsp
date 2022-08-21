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
</head>
<body>
<h1>결제페이지</h1>
<form>
    <div>
        <div>
            <input type="radio" name="pay_mthd" value="card" checked><p>신용카드</p>
        </div>
        <div>
            <div>
                <label>결제자명</label>
                <input type="text" name="usr_nm" value="${userDto.usr_nm}" readonly>
            </div>
            <div>
                <label>마일리지 사용</label><span>나의 마일리지 한도 내에서 입력해주세요</span>
                <input type="text" name="used_mlg" value="0">
                <p>나의 마일리지 : ${userDto.mlg}</p>
                <button type="button">사용</button>
            </div>
            <div>
                <label>여행상품금액</label>
                <input type="text" name="pay_ftr_prc" value="${pay_ftr_prc}" readonly>
            </div>
            <div>
                <label>이메일</label>
                <input type="text" name="email" value="${userDto.email}" readonly>
            </div>

            <div>
                <h3>총 결제금액</h3>
                <input type="text" name="pay_prc" value="${pay_ftr_prc}" readonly>
            </div>
        </div>
        <div>
            <h3>카드결제시 유의사항</h3>
            <p>- 결제내역이 다를 경우 담당자 또는 이지투어 고객센터로 연락주시기 바랍니다.</p>
            <p>- 무통장 입금의 입금자명과 금액을 반드시 확인하여 주시기 바랍니다.</p>
        </div>
        <div>
            <button type="button" class="cancelBtn">취소</button>
            <button type="button" class="payBtn">결제하기</button>
        </div>
    </div>
</form>
<script>
    $(document).ready(function(){


       $('.cancelBtn').on("click", function () {
           location.href = '<c:url value="/reserv/reservView?rsvt_no=${param.rsvt_no}&prd_dtl_cd=${param.prd_dtl_cd}"/>'
            });

       <%--$('#payBtn').on("click", function(){--%>
       <%--    //보낼 값들을 어떻게 보내지?--%>
           //payDto에는 pay_prc, used_mlg, rsvt_no, pay_mthd, usr_id, prd_dtl_cd
           //usrDto에는 usr_nm,email
           // let pay_prc =
           // let payDto = {pay_prc:};
           <%--let pay_prc = $('input[name="pay_prc"]').val();--%>
           <%--let used_mlg = $('input[name="used_mlg"]').val();--%>
           <%--used_mlg = used_mlg == "" ? 0 : used_mlg;--%>
           <%--let rsvt_no = '${param.rsvt_no}';--%>
           <%--let pay_mthd = $('input[name="pay_mthd"]').val();--%>
           <%--let prd_dtl_cd = '${param.prd_dtl_cd}';--%>
           <%--let usr_nm = '${userDto.usr_nm}';--%>
           <%--let email = '${userDto.email}';--%>
           <%--let pay_ftr_prc = '${pay_ftr_prc}';--%>
           <%--let payViewDto = {'pay_prc': pay_prc, 'used_mlg':used_mlg, 'rsvt_no':rsvt_no, 'pay_mthd':pay_mthd,--%>
           <%--    'prd_dtl_cd':prd_dtl_cd, 'usr_nm':usr_nm, 'email':email, 'pay_ftr_prc':pay_ftr_prc};--%>

       <%--    $.ajax({--%>
       <%--        type: 'POST',--%>
       <%--        url: '/pay/pay',--%>
       <%--        headers: {"content-type": "application/json"},--%>
       <%--        dataType: 'text',--%>
       <%--        data: JSON.stringify(payViewDto),--%>
       <%--        success : function(result){--%>
       <%--            // alert(result);--%>
       <%--            // alert("success!");--%>
       <%--            requestPay(result);--%>

       <%--        },--%>
       <%--        error : function(result){--%>
       <%--            // let parseResult = JSON.parse(result);--%>
       <%--            // alert(parseResult);--%>

       <%--            alert("error")--%>
       <%--        }--%>

       <%--    });--%>
       <%--    alert("the request is sent")--%>
       <%--});--%>


        $('.payBtn').on("click", function(){
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
