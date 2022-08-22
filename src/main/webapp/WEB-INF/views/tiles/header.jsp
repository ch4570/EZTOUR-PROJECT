<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title></title>
  </head>
  <body>
    <div class="header-inner">
      <div class="main-title">
        <a href="/">
          <h1>EZTour</h1>
        </a>
      </div>
      <nav class="main-nav">
        <ul class="nav__list">
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">동남아</a></div>
            <div class="nav__list--dropdown-content">
              <a href="<c:url value='/product/list?cntn_cd=A&nt_cd=1&nt_cd_nm=필리핀'/>">필리핀</a>
              <a href="<c:url value='/product/list?cntn_cd=A&nt_cd=2&nt_cd_nm=태국'/>">태국</a>
              <a href="<c:url value='/product/list?cntn_cd=A&nt_cd=3&nt_cd_nm=베트남'/>">베트남</a>
              <a href="<c:url value='/product/list?cntn_cd=A&nt_cd=5&nt_cd_nm=라오스'/>">라오스</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">동아시아</a></div>
            <div class="nav__list--dropdown-content">
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=1&nt_cd_nm=싱가포르'/>">싱가포르</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=2&nt_cd_nm=일본'/>">일본</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=3&nt_cd_nm=중국'/>">중국</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=4&nt_cd_nm=몽골'/>">몽골</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=5&nt_cd_nm=홍콩'/>">홍콩</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=6&nt_cd_nm=한국'/>">한국</a>
              <a href="<c:url value='/product/list?cntn_cd=B&nt_cd=7&nt_cd_nm=대만'/>">대만</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">서유럽</a></div>
            <div class="nav__list--dropdown-content">
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=1&nt_cd_nm=프랑스'/>">프랑스</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=2&nt_cd_nm=영국'/>">영국</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=3&nt_cd_nm=아일랜드'/>">아일랜드</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=4&nt_cd_nm=스위스'/>">스위스</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=5&nt_cd_nm=이탈리아'/>">이탈리아</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=6&nt_cd_nm=스페인'/>">스페인</a>
              <a href="<c:url value='/product/list?cntn_cd=D&nt_cd=7&nt_cd_nm=독일'/>">독일</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">동유럽</a></div>
            <div class="nav__list--dropdown-content">
              <a href="<c:url value='/product/list?cntn_cd=C&nt_cd=1&nt_cd_nm=체코'/>">체코</a>
              <a href="<c:url value='/product/list?cntn_cd=C&nt_cd=2&nt_cd_nm=그리스'/>">그리스</a>
              <a href="<c:url value='/product/list?cntn_cd=C&nt_cd=3&nt_cd_nm=러시아'/>">러시아</a>
              <a href="<c:url value='/product/list?cntn_cd=C&nt_cd=4&nt_cd_nm=우크라이나'/>">우크라이나</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">오세아니아</a></div>
            <div class="nav__list--dropdown-content">
              <a href="<c:url value='/product/list?cntn_cd=E&nt_cd=1&nt_cd_nm=호주'/>">호주</a>
              <a href="<c:url value='/product/list?cntn_cd=E&nt_cd=2&nt_cd_nm=뉴질랜드'/>">뉴질랜드</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <a href="#">이벤트</a>
          </li>
        </ul>
      </nav>
      <div class="main-aside">
        <ul class="aside__list">

          <c:choose>
              <c:when test="${loginId==''}">
                    <li><a href="/user/login">로그인</a></li>
                    <li><a href="/user/join">회원가입</a></li>
              </c:when>
              <c:when test="${loginId!=''}">
                    <li><a href="/user/logout">로그아웃</a></li>
                <c:if test="${sessionScope.userDto.rl == 'user'}">
                    <li><a href="/user/mypage">마이페이지</a></li>
                </c:if>
                <c:if test="${sessionScope.userDto.rl == 'Admin' || sessionScope.userDto.rl == 'supAdmin'}">
                    <li><a href="<c:url value='/product/admin'/>">관리자페이지</a></li>
                </c:if>
              </c:when>
          </c:choose>
          <li><a href="#">에약확인/결제</a></li>
          <li><a href="/customer/main">고객센터</a></li>
          <li><button id="open" class="prd-search"><i class="fa-solid fa-magnifying-glass"></i></button></li>
        </ul>
      </div>
    </div>

<%--    모달--%>
    <div class="modal hidden">
      <div class="modal__overlay"></div>
      <div class="modal__content">
        <div class="modal__cancel">
          <button class="modal__cancel--button"><i class="fa-solid fa-x"></i></button>
        </div>
        <h1>어디로 떠나세요?</h1>
        <form class="form-search" action="/search">
          <div>
            <input class="search-input" name="keyword" type="search" placeholder="검색어를 입력하세요"/>
            <button class="search-btn" type="submit">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </form>
      </div>
    </div>

    <script>
      const openButton = document.getElementById("open");
      const modal = document.querySelector(".modal");
      const overlay = modal.querySelector(".modal__overlay");
      const closeBtn = modal.querySelector("button")
      const openModal = () => {
        modal.classList.remove("hidden");
      }
      const closeModal = () => {
        modal.classList.add("hidden")
      }
      closeBtn.addEventListener("click", closeModal);
      openButton.addEventListener("click", openModal);
    </script>

  </body>
</html>
