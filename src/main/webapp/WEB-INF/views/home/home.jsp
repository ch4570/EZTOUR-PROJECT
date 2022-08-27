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
	<link rel="shortcut icon" href="<c:url value='/image/logo/logo_shortcut.png'/> "/>
	<link rel="stylesheet" href="/css/home/home.css">
	<title>EZ Tour</title>
</head>
<body>

<%--header--%>

<header>
	<div class="header-inner">
		<div class="main-title">
			<a href="/">
				<h1><img src="<c:url value='/image/logo/logo_main.png'/>"><img src="<c:url value='/image/logo/logo_main_letter.png'/>"></h1>
			</a>
		</div>
		<nav class="main-nav">
			<ul class="nav__list">
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">동남아</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=1&nt_cd_nm=필리핀&usr_id=${sessionScope.userDto.usr_id}'/>">필리핀</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=2&nt_cd_nm=태국&usr_id=${sessionScope.userDto.usr_id}'/>">태국</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=3&nt_cd_nm=베트남&usr_id=${sessionScope.userDto.usr_id}'/>">베트남</a>
						<a href="<c:url value='/product/list?cntn_cd=A&nt_cd=5&nt_cd_nm=라오스'/>">라오스</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">동아시아</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=1&nt_cd_nm=싱가포르&usr_id=${sessionScope.userDto.usr_id}'/>">싱가포르</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=2&nt_cd_nm=일본&usr_id=${sessionScope.userDto.usr_id}'/>">일본</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=3&nt_cd_nm=중국&usr_id=${sessionScope.userDto.usr_id}'/>">중국</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=4&nt_cd_nm=몽골&usr_id=${sessionScope.userDto.usr_id}'/>">몽골</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=5&nt_cd_nm=홍콩&usr_id=${sessionScope.userDto.usr_id}'/>">홍콩</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=6&nt_cd_nm=한국&usr_id=${sessionScope.userDto.usr_id}'/>">한국</a>
						<a href="<c:url value='/product/list?cntn_cd=B&nt_cd=7&nt_cd_nm=대만&usr_id=${sessionScope.userDto.usr_id}'/>">대만</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">서유럽</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=1&nt_cd_nm=프랑스&usr_id=${sessionScope.userDto.usr_id}'/>">프랑스</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=2&nt_cd_nm=영국&usr_id=${sessionScope.userDto.usr_id}'/>">영국</a>
						<a href="#">아일랜드</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=4&nt_cd_nm=스위스&usr_id=${sessionScope.userDto.usr_id}'/>">스위스</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=5&nt_cd_nm=이탈리아&usr_id=${sessionScope.userDto.usr_id}'/>">이탈리아</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=6&nt_cd_nm=스페인&usr_id=${sessionScope.userDto.usr_id}'/>">스페인</a>
						<a href="<c:url value='/product/list?cntn_cd=D&nt_cd=7&nt_cd_nm=독일&usr_id=${sessionScope.userDto.usr_id}'/>">독일</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">동유럽</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=1&nt_cd_nm=체코&usr_id=${sessionScope.userDto.usr_id}'/>">체코</a>
						<a href="<c:url value='/product/list?cntn_cd=C&nt_cd=2&nt_cd_nm=그리스&usr_id=${sessionScope.userDto.usr_id}'/>">그리스</a>
						<a href="#">러시아</a>
						<a href="#">우크라이나</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<div class="nav__list--dropdown-menu"><a href="#">오세아니아</a></div>
					<div class="nav__list--dropdown-content">
						<a href="<c:url value='/product/list?cntn_cd=E&nt_cd=1&nt_cd_nm=호주&usr_id=${sessionScope.userDto.usr_id}'/>">호주</a>
						<a href="<c:url value='/product/list?cntn_cd=E&nt_cd=2&nt_cd_nm=뉴질랜드&usr_id=${sessionScope.userDto.usr_id}'/>">뉴질랜드</a>
					</div>
				</li>
				<li class="nav__dropdown">
					<a href="#">이벤트</a>
					<a href="<c:url value='/review/list/'/>" style="margin-left: 15px"> 후기글</a></li>
				</li>
			</ul>
		</nav>
		<div class="main-aside">
			<ul class="aside__list">

				<c:choose>
					<c:when test="${loginId==''}">
						<li><a href="/user/login">로그인</a></li>
						<li><a href="/user/selectJoin">회원가입</a></li>
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
				<li><a href="<c:url value='/reserv/list'/>">예약확인/결제</a></li>
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

	<div class="modal_main hidden">
		<div class="modal_wrap">
			<div class="modal_recently">
				<i class="fa-solid fa-x" name="modal_close_btn"></i>
				<div class="modal_tlt">
					<strong>최근 본 상품 (${sessionScope.trvList.size()})</strong>
					<hr>
				</div>
				<div class="modal_recently_content">
					<div class="product--list__modal">
						<c:forEach items="${sessionScope.trvList}" var="trvList">
							<div class="product__recent--list">
								<a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd="${trvList.prd_cd}"></i></a>
								<img src="<c:url value='${trvList.img_pth}'/>" width="300px" height="400px">
								<a href="/product/recent/list?prd_cd=${trvList.prd_cd}"><p>${trvList.prd_nm}</p></a>
								<div class="product__cost--list"><strong>${trvList.prd_str_prc}</strong><h6>원</h6></div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		const openButton = document.getElementById("open");
		const modal = document.querySelector(".modal");
		const overlay = modal.querySelector(".modal__overlay");
		const closeBtn = modal.querySelector("button");
		const modal_main = document.querySelector(".modal_main");
		const openModal = () => {
			modal.classList.remove("hidden");
		}
		const closeModal = () => {
			modal.classList.add("hidden")
		}
		closeBtn.addEventListener("click", closeModal);
		openButton.addEventListener("click", openModal);

		$(document).ready(function (){

			$('i[name=modal_close_btn]').click(function (){
				modal_main.classList.add("hidden");
			});

			$('i[name=prd_history]').click(function (){
				modal_main.classList.remove("hidden");
			});

			$(document).on("click","i[name=product__recent--cancel]",function (){
				let prd_cd = $(this).attr('prd_cd');

				$.ajax({
					type : "GET",
					url : "<c:url value='/product/show/delete'/>",
					data : {'prd_cd':prd_cd},
					success : function (data){
						$('.product__recent--list').remove();
						$('.modal_tlt > strong').remove();

						if(data.length == "0"){
							$('.modal_tlt').append('<strong>최근 본 상품 (0)</strong>');
						}else{
							$('.modal_tlt').append('<strong>최근 본 상품 ('+data.length+')</strong>');
						}

						$(data).each(function (){
							$('.product--list__modal').append('<div class="product__recent--list">'+
									'<a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd='+this.prd_cd+'></i></a>'
									+ '<img src='+ this.img_pth +' width="300px" height="400px">' +
									'<a href="/product/recent/list?prd_nm="'+this.prd_nm+'><p>'+ this.prd_nm +'</p></a>'
									+ '<div class="product__cost--list"><strong>' + this.prd_str_prc + '</strong><h6>원</h6></div>'
									+ '</div>'
							);

						});
					},
					error : function (data){

					}
				});
			});

			$('i[name=product__recent--cancel]').on("click",function (){
				let prd_cd = $(this).attr('prd_cd');

				$.ajax({
					type : "GET",
					url : "<c:url value='/product/show/delete'/>",
					data : {'prd_cd':prd_cd},
					success : function (data){
						$('.product__recent--list').remove();
						$('.modal_tlt > strong').remove();

						if(data.length == "0"){
							$('.modal_tlt').append('<strong>최근 본 상품 (0)</strong>');
						}else{
							$('.modal_tlt').append('<strong>최근 본 상품 ('+data.length+')</strong>');
						}



						$(data).each(function (){
							$('.product--list__modal').append('<div class="product__recent--list">'+
									'<a><i class="fa-solid fa-x" name="product__recent--cancel" prd_cd='+this.prd_cd+'></i></a>'
									+ '<img src='+ this.img_pth +' width="300px" height="400px">' +
									'<a href="/product/recent/list?prd_nm="'+this.prd_nm+'><p>'+ this.prd_nm +'</p></a>'
									+ '<div class="product__cost--list"><strong>' + this.prd_str_prc + '</strong><h6>원</h6></div>'
									+ '</div>'
							);


						});
					},
					error : function (data){

					}
				});

			});

		});


	</script>

</header>

<%--body--%>

<main class="home__main">

<%--	full slider--%>
	<div class="main__full-slider">
		<!-- fade css -->
		<div class="myslide fade">
			<div class="txt">
				<p>이것이<br />여행이지</p>
			</div>
			<img class="fullImg" src="/img/home/main_img/main1.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="myslide fade">
			<div class="txt">
				<p>이것이<br />여행이지</p>
			</div>
			<img class="fullImg" src="/img/home/main_img/main2.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="myslide fade">
			<div class="txt">
				<p>이것이<br />여행이지</p>
			</div>
			<img class="fullImg" src="/img/home/main_img/main3.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="myslide fade">
			<div class="txt">
				<p>이것이<br />여행이지</p>
			</div>
			<img class="fullImg" src="/img/home/main_img/main4.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="myslide fade">
			<div class="txt">
				<p>이것이<br />여행이지</p>
			</div>
			<img class="fullImg" src="/img/home/main_img/main5.jpg" style="width: 100%; height: 100%" />
		</div>
		<!-- /fade css -->

		<!-- onclick js -->
		<a class="prevBtn" onclick="plusSlides(-1)">&#10094;</a>
		<a class="nextBtn" onclick="plusSlides(1)">&#10095;</a>

		<div class="dotsbox" style="text-align: center">
			<span class="dot" onclick="currentSlide(1)"></span>
			<span class="dot" onclick="currentSlide(2)"></span>
			<span class="dot" onclick="currentSlide(3)"></span>
			<span class="dot" onclick="currentSlide(4)"></span>
			<span class="dot" onclick="currentSlide(5)"></span>
		</div>
		<!-- /onclick js -->
	</div>



<%--	<section class="tripTaste">--%>
<%--		<div class="tripTaste-tit">--%>
<%--						--%>
<%--		</div>--%>
<%--	</section>--%>



<%-- slider --%>
	<section class="product">
		<h2 class="product-category">설레는 곳, 그게 바로 여행EZ</h2>
		<button class="pre-btn"><i class="fas fa-angle-right"></i></button>
		<button class="nxt-btn"><i class="fas fa-angle-right"></i></button>
		<div class="product-container">

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">괌으로 떠나요</span>
					<img src="/img/home/slider/gaum.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">환상의 그랜드캐니언</span>
					<img src="/img/home/slider/grand.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">알로하 하와이</span>
					<img src="/img/home/slider/hawaii.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">최고의 휴양지, 세부</span>
					<img src="/img/home/slider/sebu.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">자연을 느껴요, 스위스</span>
					<img src="/img/home/slider/swiss.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

			<div class="product-card">
				<div class="product-image">
					<span class="item__tit">도쿄로 떠나요</span>
					<img src="/img/home/slider/tokyo.jpg" class="product-thumb" alt="">
					<button class="card-btn">여행지 정보보기</button>
				</div>
			</div>

		</div>
	</section>

	<%--	gallery--%>
	<section class="home__gallery">
		<img src="/img/greece.jpg" alt="a forest after an apocalypse">
		<img src="/img/paris.jpg" alt="a waterfall and many rocks">
		<img src="/img/roma.jpg" alt="a house on a mountain">
		<img src="/img/spain.jpg" alt="sime pink flowers">
		<img src="/img/sydney.jpg" alt="big rocks with some trees">
	</section>


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
					<a class="aside__link">
						<span><i class="fas fa-history" name="prd_history"></i></span>
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

	const myslide = document.querySelectorAll('.myslide'),
			dot = document.querySelectorAll('.dot');
	let counter = 1;
	slidefun(counter);

	let timer = setInterval(autoSlide, 3500);
	function autoSlide() {
		counter += 1;
		slidefun(counter);
	}
	function plusSlides(n) {
		counter += n;
		slidefun(counter);
		resetTimer();
	}
	function currentSlide(n) {
		counter = n;
		slidefun(counter);
		resetTimer();
	}
	function resetTimer() {
		clearInterval(timer);
		timer = setInterval(autoSlide, 3500);
	}

	function slidefun(n) {

		let i;
		for(i = 0;i<myslide.length;i++){
			myslide[i].style.display = "none";
		}
		for(i = 0;i<dot.length;i++) {
			dot[i].className = dot[i].className.replace(' active', '');
		}
		if(n > myslide.length){
			counter = 1;
		}
		if(n < 1){
			counter = myslide.length;
		}
		myslide[counter - 1].style.display = "block";
		dot[counter - 1].className += " active";
	}

	$(document).ready(function () {
		// 자세히보기 버튼 클릭 시 클래스 'active' 추가
		$('button[name="asideBtn"]').on('click', function () {
			$(".aside").toggleClass('open');
			$(".aside__btn").toggleClass('open');
		})
	});

	const productContainers = [...document.querySelectorAll('.product-container')];
	const nxtBtn = [...document.querySelectorAll('.nxt-btn')];
	const preBtn = [...document.querySelectorAll('.pre-btn')];

	productContainers.forEach((item, i) => {
		let containerDimensions = item.getBoundingClientRect();
		let containerWidth = containerDimensions.width;

		nxtBtn[i].addEventListener('click', () => {
			item.scrollLeft += containerWidth;
		})

		preBtn[i].addEventListener('click', () => {
			item.scrollLeft -= containerWidth;
		})
	})



	let msg = "${msg}";
	if(msg=="REG_OK")   alert("회원가입이 완료되었습니다. 로그인 해주세요.");
	if(msg=="DEL_OK")   alert("정상적으로 회원 탈퇴 되셨습니다.");
	if(msg=="GET_ERR")   alert("회원정보를 불러오는데 문제가 생겼습니다.");
	if(msg=="ACC_ERR")   alert("잘못된 접근입니다.");

</script>
</body>
</html>
