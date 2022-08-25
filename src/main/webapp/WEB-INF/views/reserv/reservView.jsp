<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>예약내역</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv_confirm.css'/>">
</head>
<body>
<%--    ${payDto.pay_prc}--%>
    <div>
        <h1 class="dv_main_header">예약내역</h1>
        <div>
            <h2 class="rc_header">예약 상세 내역</h2>
            <div class="dv_dtl_box">
                <div class="dv_dtl_subbox">
                    <div class="dv_dtl_title">
                        [${rcid.prd_dtl_cd}]${rcid.prd_nm}
                    </div>
                    <div class="dv_arl_box">
                        <span class="dv_arl_code">${rcid.go_dpr_arl_id}</span>
                        <span>${rcid.go_dpr_tm}</span> ~
                        <span class="dv_arl_code">${rcid.cb_arr_arl_id}</span>
                        <span>${rcid.cb_arr_tm}</span>
                    </div>

                    <div class="dv_detail_box">
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 예약번호</dt>
                                <dd>${rcid.rsvt_no}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 예약일</dt>
                                <dd>${rcid.rsvt_date}</dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1"l>
                                <dt>- 여행기간</dt>
                                <dd>${rcid.trv_per}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 출발인원</dt>
                                <dd>총 ${rcid.adt_cnt + rcid.chd_cnt + rcid.bb_cnt}명</dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 상품금액</dt>
                                <dd>${rcid.sum_prc}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 입금액</dt>
                                <c:set var="pay" value="${payDto.pay_prc}"/>
                                <dd>${empty pay ? 0 : pay}원</dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 처리상태</dt>
                                <dd>${rcid.cmn_cd_rsvt_stt}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 결제예정금액</dt>
                                <dd>${rcid.pay_ftr_prc}원</dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <h2 class="rc_header">여행자 정보</h2>
            <div class="dv_dtl_box">
                <div class="rc_reserv_dtl_subbox">
                    <c:forEach var="trvlrInfo" items="${tid}" begin="0" end="${tid.size()}">
                        <div class="rc_trvlr_nm">${trvlrInfo.trvlr_nm}</div>
                        <div class="rc_trvlr_info_box">
                            <c:set var="trvlr_en_nm" value="${trvlrInfo.trvlr_en_nm}"/>
                            <div class="rc_trvlr_en_nm">- ${empty trvlr_en_nmm ? "영문명" : trvlr_en_nm}</div>
                            <div class="rc_trvlr_sub_box">
                                <span class="rc_trvlr_prc_title">- 상품가 </span>
                                <span class="rc_trvlr_prc">${trvlrInfo.pay_ftr_prc}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div>
            <h2 class="rc_header">상품 결제정보</h2>
            <div class="dv_dtl_box">
                <div>
                    <c:choose>
                        <c:when test="${empty payDto}">
                            결제 내역이 없습니다.
                        </c:when>
                        <c:otherwise>
                            <div>
                                <dl>
                                    <dt>여행상품번호</dt>
                                    <dd>${payDto.prd_dtl_cd}</dd>
                                </dl>
                                <dl>
                                    <dt>결제일</dt>
                                    <dd>${payDto.pay_date}</dd>
                                </dl>
                            </div>
                            <div>
                                <dl>
                                    <dt>결제상태</dt>
                                    <dd>${payDto.cmn_cd_pay_stt}</dd>
                                </dl>
                                <dl>
                                    <dt>결제금액</dt>
                                    <dd>${payDto.pay_prc}</dd>
                                    <dd>(사용한 마일리지 : ${payDto.used_mlg})</dd>
                                </dl>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
        <div>
            <c:set var="status" value="${rcid.cmn_cd_rsvt_stt}"/>
            <c:choose>
                <c:when test="${status == '6B'}">
                    <button type="button" class="payBtn">결제 하기</button>
                    <button class="reservList" type="button">예약 목록보기</button>
                </c:when>
                <c:when test="${status == '6A' || status == '6E'}">
                    <button type="button" class="cncBtn">${status == '6A' ? "예약접수" : "결제"} 취소</button>
                    <button class="reservList" type="button">예약 목록보기</button>
                </c:when>
                <c:otherwise>
                    <button class="reservList" type="button">예약 목록보기</button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
<script>
    $(document).ready(function () {
        $('.reservList').on("click", function(){
            location.href = '<c:url value="/reserv/list"/>';
        });

        $('.payBtn').on("click", function(){
            location.href = '<c:url value="/pay/pay?rsvt_no=${rcid.rsvt_no}&prd_dtl_cd=${rcid.prd_dtl_cd}"/> '
        });

        $('.cncBtn').on("click", function(){
            location.href = '<c:url value="/pay/cnc?rsvt_no=${rcid.rsvt_no}"/> '
        });
    });
</script>
</body>
</html>
