<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>예약내역</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value='/css/reserv/reserv.css'/>">
</head>
<script>
    let msg = '${msg}';
    if(msg==='INVALID_ACCESS') {
        alert('이미 결제완료 되었거나 취소된 상품입니다.');
    } else if (msg==='CNC_FAILED') {
        alert('이미 취소되었거나 유효하지 않은 접근입니다. 대표번호 555-5555로 문의바랍니다.');
    }
</script>
<body>
    <div class="dv_big_box">
        <h1 class="reserv_main_header">예약내역</h1>
        <div>
            <h2 class="rc_header">예약 상세 내역</h2>
            <div class="dv_dtl_box">
                <div class="dv_dtl_subbox">
                    <div class="dv_dtl_title">
                        [${rcid.prd_dtl_cd}]${rcid.prd_nm}
                    </div>
                    <div class="dv_arl_box">
                        <span class="dv_arl_code">${rcid.arl_nm}</span>
                        <span><fmt:formatDate value="${rcid.dom_dpr_date}" pattern="yyyy년 MM월 dd일 hh:mm"/></span> ~
                        <span class="dv_arl_code">${rcid.arl_nm}</span>
                        <span><fmt:formatDate value="${rcid.dom_fin_date}" pattern="yyyy년 MM월 dd일 hh:mm"/></span>
                    </div>

                    <div class="dv_detail_box">
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 예약번호</dt>
                                <dd>${rcid.rsvt_no}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 예약일</dt>
                                <dd><fmt:formatDate value="${rcid.rsvt_date}" pattern="yyyy/MM/dd(E)"/></dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1"l>
                                <dt>- 여행기간</dt>
                                <dd>${rcid.trv_dtl_per}</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 출발인원</dt>
                                <dd>총 ${rcid.adt_cnt + rcid.chd_cnt + rcid.bb_cnt}명</dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 상품금액</dt>
                                <dd><fmt:formatNumber value="${rcid.sum_prc}" type="number"/>원</dd>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 입금액</dt>
                                <c:set var="pay" value="${payDto.pay_prc}"/>
                                <dd><fmt:formatNumber value="${empty pay ? 0 : pay}" type="number"/>원</dd>
                            </dl>
                        </div>
                        <div class="dv_low">
                            <dl class="dv_col1">
                                <dt>- 처리상태</dt>
                                <c:choose>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6A'}">
                                        <dd>예약접수</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6B'}">
                                        <dd>예약승인</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6C'}">
                                        <dd>예약반려</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6D'}">
                                        <dd>예약취소</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6E'}">
                                        <dd>예약완료</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6F'}">
                                        <dd>예약불가</dd>
                                    </c:when>
                                    <c:when test="${rcid.cmn_cd_rsvt_stt eq '6G'}">
                                        <dd>예약기타상태</dd>
                                    </c:when>
                                </c:choose>
                            </dl>
                            <dl class="dv_col2">
                                <dt>- 결제예정금액</dt>
                                <dd>
                                    <fmt:formatNumber value="${rcid.pay_ftr_prc}" type="number"/>원</dd>
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
                    <c:choose>
                        <c:when test="${empty tid}">
                            <div class="reserv_text_center">여행자 정보가 없습니다.</div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="trvlrInfo" items="${tid}" begin="0" end="${tid.size()}">
                                <div class="rc_trvlr_box">
                                    <div class="rc_trvlr_nm">${trvlrInfo.trvlr_nm}</div>
                                    <div class="rc_trvlr_info_box">
                                        <c:set var="trvlr_en_nm" value="${trvlrInfo.trvlr_en_nm}"/>
                                        <div class="rc_trvlr_en_nm">- ${empty trvlr_en_nmm ? "영문명" : trvlr_en_nm}</div>
                                        <div class="rc_trvlr_sub_box">
                                            <span class="rc_trvlr_prc_title">- 상품가 </span>
                                            <span class="rc_trvlr_prc"><fmt:formatNumber value="${trvlrInfo.pay_ftr_prc}" type="number"/>원</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div>
            <h2 class="rc_header">상품 결제정보</h2>
            <div class="dv_dtl_box">
                <div>
                    <c:choose>
                        <c:when test="${empty payDto}">
                            <div class="reserv_text_center">결제 내역이 없습니다.</div>
                        </c:when>
                        <c:otherwise>
                            <div class="dv_low">
                                <dl class="dv_col1">
                                    <dt>- 결제일</dt>
                                    <dd><fmt:formatDate value="${payDto.pay_date}" pattern="yyy/MM/dd일(E)"/></dd>
                                </dl>
                                <dl class="dv_col2">
                                    <dt>- 결제상태</dt>
                                    <c:choose>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7A'}">
                                            <dd>결제대기</dd>
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7B'}">
                                            <dd>결제취소</dd>
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7C'}">
                                            <dd>결제완료</dd>
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7D'}">
                                            <dd>결제실패</dd>
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7E'}">
                                            <dd>결제준비중</dd>
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7F'}">
                                            <dd>결제오류</dd><!--결제위조시도(금액)-->
                                        </c:when>
                                        <c:when test="${payDto.cmn_cd_pay_stt eq '7G'}">
                                            <dd>결제오류</dd><!--결제위조시도(마일리지)-->
                                        </c:when>
                                    </c:choose>
                                </dl>
                            </div>
                            <div class="dv_low">
                                <dl class="dv_col1">
                                    <c:choose>
                                        <c:when test="${payDto.cmn_cd_pay_stt == '7B'}">
                                            <dt>- 환불금액</dt>
                                        </c:when>
                                        <c:otherwise>
                                            <dt>- 결제금액</dt>
                                        </c:otherwise>
                                    </c:choose>
                                    <dd><fmt:formatNumber value="${empty payDto.pay_prc ? '0' : payDto.pay_prc}" type="number"/>원</dd>
                                </dl>
                                <dl class="dv_col2">
                                    <dt>- 사용한 마일리지</dt>
                                    <dd><fmt:formatNumber value="${payDto.used_mlg}" type="number"/></dd>
                                </dl>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
        <div class="rc_btn_box">
            <c:set var="status" value="${rcid.cmn_cd_rsvt_stt}"/>
            <c:choose>
                <c:when test="${status == '6B'}">
                    <button type="button" class="payBtn rc_btn_m rc_btn_margin reserv_btn_m_black">결제 하기</button>
                    <button class="reservList rc_btn_m reserv_btn_m_white" type="button">예약 목록보기</button>
                </c:when>
                <c:when test="${status == '6A' || status == '6E'}">
                    <button type="button" class="cncBtn rc_btn_m rc_btn_margin reserv_btn_m_white">${status == '6A' ? "예약접수" : "결제"} 취소</button>
                    <button class="reservList rc_btn_m reserv_btn_m_black" type="button">예약 목록보기</button>
                </c:when>
                <c:otherwise>
                    <button class="reservList reserv_btn_large reserv_btn_m_black" type="button">예약 목록보기</button>
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
