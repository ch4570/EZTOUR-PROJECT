<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
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
          <li><a href="#">동남아</a></li>
          <li><a href="#">동아시아</a></li>
          <li><a href="#">서유럽</a></li>
          <li><a href="#">동유럽</a></li>
          <li><a href="#">오세아니아</a></li>
          <li><a href="#">이벤트</a></li>
        </ul>
      </nav>
      <div class="main-aside">
        <ul class="aside__list">
          <%-- 로그온일때 로그인->로그아웃 / 회원가입->마이페이지 --%>

<c:choose>
    <c:when test="${loginId==''}">
          <li><a href="/user/login">로그인</a></li>
          <li><a href="/user/join">회원가입</a></li>
    </c:when>
    <c:when test="${loginId!=''}">
          <li><a href="/user/logout">로그아웃</a></li>
          <li><a href="/user/mypage">마이페이지</a></li>
    </c:when>
</c:choose>
          <li><a href="#">에약확인/결제</a></li>
          <li><a href="/customer/main">고객센터</a></li>
          <li><button id="open" class="prd-search"><i class="fa-solid fa-magnifying-glass"></i></button></li>

          <div class="modal hidden">
            <div class="modal__overlay"></div>
            <div class="modal__content">
              <div class="modal__cancel">
                <button class="modal__cancel--button"><i class="fa-solid fa-x"></i></button>
              </div>
              <h1>어디로 떠나세요?</h1>
              <form class="form-search" action="/search">
                <div>
                  <input class="search-input" name="prd-keyword" type="search" placeholder="검색어를 입력하세요"/>
                  <button class="search-btn" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                  </button>
                </div>
              </form>
            </div>
          </div>
        </ul>
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
