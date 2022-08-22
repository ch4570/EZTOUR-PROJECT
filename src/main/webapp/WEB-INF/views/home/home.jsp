<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>
<c:set var="adminPage" value="/product/admin"/>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://kit.fontawesome.com/46b11191c3.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="/css/home/home.css">
	<title>EZ Tour</title>
</head>
<body>

<%--header--%>

<header>
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
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=1'/>">필리핀</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=2'/>">태국</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=3'/>">베트남</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=4'/>">말레이시아</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=5'/>">라오스</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=6'/>">인도네시아</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">동아시아</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=1'/>">싱가포르</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=2'/>">일본</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=3'/>">중국</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=4'/>">몽골</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=5'/>">홍콩</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=6'/>">한국</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=7'/>">대만</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">서유럽</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=1'/>">프랑스</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=2'/>">영국</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=3'/>">아일랜드</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=4'/>">스위스</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=5'/>">이탈리아</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=6'/>">스페인</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=7'/>">독일</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">동유럽</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=1'/>">체코</a>
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=2'/>">그리스</a>
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=3'/>">러시아</a>
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=4'/>">우크라이나</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">오세아니아</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=E&nt_cd=1'/>">호주</a>
						<a href="<c:url value='/product/list?cntn_cd=E&nt_cd=2'/>">뉴질랜드</a>
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

</header>

<%--body--%>

<main class="home__main">
	<c:if test="${loginId!=''}">
		<div>
			<span style="font-size: xx-large; color: crimson">${loginName}</span>
			<span>님 환영합니다.</span>
		</div>
	</c:if>

	<div class="home__gallery">
		<img src="/img/greece.jpg" alt="a forest after an apocalypse">
		<img src="/img/paris.jpg" alt="a waterfall and many rocks">
		<img src="/img/roma.jpg" alt="a house on a mountain">
		<img src="/img/spain.jpg" alt="sime pink flowers">
		<img src="/img/sydney.jpg" alt="big rocks with some trees">
	</div>
</main>


<%--footer--%>

<footer>

	<div class="aside">
		<button class="aside__btn" name="asideBtn">
			<em><i class="fas fa-ellipsis-v"></i></em>
		</button>
		<div class="aside__menu">
			<ul class="aside__menu--list">
				<li>
					<a class="aside__link" href="#">
						<span><i class="fas fa-history"></i></span>
						<span>최근 본 상품</span>
					</a>
				</li>
				<li>
					<a class="aside__link" href="#">
						<span><i class="far fa-clone"></i></span>
						<span>상품 비교함</span>
					</a>
				</li>
				<li>
					<a class="aside__link" href="#">
						<span><i class="far fa-heart"></i></span>
						<span>관심상품</span>
					</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="foot">FOOTER</div>

</footer>

<script>
	$(document).ready(function () {
		// 자세히보기 버튼 클릭 시 클래스 'active' 추가
		$('button[name="asideBtn"]').on('click', function () {
			$(".aside").toggleClass('open');
			$(".aside__btn").toggleClass('open');
		})
	});

	let msg = "${msg}";
	if(msg=="REG_OK")   alert("회원가입이 완료되었습니다. 로그인 해주세요.");
	if(msg=="DEL_OK")   alert("정상적으로 회원 탈퇴 되셨습니다.");
	if(msg=="GET_ERR")   alert("회원정보를 불러오는데 문제가 생겼습니다.");
	if(msg=="ACC_ERR")   alert("잘못된 접근입니다.");
</script>
</body>
</html>
