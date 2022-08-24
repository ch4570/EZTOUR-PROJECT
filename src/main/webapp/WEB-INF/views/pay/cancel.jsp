<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-22
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
    <div>
        <h1>환불 페이지</h1>
        <div>
            <div>
                <div>사진</div>
            </div>
            <div>
                <span>예약번호</span>
                <span>${param.rsvt_no}</span> |
                <span>예약일</span>
                <span>${cncViewDto.rsvt_date}</span>
            </div>
            <div>
                <p>${cncViewDto.prd_nm}</p>
                <span>${cncViewDto.prd_dtl_desc}</span>
            </div>
            <div>${cncViewDto.sum_prc}원</div>
        </div>
        <form>
            <div>
                <span>취소사유</span>
                <input type="text" name="reason" placeholder="취소사유를 입력해주세요">
                <input type="hidden" name="amount" value="${cncViewDto.pay_prc}">
                <input type="hidden" name="pay_no" value="${cncViewDto.pay_no}">
            </div>
            <div>
                <div>
                    <span>여행상품금액</span>
                    <span>${cncViewDto.sum_prc}원</span>
                </div>
                <div>
                    <span>사용한 마일리지</span>
                    <span>${cncViewDto.used_mlg}원</span>
                </div>
                <div>
                    <span>총 환불금액</span>
                    <span>${cncViewDto.pay_prc}원</span>
                </div>
            </div>
            <div>
                <button>취소</button>
                <button type="button" class="cncBtn">환불하기</button>
            </div>
        </form>
    </div>
    <script>
        $(document).ready(function(){
            $('.cncBtn').on("click", function(){
                let cancelViewDto = {
                    pay_no: '${cncViewDto.pay_no}',
                    pay_prc: '${cncViewDto.pay_prc}', // 환불금액
                    cnc_rsn: $('input[name="reason"]').val(), // 환불사유
                    rsvt_no: '${param.rsvt_no}',
                    new_pay_no : generateMId(20),
                    // refund_holder: "홍길동", // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
                    // refund_bank: "88" // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
                    // refund_account: "56211105948400" // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
                };
                jQuery.ajax({
                    url: "/pay/cnc",
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(cancelViewDto),
                    // dataType:"json",
                }).done(function(data) { // 환불 성공시 로직
                    let result = JSON.parse(data);
                    switch (result.status){
                        case 'CANCEL_FAILED':
                            alert("CANCEL_FAILED");
                            break;

                        case 'ACCESS_DENIED':
                            alert("ACCESS_DENIED");
                            break;

                        case 'SUCCESS':
                            alert('SUCCESS!!!');
                            break;
                    }
                    alert("환불 성공");
                    location.href = '<c:url value="/pay/cncConfirm"/>';
                }).fail(function(error) { // 환불 실패시 로직
                    alert("환불 실패");
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
        })
    </script>
</body>
</html>
