<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="/css/user/user_login.css">
</head>
<body>
<br/>
<script>
    let msg = "${msg}";
    if(msg=="ACC_ERR")  alert("잘못된 접근입니다.");
</script>

<div class="outer-content">
    <div class="inner-content" style="display: flex; flex-direction: column; justify-content: flex-start">
        <form action="<c:url value="/user/login"/>" method="post" onsubmit="return formCheck(this);">
            <div class="login__form" style="display: flex; flex-direction: column; align-items: center">
                    <h2 class="login-title" id="loginTitle">로그인</h2>
                    <h2 class="login-title" id="rsvTitle" style="display: none">예약확인</h2>
                <nav class="login-nav">
                    <div id="msg">
                        <c:if test="${not empty param.msg}">
                            <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
                        </c:if>
                    </div>
                    <div style="display: flex;">
                        <button  type="button" id="usrBt" onclick="usrLogin()" >회원</button>
                        <button  type="button" id="nonUsrBt" onclick="nonUsrAuth()" >비회원 예약확인</button>
                    </div>
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
                            <a href="<c:url value='/user/selectJoin'/>" style="font-weight: bolder;">회원가입</a>
                        </span>
                    </div>
                    <button id="loginBtn">로그인</button>
                </div>
            </div>
        </form>
        <form style="display: flex; flex-direction: column; align-items: center">
            <div class="form-inp-nonUsr" style="display: none; flex-direction: column;">

                <input id="rsvNo" type="text" name="rsvt_no" placeholder="예약번호를 입력하세요" autofocus>
                <input id="rsvName" type="password" name="mn_rsvt_nm" placeholder="이름을 입력하세요">
                <div class="phn">
                    <input id="rsvPhone1" type="text" name="phn1" placeholder="010" style="width: 110px;">
                    <input id="rsvPhone2" type="text" name="phn2" placeholder="0000">
                    <input id="rsvPhone3" type="text" name="phn3" placeholder="0000" style="margin-right: 0px;">
                </div>
                <button id="rsvBtn">예약확인</button>
            </div>
            <div>
                <div style="display: flex; justify-content: center;">
                    <a style="height: 100px; padding: 0px 20px; display: flex; flex-direction: column; align-items: center; justify-content: space-between" href="${naverUrl}"><img src="../img/user/btnG_아이콘원형.png" alt="" style="width: 70px;"><em>네이버 로그인</em></a>
                    <a style="height: 100px; padding: 0px 20px; display: flex; flex-direction: column; align-items: center; justify-content: space-between" href="javascript:kakaoLogin()"><img src="../img/user/카카오아이콘.png" alt="" style="width: 70px;"><em>카카오 로그인</em></a>
                </div>
            </div>
        </form>
    </div>
</div>
    <br/>
<form name="kakaoForm" id="kakaoForm" method = "post" action="/user/setSubInfo">
    <input type="hidden" name="email" id="kakaoEmail" />
    <input type="hidden" name="kakao_id" id="kakaoId" />
    <input type="hidden" name="gndr" id="kakaoGender" />
</form>


<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    <!-- 카카오 로그인 init -->
    $(document).ready(function(){
        $.ajax({
            type:'POST',
            url : '/api/getKakaoApi',
            data : {},
            dataType : 'text',
            success : function(data){
                Kakao.init(data);
            },
            error : function(xhr, status, error){
                alert("API 등록에 실패했습니다." +error);
            }
        });
    });

    <!-- 카카오 로그인 -->
    function kakaoLogin() {
        Kakao.Auth.login({
            success: function (response) {
                Kakao.API.request({
                    url: '/v2/user/me',
                    success: function (response) {
                        console.log(response);
                        kakaoLoginPro(response)
                    },
                    fail: function (error) {
                        console.log(error)
                    },
                })
            },
            fail: function (error) {
                console.log(error)
            },
        })
    }

    <!--카카오 로그인 데이터 받기-->
    function kakaoLoginPro(response) {
        var data = {kakao_id: response.id, email: response.kakao_account.email, gndr:response.kakao_account.gender}
        $.ajax({
            type: 'POST',
            url: '/api/kakaoLoginPro',
            data: data,
            dataType: 'json',
            success: function (data) {
                console.log(data)
                if (data.JavaData == "login") {
                    alert("카카오 계정으로 로그인되었습니다.");
                    if(data.toURL != null) {
                        location.href = data.toURL;
                    }else{
                        location.href = '/';
                    }
                } else if (data.JavaData == "register") {
                    $("#kakaoEmail").val(response.kakao_account.email);
                    $("#kakaoId").val(response.id);
                    $("#kakaoGender").val(response.kakao_account.gender);
                    $("#kakaoForm").submit();
                } else {
                    alert("로그인에 실패했습니다");
                }
            },
            error: function (xhr, status, error) {
                alert("로그인에 실패했습니다." + error);
            }
        });
    }

    function formCheck(frm) {
        let msg ='';
        if(frm.usr_id.value.length==0) {
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

        $(".form-inp-nonUsr").css({
            'display': 'flex'
        })
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
