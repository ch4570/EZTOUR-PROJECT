<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="/css/user/user_login.css">

</head>
<body>
<br/>
<div class="outer-content">
    <div class="inner-content">
<form action="<c:url value="/user/login"/>" method="post" onsubmit="return formCheck(this);">
    <div class="login__form">
        <h2 class="login-title" id="loginTitle">로그인</h2>
        <h2 class="login-title" id="rsvTitle" style="display: none">예약확인</h2>

        <nav class="login-nav">
            <button  type="button" id="usrBt" onclick="usrLogin()" >회원</button>
            <button  type="button" id="nonUsrBt" onclick="nonUsrAuth()" >비회원 예약확인</button>
        </nav>

        <div class="form-inp">
            <input id="id" type="text" name="usr_id" value="${cookie.id.value}" placeholder="아이디 입력" autofocus>
            <input id="pwd" type="password" name="pwd" placeholder="비밀번호">

            <div class="panel-box">
                <span class="remember">
                    <label><input id="idChk" type="checkbox" name="rememberId" value="on" ${empty cookie.id.value ? "":"checked"}></label> <label>아이디 저장</label>
                </span>
                <span>
                <a href="<c:url value='/user/findIdPwd'/>">아이디 찾기 | 비밀번호 찾기 | </a>
                    <a href="<c:url value='/user/join'/>" style="font-weight: bolder;">회원가입</a>
                </span>
            </div>
            <button id="loginBtn">로그인</button>
        </div>

        <div class="form-inp-nonUsr" style="display:none;">
            <input id="rsvNo" type="text" name="rsvNo" placeholder="예약번호를 입력하세요" autofocus>
            <input id="rsvName" type="password" name="rsvNm" placeholder="이름을 입력하세요">
            <div class="phn">
                <input id="rsvPhone1" type="text" name="phn1" placeholder="010" style="width: 110px;">
                <input id="rsvPhone2" type="text" name="phn2" placeholder="0000">
                <input id="rsvPhone3" type="text" name="phn3" placeholder="0000" style="margin-right: 0px;">
            </div>
            <button id="rsvBtn">예약확인</button>
        </div>
        <input type="hidden" name="toURL" value="${param.toURL}">
    </div>
</form>
</div>
</div>

    <br/><br/><br/>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function formCheck(frm) {
            let msg ='';
            if(frm.id.value.length==0) {
                setMessage('id를 입력해주세요.', frm.id);
                return false;
            }
            if(frm.pwd.value.length==0) {
                setMessage('password를 입력해주세요.', frm.pwd);
                return false;
            }
            return true;
        }
        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
            if(element) {
                element.select();
            }
        }

        function nonUsrAuth(){
            $(".form-inp").hide();
            $("#loginTitle").hide();
            $(".form-inp-nonUsr").show();
            $("#rsvTitle").show();
            $("#nonUsrBt").css({
                'border-bottom': '3px solid #333333',
                'color' : '#333333'
            });

            $("#usrBt").css({
                'border-bottom': '3px solid #E6E6E6',
                'color' : 'gray'
            });
        }

        function usrLogin(){
            $("#rsvTitle").hide();
            $(".form-inp-nonUsr").hide();
            $(".form-inp").show();
            $("#loginTitle").show();
            $("#usrBt").css({
                'border-bottom': '3px solid #333333',
                'color' : '#333333'
            });

            $("#nonUsrBt").css({
                'border-bottom': '3px solid #E6E6E6',
                'color' : 'gray'
            });

        }

    </script>
</body>
</html>
