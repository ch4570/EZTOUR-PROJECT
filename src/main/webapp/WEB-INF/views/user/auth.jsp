<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/user/user_selectJoin.css">
</head>
<body>
<div class="form-user-selectjoin">
    <div>
        <div class="form-selectjoin-content" style="height: 500px">
            <h2 class="login-title" id="loginTitle" style="margin-bottom: 100px;">회원가입</h2>

            <div class="form-selectjoin-general">
                <div style="width: 540px;">
                    <h3>본인인증</h3>
                    <div style="font-size: 15px; margin-top: 5px;">휴대폰 인증을 통한 본인인증을 진행해주세요</div>
                </div>
                <div style="height:120px; display: flex; justify-content: center"><button id="openAuthModalBtn"><i class="fa fa-mobile fa-lg" aria-hidden="true" style="color: white"></i> &nbsp;휴대폰 인증 하기</button></div>
                <div style="margin-right: 80px; font-size: 15px;"><i class="fa fa-exclamation-circle" aria-hidden="true" style="color: crimson"></i> SNS 회원가입시 추가정보를 반드시 기입해야 가입 완료됩니다.</div>
            </div>
        </div>
    </div>
</div>



    <!-- 휴대폰 인증 모달창 -->
    <div class="modal" id="authModal">
        <div class="modal__overlay" id="authOverlay"></div>
        <div class="modal__content" style="width: 500px; height: 500px;">
            <div name="authForm" style="height: 500px; display: flex; flex-direction: column; align-items: center; justify-content: space-evenly">
                <h2 style="font-weight: bolder; font-size: x-large; padding-right: 250px">핸드폰 인증</h2>
                <h2 style="padding-top: 20px; padding-left : 20px; padding-right: 70px; color: #333333; font-weight: bold"><i class="fa fa-check" aria-hidden="true"></i> 회원가입을 위해 이름과 핸드폰 번호를 입력해주세요.</h2>
                <hr>
                <form id="form" method="" action="">
                    <div style="margin-left: 30px; font-size: 18px;">
                        <div class="form-check">
                            <div>이름</div>
                            <input class="form-check-input" name="usr_nm" id="usr_nm">
                            <div>핸드폰 번호</div>
                            <input class="form-check-input" name="phn" id="phn">
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
