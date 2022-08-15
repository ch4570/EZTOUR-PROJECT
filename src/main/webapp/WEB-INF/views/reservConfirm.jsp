<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-12
  Time: 오후 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div>
        <h1>예약완료</h1>
        <div>
            <p>상품예약이 정상적으로 완료되었습니다.</p>
            <p>이지투어 상품을 예약해 주셔서 감사합니다.</p>
            <p>담당자 확인 후 해피콜 시 예약 승인여부를 알려드립니다.</p>
            <p>확정 불가 시 즉시 결제 내역은 자동취소처리 됩니다.</p>
        </div>
    </div>
    <div>
        <h2>예약상세 내역</h2>
        <div>
            <p>${rcid.prd_nm}</p>
            <div>
                <span>${rcid.go_dpr_arl_id}</span>
                <span>${rcid.go_dpr_tm}</span> ~
                <span>${rcid.cb_arr_arl_id}</span>
                <span>${rcid.cb_arr_tm}</span>
            </div>
            <div>
                <div>
                    <dl>
                        <dt>예약일</dt>
                        <dd>${rcid.rsvt_date}</dd>
                    </dl>
                    <dl>
                        <dt>예약일</dt>
                        <dd>${rcid.rsvt_no}</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>상품번호</dt>
                        <dd>${rcid.prd_cd}</dd>
                    </dl>
                    <dl>
                        <dt>행사번호</dt>
                        <dd>${rcid.prd_dtl_cd}</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>여행기간</dt>
                        <dd>${rcid.trv_per}</dd>
                    </dl>
                    <dl>
                        <dt>출발인원</dt>
                        <dd>총 ${rcid.adt_cnt + rcid.chd_cnt + rcid.bb_cnt}명</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>상품금액</dt>
                        <dd>${rcid.sum_prc}</dd>
                    </dl>
                    <dl>
                        <dt>처리상태</dt>
                        <dd>${rcid.cmn_cd_rsvt_stt}</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
    <div>
        <h2>여행자 정보</h2>
        <div>
            <c:forEach var="trvlrInfo" items="${tid}" begin="0" end="${tid.size()}">
                <div>${trvlrInfo.trvlr_nm}</div>
                <div>
                    <c:set var="trvlr_en_nm" value="${trvlrInfo.trvlr_en_nm}"/>
                    <span>- ${empty trvlr_en_nmm ? "영문명" : trvlr_en_nm}</span>
                    <span>- 상품가 </span>
                    <span>${trvlrInfo.pay_ftr_prc}</span>
                </div>
            </c:forEach>
            <div></div>
        </div>
    </div>
    <div>
        <button type="button" class="home">메인으로</button>
        <button type="button" id="rsvtCheck">예약/결제 조회</button>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('.home').on("click", function(){
            location.href = '<c:url value="/"/>'
        });

        $('#rsvtCheck').on("click", function(){
            location.href = '<c:url value="/reserv/list"/>';
        });
    })
</script>
</body>
</html>
