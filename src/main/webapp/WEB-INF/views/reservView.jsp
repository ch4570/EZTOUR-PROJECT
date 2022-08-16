<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-14
  Time: 오전 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>예약내역</title>
    ${payDto.pay_prc}
    <div>
        <h2>예약상세 내역</h2>
        <div>
            <div>
                [${rcid.prd_dtl_cd}]${rcid.prd_nm}
            </div>
            <div>
                <span>${rcid.go_dpr_arl_id}</span>
                <span>${rcid.go_dpr_tm}</span> ~
                <span>${rcid.cb_arr_arl_id}</span>
                <span>${rcid.cb_arr_tm}</span>
            </div>

            <div>
                <div>
                    <dl>
                        <dt>- 예약번호</dt>
                        <dd>${rcid.rsvt_no}</dd>
                    </dl>
                    <dl>
                        <dt>- 예약일</dt>
                        <dd>${rcid.rsvt_date}</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>- 여행기간</dt>
                        <dd>${rcid.trv_per}</dd>
                    </dl>
                    <dl>
                        <dt>- 출발인원</dt>
                        <dd>총 ${rcid.adt_cnt + rcid.chd_cnt + rcid.bb_cnt}명</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>- 상품금액</dt>
                        <dd>${rcid.sum_prc}</dd>
                    </dl>
                    <dl>
                        <dt>- 입금액</dt>
                        <c:set var="pay" value="${payDto.pay_prc}"/>
                        <dd>${empty pay ? 0 : pay}원</dd>
                    </dl>
                </div>
                <div>
                    <dl>
                        <dt>- 처리상태</dt>
                        <dd>${rcid.cmn_cd_rsvt_stt}</dd>
                    </dl>
                    <dl>
                        <dt>- 결제예정금액</dt>
                        <dd>${rcid.pay_ftr_prc}원</dd>
                    </dl>
                </div>
            </div>
            <div>
                <h2>여행자 정보</h2>
                <div>
                    <c:forEach var="trvlrInfo" items="${tid}" begin="0" end="${tid.size() - 1}">
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
                <h2>상품 결제정보</h2>
                <div>
                    <c:choose>
                        <c:when test="${empty payDto}">
                            결제 내역이 없습니다.
                        </c:when>
                        <c:otherwise> <!--결제일, 결제금액, 상품상세번호, 결제상태, 마일리지 사용금액-->
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
            <button type="button">결제취소</button>
            <button type="button">예약 목록보기</button>
        </div>
    </div>
</head>
<body>

</body>
</html>
