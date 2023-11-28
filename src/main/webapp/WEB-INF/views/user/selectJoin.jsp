<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>회원가입</title>
<link rel="stylesheet" href="/css/user/user_selectJoin.css">
</head>
<body>
<div class="form-user-selectjoin">
    <div>
        <div class="form-selectjoin-content">
            <h2 class="login-title" id="loginTitle">회원가입</h2>

            <div class="form-selectjoin-general">
                <div style="width: 540px;">
                    <h3>이지투어 회원가입</h3>
                    <div style="font-size: 15px; margin-top: 5px;"> 현금처럼 사용할 수 있는 마일리지 적립, 다양한 혜택</div>
                </div>
                <div style="display: flex; justify-content: center;"><button id="toJoin" style="cursor: pointer;" onclick="location.href='<c:out value="/user/auth"/>'">회원가입</button></div>
            </div>

            <div class="form-selectjoin-sns">
                <div style="width: 540px;">
                    <h3>SNS 회원가입</h3>
                    <div style="font-size: 15px; margin-top: 5px;">기존 사용 계정으로 간편하게 가입하세요</div>
                </div>

                    <div style="display: flex; justify-content: center;">
                        <a class="snsIcon" href="${naverUrl}"><img src="../img/user/btnG_아이콘원형.png" alt="" style="width: 70px;"><em>네이버 회원가입</em></a>
                        <a class="snsIcon" href="javascript:kakaoLogin()"><img src="../img/user/카카오아이콘.png" alt="" style="width: 70px;"><em>카카오 회원가입</em></a>
                    </div>
                <div style="margin-right: 80px; font-size: 15px;"><i class="fa fa-exclamation-circle" aria-hidden="true" style="color: crimson"></i> SNS 회원가입시 추가정보를 반드시 기입해야 가입 완료됩니다.</div>
            </div>

        </div>
    </div>
</div>



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
</script>
</body>
</html>
