<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/common/header.css" />
</head>
<body>
    <button id="openAuthModalBtn">휴대폰 인증 하기</button>

    <!-- 휴대폰 인증 모달창 -->
    <div class="modal hidden" id="authModal">
        <div class="modal__overlay" id="authOverlay"></div>
        <div class="modal__content">
            <h5>인증번호 입력</h5>
            <hr>
            <form id="form" method="" action="">
                <div style="margin-left: 30px; font-size: 18px;">
                    <div class="form-check">
                        <label>이름
                        <input class="form-check-input" name="usr_nm" id="usr_nm"></label>
                        <label>핸드폰 번호
                        <input class="form-check-input" name="phn" id="phn"></label>
                    </div>
                    <br/>
                    <hr>
                    <!-- rest 호출-->
                    <input type="button" id="authModalBtn" onclick="authPhn()" value="인증번호 보내기">
                    <!-- submit 버튼 -->
                    <input class="hidden" type="button" id="checkAuthBtn" value="인증하기">
                </div>
            </form>
            <button id="closeAuthModalBtn"> 닫기 </button>
        </div>
    </div>

<script>
    function authPhn() {
        let phn = $("#phn").val()
        const authModalBtn = document.querySelector("#authModalBtn");
        const checkAuthBtn = document.querySelector("#checkAuthBtn");
            $.ajax({
            type:'GET',       // 요청 메서드
            url: '/authPhn/' + phn,  // 요청 URI
            success : function(msg){
                alert(msg);   // 서버로부터 응답이 도착하면 호출될 함수
                // 밑에 input버튼 생성,
                $(".form-check").append("<input name='checkNum' id='checkNum' placeholder='인증번호를 입력하세요.'>");
                // #phn 사라지고
                authModalBtn.classList.add("hidden")
                // submit 버튼 생성
                checkAuthBtn.classList.remove("hidden")
            },
            error   : function(){ alert("잘못된 요청입니다.") }
        });
    }

    // checkNum 에이잭스
    $("#checkAuthBtn").click(function(){
        let form = $("#form");
        let checkNum = $("#checkNum").val()

        $.ajax({
            type:'GET',
            url: '/checkAuthNum/' + checkNum,
            success : function(msg){
                alert(msg);   // 서버로부터 응답이 도착하면 호출될 함수
                form.attr("action", "<c:url value='/user/authOk'/>");
                form.attr("method", "post");
                form.submit();
            },
            error   : function(){ alert("인증번호가 일치하지 않습니다.") }
        });
    });

    const openAuthModalBtn = document.getElementById("openAuthModalBtn");
    const authModal = document.querySelector("#authModal");
    const authOverlay = authModal.querySelector("#authOverlay");
    const closeAuthModalBtn = authModal.querySelector("#closeAuthModalBtn")
    const openAuthModal = () => {
        authModal.classList.remove("hidden");
    }
    const closeAuthModal = () => {
        authModal.classList.add("hidden")
    }
    openAuthModalBtn.addEventListener("click", openAuthModal);
    closeAuthModalBtn.addEventListener("click", closeAuthModal);
    authOverlay.addEventListener("click", closeAuthModal);

</script>
</body>
</html>
