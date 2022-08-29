<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="/css/user/user_login.css">
</head>
<body>
<br/>

<div class="outer-content">
    <div class="inner-content" style="display: flex; flex-direction: column; justify-content: flex-start">

        <!-- 일반회원 로그인 폼 시작 -->
        <form id="loginForm" action="<c:url value="/user/login"/>" method="post" onsubmit="return formCheck(this);">
            <div class="login__form" style="display: flex; flex-direction: column; align-items: center">
                    <h2 class="login-title" id="loginTitle">로그인 ${apiJson}</h2>
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
                    <input id="id" type="text" name="usr_id" value="${deCookieId}" placeholder="아이디 입력" autofocus>
                    <div class="msg" id="msg-id"></div>
                    <input id="pwd" type="password" name="pwd" placeholder="비밀번호">
                    <div class="msg" id="msg-pwd"></div>
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
        <!-- 일반회원 로그인 폼 끝 -->

        <!-- 비회원 예약확인 폼 시작 -->
        <form id="guestLoginForm" action="<c:url value='/reserv/gstLogin'/>" method="post" onsubmit="return makePhn(this);" style="">
            <div class="form-inp-nonUsr" style="display: none; flex-direction: column;">
                <input id="rsvt_no" type="text" name="rsvt_no" placeholder="예약번호를 입력하세요" autofocus>
                <div class="msg" id="msg-rsvt_no"></div>
                <input id="rsvt_nm" type="text" name="mn_rsvt_nm" placeholder="이름을 입력하세요">
                <div class="msg" id="msg-rsvt_nm"></div>
                <div class="phn">
                    <input type="hidden" name="phn" id="phn">
                    <input class="rsvt_phn" id="rsvt_phn1" type="text" name="phn1" placeholder="010" style="width: 110px;">
                    <input class="rsvt_phn" id="rsvt_phn2" type="text" name="phn2" placeholder="0000">
                    <input class="rsvt_phn" id="rsvt_phn3" type="text" name="phn3" placeholder="0000" style="margin-right: 0px;">
                </div>
                <div class="msg" id="msg-rxvt-phn"></div>
                <button id="rsvBtn">예약확인</button>
            </div>
        </form>
        <!-- 비회원 예약확인 폼 끝 -->

        <div>
            <div style="display: flex; justify-content: center;">
                <a class="snsIcon" href="${naverUrl}"><img src="../img/user/btnG_아이콘원형.png" alt="" style="width: 70px;"><em>네이버 로그인</em></a>
                <a class="snsIcon"  href="javascript:kakaoLogin()"><img src="../img/user/카카오아이콘.png" alt="" style="width: 70px;"><em>카카오 로그인</em></a>
            </div>
        </div>

    </div>
</div>
    <br/>

<!-- 휴면계정 해제 모달 -->
<div class="modal hidden" id="rstReleaseModal">
    <div class="modal__overlay" id="rstReleaseOverlay"></div>
    <div class="modal__content" style="width: 600px; height: 400px;">
        <br/>
        <div name="pwChkForm" style="display: flex; flex-direction: column; align-items: flex-start;
        justify-content: space-around; width: 550px; height: 350px; padding-left: 40px">
            <h2 style="font-weight: bolder; font-size: x-large; padding-right: 250px">휴면계정 해제 안내</h2>
            <hr>
            <div style="font-size: 18px;">
                안녕하세요!
            </div>
            <div style="font-size: 18px;">
                회원님은 이지투어 계정에 1년 이상 로그인하지 않아
            </div>
            <div style="font-size: 18px;">
                관련 법령에 따라 휴면 상태로 전환되었습니다.
            </div>
            <div style="display: flex; flex-direction: column; justify-content: space-around; background-color: lightgray;width: 500px; height: 70px; text-align: left; margin: 20px 0px; padding: 10px;">
                <div>
                    마지막 접속일 : <fmt:formatDate value="${lst_acc_date}" pattern="yyyy-MM-dd" />
                </div>
                <div>
                    휴면 전환일 :  <fmt:formatDate value="${rst_chg_date}" pattern="yyyy-MM-dd" />
                </div>
            </div>
            <div>
                이지투어 계정 서비스를 계속 이용하시려면
            </div>
            <div>
                <span style="font-weight: bold">[휴면 해제하기]</span> 버튼을 클릭해주세요.
            </div>
            <div style="width: 400px; display: flex; justify-content: space-evenly; align-items: flex-end; margin-left: 60px;">
                <input type="button" id="closeReleaseModal" value="취소">
                <input type="button" id="releaseRstBtn" style="margin-top: 30px; cursor: pointer;" value="휴면 해제하기" onclick="goPost()" >
            </div>
        </div>
    </div>
</div>

<form name="kakaoForm" id="kakaoForm" method = "post" action="/user/setSubInfo">
    <input type="hidden" name="email" id="kakaoEmail" />
    <input type="hidden" name="kakao_id" id="kakaoId" />
    <input type="hidden" name="gndr" id="kakaoGender" />
</form>

<form name="naverForm" id="naverForm" method = "post" action="/user/setNaverConnection">
    <input type="hidden" name="naver_id" id="naver_id" />
    <input type="hidden" name="usr_nm" id="naver_usr_nm" />
    <input type="hidden" name="phn" id="naver_phn" />
</form>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>

    let msg = "${msg}";
    if(msg=="ACC_ERR")  alert("잘못된 접근입니다.");
    if(msg=="LOGIN_FAIL")  alert("아이디와 비밀번호를 다시 확인해주세요.");
    if(msg=="SET_NAVER_ERR")  alert("네이버 아이디 연동 과정에 문제가 생겼습니다.");
    if(msg=="SET_NAVER_OK")  alert("네이버 계정과 성공적으로 연동되었습니다. 다시 로그인해주세요.");
    if(msg=="RLS_OK")  alert("휴면 해제되었습니다. 다시 로그인해주세요.");
    if(msg=="NEW_PWD_OK")  alert("비밀번호가 정상적으로 재설정되었습니다. 다시 로그인해주세요.");

    if(msg=="NAVER_SET_CONFIRM"){
        if (!confirm("이미 존재하는 회원정보입니다. 네이버 아이디와 연동하시겠습니까?")) {
            alert("네이버 아이디 연동이 취소되었습니다.");
        } else {
            console.log("${param.usr_nm}")
            $("#naver_id").val("${param.naver_id}");
            $("#naver_usr_nm").val("${param.usr_nm}");
            $("#naver_phn").val("${param.phn}");
            $("#naverForm").submit();
        }
    }

    window.history.forward();
    $( document ).ready(function() {
        history.replaceState({}, null, location.pathname);
    });
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
                    alert("로그인에 실패했습니다.");
                }
            },
            error: function (xhr, status, error) {
                alert("로그인에 실패했습니다." + error);
                console.log(status);
            }
        });
    }

    function formCheck(frm) {
        let msg ='';
        if(frm.usr_id.value.length==0) {
            setMessage('no-id', frm.id);
            return false;
        }
        if(frm.pwd.value.length==0) {
            setMessage('no-pwd', frm.pwd);
            return false;
        }
        return true;
    }

    function setMessage(msg, element){
        if(msg=='no-id') {
            document.getElementById("msg-id").innerHTML = "아이디를 입력해주세요.";
            document.getElementById("msg-pwd").innerHTML = "";
            $('#id').css('border-color', 'crimson');
            $('#pwd').css('border-color', '#ccc');

        } else if(msg=='no-pwd') {
            document.getElementById("msg-pwd").innerHTML = "비밀번호를 입력해주세요.";
            document.getElementById("msg-id").innerHTML = "";
            $('#id').css('border-color', '#ccc');
            $('#pwd').css('border-color', 'crimson');
        }

        element.select();
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

        document.getElementById('loginForm').reset();
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

        document.getElementById('guestLoginForm').reset();
    }

    function makePhn(frm){
        let phn = $('#rsvt_phn1').val() + $('#rsvt_phn2').val() + $('#rsvt_phn3').val();
        $('input[name=phn]').attr('value',phn);
        alert("내가 만든 핸드폰 번호 :: "+phn);

        if(frm.rsvt_no.value.length==0) {
            setMessageForGst('no-rsvt_no', frm.rsvt_no);
            return false;
        }
        if(frm.mn_rsvt_nm.value.length==0) {
            setMessageForGst('no-rsvt_nm', frm.mn_rsvt_nm);
            return false;
        }
        if(frm.phn1.value.length==0) {
            setMessageForGst('no-phn1', frm.phn1);
            return false;
        }
        if(frm.phn2.value.length==0) {
            setMessageForGst('no-phn2', frm.phn2);
            return false;
        }
        if(frm.phn3.value.length==0) {
            setMessageForGst('no-phn3', frm.phn3);
            return false;
        }
        return true;
    }

    function setMessageForGst(msg, element){
        if(msg=='no-rsvt_no') {
            document.getElementById("msg-rsvt-no").innerHTML = "예약번호를 입력해주세요.";
            document.getElementById("msg-rsvt-nm").innerHTML = "";
            document.getElementById("msg-rsvt-phn").innerHTML = "";
            $('#rsvt_no').css('border-color', 'crimson');
            $('#rsvt_nm').css('border-color', '#ccc');
            $('.rsvt_phn').css('border-color', '#ccc');
        } else if(msg=='no-rsvt_nm') {
            document.getElementById("msg-rsvt-no").innerHTML = "";
            document.getElementById("msg-rsvt-nm").innerHTML = "예약자 성함을 입력해주세요.";
            document.getElementById("msg-rsvt-phn").innerHTML = "";
            $('#rsvt_nm').css('border-color', 'crimson');
            $('#rsvt_no').css('border-color', '#ccc');
            $('.rsvt_phn').css('border-color', '#ccc');
        } else if(msg=='no-phn1' || msg=='no-phn2' || msg=='no-phn3') {
            document.getElementById("msg-rsvt-no").innerHTML = "";
            document.getElementById("msg-rsvt-nm").innerHTML = "";
            document.getElementById("msg-rsvt-phn").innerHTML = "핸드폰 번호를 입력해주세요.";
            $('.rsvt_phn').css('border-color', 'crimson');
            $('#rsvt_nm').css('border-color', '#ccc');
            $('#rsvt_no').css('border-color', '#ccc');
        }
        element.select();
    }

    const rstReleaseModal = document.querySelector("#rstReleaseModal");
    const rstReleaseOverlay = rstReleaseModal.querySelector("#rstReleaseOverlay");
    const openRstReleaseModal = () => {
        rstReleaseModal.classList.remove("hidden");
    }
    const closeRstReleaseModal = () => {
        rstReleaseModal.classList.add("hidden")
    }
    rstReleaseOverlay.addEventListener("click", closeRstReleaseModal);

    $(document).ready(function(){
        if("${param.rstmsg}"==="RST_ERR") {
            openRstReleaseModal();
        }
    });

    function goPost(){
        let f=document.createElement('form');
        f.setAttribute('method','post');
        f.setAttribute('action','/user/rstRelease?usr_id=${usr_id}');
        document.body.appendChild(f);
        f.submit();
    }


</script>
</body>
</html>