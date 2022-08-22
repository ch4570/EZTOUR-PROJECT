<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>
<c:set var="prfImg" value="${loginId=='' ? '' : sessionScope.userDto.cmn_cd_prf_img}"/>



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
        <span id="user-name" >${loginName}</span>
        <span>님, 안녕하세요!</span>
        <div>마일리지는 <span style="font-size: xx-large;"><fmt:formatNumber value="${userDto.mlg}" pattern="#,###" />P</span>가 있어요</div>
        <div>${loginName}님이 좋아하시는 여행 스타일은 무엇인가요?</div>
        <div>나는 머머머머머하고싶어요. 나는 집에가고싶어요</div>
      </div>
    <div>
        <c:if test="${prfImg eq '3A'}">
          <img src="../image/user/Bikini.png?" alt="">
        </c:if>
        <c:if test="${prfImg eq '3B'}">
           <img src="../image/user/Dancing.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3C'}">
        <img src="../image/user/Doggie.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3D'}">
        <img src="../image/user/Laying Down.png" alt="">
        </c:if>
        <c:if test="${prfImg eq '3E'}">
        <img src="../image/user/Meditating.png" alt="">
        </c:if>

    </div>
        <div>
            <a href="/user/usrMod">회원정보수정(클릭)</a>
        </div>
    </div>
    <br>
    <div class="mypage-sub-content">
        <div>
          <div class="reserv-header">
            <span>예약 내역</span>
            <span>더보기</span>
          </div>
          <div style="display: flex;">
            <section class="box"></section>
            <section class="box"></section>
          </div>
        </div>
      <div>
        문의내역
      </div>
    <div>
      서비스 바로가기
    </div>

    </div>
  </div>


  <br>
  <br>


</body>
</html>
