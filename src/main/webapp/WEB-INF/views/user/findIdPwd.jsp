<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>아이디/비밀번호 찾기</title>
<link rel="stylesheet" href="/css/user/user_find.css">
</head>
<body>

<br/>
<div class="outer-content">
    <div class="inner-content">
            <div class="find__form">
                <h2 class="find-title" id="findIdTitle">아이디 찾기</h2>
                <h2 class="find-title" id="findPwdTitle" style="display: none">비밀번호 찾기</h2>

                <nav class="find-nav">
                    <button  type="button" id="findId" onclick="findIdView()" >아이디 찾기</button>
                    <button  type="button" id="findPwd" onclick="findPwdView()" >비밀번호 찾기</button>
                </nav>

                <div class="form-inp">
                    <input id="usr_nm" type="text" name="usr_nm"  placeholder="이름 입력" >
                    <input id="phn" name="phn" placeholder="핸드폰 번호 입력">
                    <input class="hidden" type="button" id="checkAuthBtn" value="인증하기">
                    <button id="findIdBtn" onclick="authPhn()">인증번호 보내기</button>
                </div>

                <div class="form-inp-find" style="display:none;">
                    <input id="rsvNo" type="text" name="rsvNo" placeholder="예약번호를 입력하세요" autofocus>
                    <input id="rsvName" type="password" name="rsvNm" placeholder="이름을 입력하세요">
                    <div class="phn">
                        <input id="rsvPhone1" type="text" name="phn1" placeholder="010" style="width: 110px;">
                        <input id="rsvPhone2" type="text" name="phn2" placeholder="0000">
                        <input id="rsvPhone3" type="text" name="phn3" placeholder="0000" style="margin-right: 0px;">
                    </div>
                    <button id="findPwdBtn">예약확인</button>
                </div>
                <input type="hidden" name="toURL" value="${param.toURL}">
            </div>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    function findPwdView(){
        $(".form-inp").hide();
        $("#findTitle").hide();
        $(".form-inp-find").show();
        $("#rsvTitle").show();
        $("#findPwd").css({
            'border-bottom': '3px solid #333333',
            'color' : '#333333'
        });

        $("#findId").css({
            'border-bottom': '3px solid #E6E6E6',
            'color' : 'gray'
        });
    }

    function findIdView() {
        $("#rsvTitle").hide();
        $(".form-inp-find").hide();
        $(".form-inp").show();
        $("#findTitle").show();
        $("#findId").css({
            'border-bottom': '3px solid #333333',
            'color': '#333333'
        });

        $("#findPwd").css({
            'border-bottom': '3px solid #E6E6E6',
            'color': 'gray'
        });
    }

    function authPhn(){
        let phn = $("#phn").val()
        const findIdBtn = document.querySelector("#findIdBtn");
        const checkAuthBtn = document.querySelector("#checkAuthBtn");
        $.ajax({
            type:'GET',
            url: '/authPhn/' + phn,
            success : function(msg){
                alert(msg);
                $("#findIdBtn").before("<input id='checkNum' name='checkNum' placeholder='인증번호를 입력해주세요.'>");
                findIdBtn.classList.add("hidden")
                checkAuthBtn.classList.remove("hidden")
            },
            error   : function(){ alert("잘못된 요청입니다.") }
        });
    }

    $("#checkAuthBtn").click(function(){
        let checkNum = $("#checkNum").val();
        let phn = $("#phn").val();
        let usr_nm = $("#usr_nm").val();

        $.ajax({
            type:'GET',
            url: '/checkAuthNum/' + checkNum,
            success : function(msg){
                alert(msg);

                $.ajax({
                    type:'POST',
                    url: '/findId/' +usr_nm+ "/" +phn,
                    success : function(usr_id){
                        alert(usr_id);
                    },
                    error   : function(){ alert("존재하지 않는 사용자입니다.") }
                });
            },
            error   : function(){ alert("인증번호가 일치하지 않습니다.") }
        });
    });

</script>
</body>
</html>
