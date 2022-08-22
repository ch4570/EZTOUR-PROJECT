<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-22
  Time: 오전 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <div>
        <div>
            <h1>결제 완료</h1>
            <p>결제가 정상적으로 완료되었습니다</p>
            <span>이지투어 상품을 결제해주셔서 감사합니다.</span>
            <span>편안하고 쉬운 여행이 될 수 있도록 준비 중입니다.</span>
            <span>변동사항 발생 즉시 연락드리겠습니다.</span>
        </div>
        <div>
            <div>
                <h3>결제내역</h3>
                <div>
                    <p>${rcid.prd_nm}</p>
                    <div>
                        <span>${rcid.go_dpr_arl_id}</span>
                        <span>${rcid.go_dpr_tm}</span> ~
                        <span>${rcid.cb_arr_arl_id}</span>
                        <span>${rcid.cb_arr_tm}</span>
                    </div>
                </div>
                <div>
                    <div>
                        <dl>
                            <dt>예약번호</dt>
                            <dd>${rcid.rsvt_no}</dd>
                        </dl>
                        <dl>
                            <dt>결제일</dt>
                            <dd>${payDto.pay_date}</dd>
                        </dl>
                    </div>
                    <div>
                        <dl>
                            <dt>상픔번호</dt>
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
                            <dd>${rcid.sum_prc}원</dd>
                        </dl>
                        <dl>
                            <dt>입금액</dt>
                            <dd>${payDto.pay_prc}원</dd>
                        </dl>
                    </div>
                    <div>
                        <dl>
                            <dt>결제상태</dt>
                            <dd>${payDto.cmn_cd_pay_stt}</dd>
                        </dl>
                        <dl>
                            <dt>마일리지사용</dt>
                            <dd>${payDto.used_mlg} 마일리지</dd>
                        </dl>
                    </div>
                    <div>
                        <dl>
                            <dt>결제방식</dt>
                            <dd>${payDto.pay_mthd}</dd>
                        </dl>
                        <dl>
                            <dt>할부개월수</dt>
                            <dd>${payDto.dvd_mnt==0 ? "일시불" : payDto.dvd_mnt }</dd>
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
            <button class="reservList" type="button">예약 목록보기</button>
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
