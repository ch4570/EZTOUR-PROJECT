<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
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
              <a href="#">필리핀</a>
              <a href="#">태국</a>
              <a href="#">베트남</a>
              <a href="#">말레이시아</a>
              <a href="#">라오스</a>
              <a href="#">인도네시아</a>
              <a href="#">싱가포르</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">동아시아</a></div>
            <div class="nav__list--dropdown-content">
              <a href="#">일본</a>
              <a href="#">중국</a>
              <a href="#">몽골</a>
              <a href="#">홍콩</a>
              <a href="#">한국</a>
              <a href="#">대만</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">서유럽</a></div>
            <div class="nav__list--dropdown-content">
              <a href="#">프랑스</a>
              <a href="#">영국</a>
              <a href="#">아일랜드</a>
              <a href="#">스위스</a>
              <a href="#">이탈리아</a>
              <a href="#">스페인</a>
              <a href="#">독일</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">동유럽</a></div>
            <div class="nav__list--dropdown-content">
              <a href="#">체코</a>
              <a href="#">그리스</a>
              <a href="#">러시아</a>
              <a href="#">우크라이나</a>
            </div>
          </li>
          <li class="nav__dropdown">
            <div class="nav__list--dropdown-menu"><a href="#">오세아니아</a></div>
            <div class="nav__list--dropdown-content">
              <a href="#">호주</a>
              <a href="#">뉴질랜드</a>
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
                    <li><a href="/user/auth">회원가입</a></li>
              </c:when>
              <c:when test="${loginId!=''}">
                    <li><a href="/user/logout">로그아웃</a></li>
                    <li><a href="/user/mypage">마이페이지</a></li>
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
