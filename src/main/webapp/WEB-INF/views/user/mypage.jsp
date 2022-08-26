<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setHeader("Pragma","no-store");
  response.setDateHeader("Expires",0);
%>

<html>
<head>
    <title>My Page</title>
  <link rel="stylesheet" href="/css/user/user_mypage.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
  <div class="mypage-content">
    <div>

    <div class="mypage-main-content" >
        <div>
          <div style="display: flex; flex-direction:column; height:250px; justify-content: space-between; font-size: large; margin-top: 30px;">
            <div style="margin-bottom: 30px">
              <span id="user-name" >${loginName}</span>
              <span style="font-size: 30px;">님, 안녕하세요!</span>
            </div>
            <div style="display: flex; flex-direction: column; justify-content: space-around; height: 80px; font-weight: bold">
                <div>${loginName}님의 마일리지는 <span id="mlg"><fmt:formatNumber value="${mlg}" pattern="#,###" />P</span>가 있어요</div>
                <div>어떤 여행을 하고싶으신가요?</div>
            </div>
                <div style="display: flex; flex-direction: column; height: 90px; justify-content: space-around;">
                <div style="font-weight: bold;">나는 <span style="font-weight: bolder; font-size: xx-large">머머머머머</span>하고싶어요. 나는 <span style="font-weight: bolder; font-size: xx-large">집에가고</span>싶어요</div>
                <div style="font-weight: bold">나는 <span style="font-weight: bolder; font-size: xx-large">어쩌구저쩌구어쩌구</span>하고싶어요.</div>
            </div>
          </div>
        </div>

    <div>
        <c:if test="${prfImg eq '3A'}">
           <img class="no-cache" src="../image/user/Bikini.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3B'}">
           <img class="no-cache" src="../image/user/Dancing.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3C'}">
            <img class="no-cache" src="../image/user/Doggie.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3D'}">
            <img class="no-cache" src="../image/user/Laying Down.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3E'}">
            <img class="no-cache" src="../image/user/Meditating.png" alt="">
        </c:if>
        <div style="width: 70px; position: relative; left: 300px; bottom: 50px;" >
            <a class="openPwChkBtn" ><i class="fa fa-cog fa-5x" aria-hidden="true"></i></a>
        </div>
    </div>
    </div>
    <br>
    <div class="mypage-sub-content">
        <div>
          <div class="reserv-header" style="display: flex; justify-content: space-between; padding: 0px 5px">
            <span class="box-header">예약 내역</span>
            <a href="/reserv/list"><span>더보기 <i class="fa fa-long-arrow-right" aria-hidden="true"></i></span></a>
          </div>

          <div style="display: flex; justify-content: space-between; height: 360px">

              <c:if test="${empty rsrvlist}">
              <section class="box-non-rsrv">
                  <span style="margin-top: 100px; font-size: 20px; font-weight: bold; position: relative; top: 100px; left: 410px;">
                      예약 내역이 없습니다
                  </span>
              </section>
              </c:if>

            <c:set var="i" value="0" />
            <c:forEach var="rsvt" items="${rsrvlist}">
                <c:set var="i" value="${ i+1 }" />
              <div>
               <a href="<c:url value='/reserv/reservView?rsvt_no=${rsvt.rsvt_no}&prd_dtl_cd=${rsvt.prd_dtl_cd}'/>"  >
                  <section class="box">
                      <div class="image-box">
                      </div>
                      <div style="width: 200px; padding: 20px; display: flex; flex-direction: column; justify-content: space-around">

                          <dl style="font-weight: bolder; display: flex; justify-content: space-between; flex-direction: column">
                              <dt>
                                  예약번호
                              </dt>
                              <dd>
                                  ${rsvt.rsvt_no}
                              </dd>
                          </dl>

                          <dl style="font-weight: bolder; display: flex; justify-content: space-between; flex-direction: column; margin-bottom: 20px">
                              <dt>
                                  예약일
                              </dt>
                              <dd>
                                  <fmt:formatDate value="${rsvt.rsvt_date}" pattern="yyyy-MM-dd" />
                              </dd>
                          </dl>

                          <div>
                              <c:choose>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6A'}">
                                  <input class="input-rsrv-stt" value="예약접수" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6B'}">
                                      <input class="input-rsrv-stt" value="예약승인" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6C'}">
                                      <input class="input-rsrv-stt" value="예약반려" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6D'}">
                                      <input class="input-rsrv-stt" value="예약취소" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6E'}">
                                      <input class="input-rsrv-stt" value="예약완료" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6F'}">
                                      <input class="input-rsrv-stt" value="예약불가" >
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_rsvt_stt eq '6G'}">
                                      <input class="input-rsrv-stt" value="예약기타상태" >
                                  </c:when>
                              </c:choose>
                              <c:choose>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7A'}">
                                  <input class="input-pay-stt" value="결제대기">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7B'}">
                                      <input class="input-pay-stt" value="결제취소">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7C'}">
                                      <input class="input-pay-stt" value="결제완료">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7D'}">
                                      <input class="input-pay-stt" value="결제실패">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7E'}">
                                      <input class="input-pay-stt" value="결제 준비중">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7F'}">
                                      <input class="input-pay-stt" value="결제위조시도(금액)">
                                  </c:when>
                                  <c:when test="${rsvt.cmn_cd_pay_stt eq '7G'}">
                                      <input class="input-pay-stt" value="결제위조시도(마일리지)">
                                  </c:when>
                              </c:choose>

                          </div>

                          <div style="font-size: large">
                              <c:choose>
                                  <c:when test="${fn:length(rsvt.prd_nm) > 25}">
                                    <c:out value="${fn:substring(rsvt.prd_nm,0,24)}"/>....
                                  </c:when>
                                  <c:otherwise>
                                     <c:out value="${rsvt.prd_nm}"/>
                                  </c:otherwise>
                              </c:choose>
                          </div>
                          <div>
                              <span style="font-size: 25px; font-weight: bolder"><fmt:formatNumber value="${rsvt.sum_prc}" pattern="#,###" /></span>원
                          </div>
                      </div>
                  </section>
              </a>
            </div>
            </c:forEach>
        </div>
        </div>
        <div class="box-header">결제 내역</div>
        <c:if test="${empty paylist}">
        <section class="box-non-rsrv">
              <span style="margin-top: 100px; font-size: 20px; font-weight: bold; position: relative; top: 100px; left: 410px;">
                  결제내역이 없습니다.
              </span>
        </section>
        </c:if>

        <c:if test="${not empty paylist}">
        <table>
            <thead>
            <tr>
                <th>여행 상품 번호</th>
                <th>상품 이름</th>
                <th>결제 금액</th>
                <th>결제일</th>
                <th>결제상태</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="0" />
            <c:forEach var="pay" items="${paylist}">
            <c:set var="i" value="${ i+1 }" />
            <tr>
                <td><c:out value=" ${pay.prd_dtl_cd}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${fn:length(pay.prd_nm) > 20}">
                            <c:out value="${fn:substring(pay.prd_nm,0,19)}"/>....
                        </c:when>
                        <c:otherwise>
                            <c:out value="${pay.prd_nm}"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><fmt:formatNumber value="${pay.pay_prc}" pattern="#,###" />원</td>
                <td><c:out value="${pay.pay_date}"/> </td>

                <c:set var="stt" value="${pay.cmn_cd_pay_stt}" />
                <c:choose>
                    <c:when test="${stt eq '7A'}">
                        <td>결제대기</td>
                    </c:when>
                    <c:when test="${stt eq '7B'}">
                        <td>결제취소</td>
                    </c:when>
                    <c:when test="${stt eq '7C'}">
                        <td>결제완료</td>
                    </c:when>
                    <c:when test="${stt eq '7D'}">
                        <td>결제실패</td>
                    </c:when>
                    <c:when test="${stt eq '7E'}">
                        <td>결제 준비중</td>
                    </c:when>
                    <c:when test="${stt eq '7F'}">
                        <td>결제위조시도(금액)</td>
                    </c:when>
                    <c:when test="${stt eq '7G'}">
                        <td>결제위조시도(마일리지)</td>
                    </c:when>
                </c:choose>
            </tr>
            </c:forEach>

            </tbody>
        </table>
        </c:if>

        <div style="display: flex; justify-content: space-between; margin-top: 50px;">
            <div style="display: flex; flex-direction: column">
                <div class="box-header">문의 내역</div>
                <div class="box-sub" style="display: flex; flex-direction: column; justify-content: center; align-items: center">
                    <span style="font-size: 20px; font-weight: bold; position: relative;">
                      문의 내역이 없습니다
                  </span>
                </div>
            </div>

            <div style="display: flex; flex-direction: column">
                <div class="box-header">서비스 바로가기</div>
                <div class="service-box-frame" style="">
                    <a class="service-box"><i class="fa fa-heart-o fa-2x" aria-hidden="true"></i><div>관심상품</div>
                    </a>
                    <a class="service-box"><i class="fa fa-money fa-2x" aria-hidden="true"></i><div>포인트</div></a>
                    <a class="service-box"><i class="fa fa-ticket fa-2x" aria-hidden="true"></i><div>할인쿠폰</div></a>
                    <a class="service-box" type="button" id="openPwChkBtn"><i class="fa fa-user-circle-o fa-2x" aria-hidden="true"></i><div>회원정보 수정</div></a>
                </div>
            </div>
        </div>

    </div>
  </div>

  <br>
  <br>

<!-- 정보수정화면 접근을 위한 비밀번호 입력 모달창 -->
<div class="modal hidden" id="pwCheckModal">
  <div class="modal__overlay" id="pwCheckOverlay"></div>
  <div class="modal__content" style="width: 500px; height: 300px;">
      <br/>
      <div name="pwChkForm" style="display: flex; flex-direction: column; align-items: center">
          <h2 style="font-weight: bolder; font-size: x-large; padding-right: 250px">비밀번호 확인</h2>
          <h2 style="padding-top: 20px; padding-left : 40px; padding-right: 50px; color: #333333; font-weight: bold"><i class="fa fa-check" aria-hidden="true"></i> 회원님의 정보 보호를 위해 비밀번호를 입력해주세요.</h2>
          <hr>

          <div style="font-size: 18px;height: 80px; margin-top: 10px;">
              <div class="form-check" id="pwChk">
                  <div id="pwdChkFail" style="padding-top: 10px; font-size: 12px; color: crimson; text-align: left">
                  </div>
                   <input class="form-check-input" type="password" style="margin-top: 10px;" name="pwd" id="pwd" value="" placeholder="비밀번호를 입력해주세요">

              </div>
              <br/>

          </div>
          <input type="button" id="pwChkBtn" style="margin-top: 30px;" value="확인">
      </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
  let msg = "${msg}";
  if(msg=="MOD_OK")   alert("회원정보가 정상적으로 수정되었습니다.");
  if(msg=="GET_ERR")   alert("올바른 비밀번호가 아닙니다. 다시 입력해주세요.");
  if(msg=="DB_ERR")   alert("회원정보를 가져오는데 문제가 생겼습니다. 다시 시도해주세요.");

  $(document).ready(function ()
  {
      $('.no-cache').attr('src',function () { return $(this).attr('src') + "?a=" + Math.random() });
  });


    function setMessage(msg){
      document.getElementById("pwdChkFail").innerHTML = msg;
    }


  $("#pwChkBtn").click(function(){
      let pwd = $("#pwd").val()
      if(pwd.length==0) {
          setMessage('비밀번호를 입력해주세요.');
          return false;
      }
          $.ajax({
              type:'GET',
              url: '/checkPwdForUsrMod/' + pwd,
              success : function(pwdCheck){
                  if(pwdCheck===true) {
                      location.href='/user/usrMod'
                  }else{
                      setMessage("일치하지 않는 비밀번호입니다. 다시 입력햐주세요.")
                  }
              },
              error   : function(){ setMessage("일지하지 않습니다.") }
          });
  });


  const pwCheckModal = document.querySelector("#pwCheckModal");
  const pwCheckOverlay = pwCheckModal.querySelector("#pwCheckOverlay");
  const openPwChkBtn = document.querySelector(".openPwChkBtn");
  const openPwChkBtn2 = document.querySelector("#openPwChkBtn");
  const openPwChkModal = () => {
      pwCheckModal.classList.remove("hidden");
  }
  const closePwChkModal = () => {
      pwCheckModal.classList.add("hidden")
  }
  openPwChkBtn.addEventListener("click", openPwChkModal);
  openPwChkBtn2.addEventListener("click", openPwChkModal);
  pwCheckOverlay.addEventListener("click", closePwChkModal);
  pwCheckOverlay.addEventListener("click", function(){document.getElementById('pwd').value=null});


</script>

</body>
</html>
