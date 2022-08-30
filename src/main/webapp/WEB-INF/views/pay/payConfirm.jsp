<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-22
  Time: 오전 2:20
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
    <div class="rc_big_box">
        <div class="rc_middle_box">
            <h1 class="reserv_conf_main_header">결제 완료</h1>
            <div class="reserv_icon_box pay_conf_icon"></div>
            <div>
                <p>결제가 정상적으로 완료되었습니다</p>
                <p>이지투어 상품을 결제해주셔서 감사합니다.</p>
                <p>편안하고 쉬운 여행이 될 수 있도록 준비 중입니다.</p>
                <p>변동사항 발생 즉시 연락드리겠습니다.</p>
            </div>
        </div>
        <div class="rc_reserv_dtl_box">
            <h2 class="rc_header">결제내역</h2>
            <div class="rc_content_box">
                <div>
                    <p class="rc_content_box_header">${rcid.prd_nm}</p>
                    <div>
                        <span class="dv_arl_code">${rcid.arl_nm}</span>
                        <span><fmt:formatDate value="${rcid.loc_fin_date}" pattern="yyyy년 MM월 dd일(E) hh:mm"/></span> ~
                        <span class="dv_arl_code">${rcid.arl_nm}</span>
                        <span><fmt:formatDate value="${rcid.dom_fin_date}" pattern="yyyy년 MM월 dd일(E) hh:mm"/></span>
                    </div>
                </div>
                <div class="rc_dtl_content_box">
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 예약번호</dt>
                            <dd>${rcid.rsvt_no}</dd>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 결제일</dt>
                            <dd><fmt:formatDate value="${payDto.pay_date}" pattern="yyyy/MM/dd일(E)"/></dd>
                        </dl>
                    </div>
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 상품번호</dt>
                            <dd>${rcid.prd_cd}</dd>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 행사번호</dt>
                            <dd>${rcid.prd_dtl_cd}</dd>
                        </dl>
                    </div>
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 여행기간</dt>
                            <dd>${rcid.trv_dtl_per}</dd>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 출발인원</dt>
                            <dd>총 ${rcid.adt_cnt + rcid.chd_cnt + rcid.bb_cnt}명</dd>
                        </dl>
                    </div>
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 상품금액</dt>
                            <dd><fmt:formatNumber value="${rcid.sum_prc}" type="number"/>원</dd>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 입금액</dt>
                            <dd><fmt:formatNumber value="${rcid.sum_prc}" type="number"/>원</dd>
                        </dl>
                    </div>
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 결제상태</dt>
                            <c:choose>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7A'}">
                                    <dd>${payDto.cmn_cd_pay_stt}</dd>
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7B'}">
                                    <dd>결제취소</dd>
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7C'}">
                                    <dd>결제완료</dd>
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7D'}">
                                    <span>결제실패</span>
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7E'}">
                                    <span>결제준비중</span>
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7F'}">
                                    <span>결제오류</span><!--결제위조시도(금액)-->
                                </c:when>
                                <c:when test="${payDto.cmn_cd_pay_stt eq '7G'}">
                                    <span>결제오류</span><!--결제위조시도(마일리지)-->
                                </c:when>
                            </c:choose>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 마일리지사용</dt>
                            <dd><fmt:formatNumber value="${payDto.used_mlg}" type="number"/>마일리지</dd>
                        </dl>
                    </div>
                    <div class="rc_low">
                        <dl class="rc_col1">
                            <dt>- 결제방식</dt>
                            <dd>${payDto.pay_mthd}</dd>
                        </dl>
                        <dl class="rc_col2">
                            <dt>- 할부개월수</dt>
                            <dd>${payDto.dvd_mnt==0 ? "일시불" : payDto.dvd_mnt + "개월" }</dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="rc_reserv_dtl_box">
            <h2 class="rc_header">여행자 정보</h2>
            <div class="rc_reserv_dtl_subbox">
                <c:forEach var="trvlrInfo" items="${tid}" begin="0" end="${tid.size()}">
                    <div class="rc_trvlr_box">
                        <div class="rc_trvlr_nm">${trvlrInfo.trvlr_nm}</div>
                        <div class="rc_trvlr_info_box">
                            <c:set var="trvlr_en_nm" value="${trvlrInfo.trvlr_en_nm}"/>
                            <div  class="rc_trvlr_en_nm">- ${empty trvlr_en_nmm ? "영문명" : trvlr_en_nm}</div>
                            <div class="rc_trvlr_sub_box">
                                <span class="rc_trvlr_prc_title">- 상품가 </span>
                                <span class="rc_trvlr_prc"><fmt:formatNumber value="${trvlrInfo.pay_ftr_prc}" type="number"/></span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="rc_btn_box">
            <button class="reservList reserv_btn_large" type="button">예약 목록보기</button>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('.reservList').on("click", function(){
                location.href = '<c:url value="/reserv/list"/>';
            });
        });
    </script>
</body>
</html>
