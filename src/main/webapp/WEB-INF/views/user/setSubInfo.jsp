<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>SNS 회원가입 추가정보 입력</title>
    <link rel="stylesheet" href="/css/user/user_join.css">
</head>

<body>
<h2 class="login-title" id="loginTitle">추가 정보 입력</h2>

<br><br>
<div class="form__join" style="display: flex; justify-content: center;">

    <form action="<c:url value="/user/join"/>" method="POST" onsubmit="" accept-charset="utf-8">
        <input type="hidden" name="naver_id" value="${naver_id}">
        <input type="hidden" name="kakao_id" value="${kakao_id}">
        <div style="display: flex; flex-direction: column;">
            <div class="login-subtitle" style="margin-bottom: 20px; width: 540px;">
                <h2 style="font-size: 20px; font-weight: 700; margin-bottom: 10px;">정보입력</h2>
                <p style="font-size: 12px; margin-bottom: 20px; color: gray">회원정보는 예약이나 각종 이벤트 참여에 따른 정보 제공에 활용되므로 정확하게 입력해주세요.</p>
                <hr>
            </div>
            <div class="input-id">
                <label for="">아이디</label><br>
                <input class="input-field" type="text" name="usr_id" id="usr_id" placeholder="6~12자리의 영문자와 숫자 조합"
                       onkeyup="noSpaceForm(this); moreThanSixId();" onchange="noSpaceForm(this);" style="margin-bottom: 0px;"><br>
                <p id="moreThan6Id" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >6자이상 입력해주세요.</p>
                <p id="usable" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: forestgreen" >사용 가능한 아이디입니다.</p>
                <p id="unusable" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >중복된 아이디입니다.</p>
                <p id="nonAlterId" style="margin-bottom: 20px; padding: 5px 5px;"> </p>
            </div>
            <div class="input-pwd">
                <label for="">비밀번호</label><br>
                <input class="input-field" type="text" name="pwd" id="pwd1" placeholder="6~12자리의 영대소문자와 숫자 조합"
                       onkeyup="noSpaceForm(this); moreThanSixPwd();" onchange="noSpaceForm(this);" style="margin-bottom: 0px;"><br>
                <p id="nonAlterPwd1" style="margin-bottom: 20px; padding: 5px 5px;"> </p>
                <p id="moreThan6Pwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >6자이상 입력해주세요.</p>
            </div>
            <div class="input-pwd">
                <label for="">비밀번호 확인</label><br>
                <input class="input-field" type="text" id="pwd2" placeholder="6~12자리의 영대소문자와 숫자 조합"
                       onkeyup="noSpaceForm(this); checkPwd();" onchange="noSpaceForm(this);" style="margin-bottom: 0px;"><br>
                <p id="nonAlterPwd2" style="margin-bottom: 20px; padding: 5px 5px;"> </p>
                <p id="alertPwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >비밀번호가 일치하지 않습니다.</p>
                <p id="okPwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: forestgreen" >비밀번호가 일치합니다.</p>
            </div>
            <div class="input-basic">
                <label for="">이름</label><br>
                <c:if test="${empty kakao_id}">
                <input class="input-field" type="text" name="usr_nm" value="${usr_nm}" readonly><br>
                </c:if>
                <c:if test="${not empty kakao_id}">
                    <input class="input-field" type="text" name="usr_nm" value="" placeholder="이름을 입력해주세요" ><br>
                </c:if>

            </div>
            <div class="input-basic">
                <label for="">핸드폰 번호</label><br>
                <c:if test="${empty kakao_id}">
                <input class="input-field" type="text" name="phn" value="${phn}" readonly><br>
                </c:if>
                <c:if test="${not empty kakao_id}">
                    <input class="input-field" type="text" name="phn" value="" placeholder="'-'를 빼고 입력해주세요"><br>
                </c:if>
            </div>
            <div class="input-basic">
                <label for="">생년월일</label><br>
                <c:if test="${empty kakao_id}">
                <input class="input-field" type="text" name="brth" value="${brth}" readonly><br>
                </c:if>
                <c:if test="${not empty kakao_id}">
                <input class="input-field" type="text" name="brth" value="" placeholder="숫자만 입력해주세요. ex) 20220819"><br>
                </c:if>
            </div>

            <c:set var="m" value="${kakao_id!=null? 'male':'M'}"/>
            <c:set var="f" value="${kakao_id!=null? 'female':'F'}"/>
            <div class="input-basic">
                <label for="">성별</label><br>
                <div class="select"  style="display: flex; justify-content: space-between;">
                    <input type="radio" id="select" name="gndr" value="${m}" ${not empty kakao_id? '' : "onclick='return(false);'"}><label for="select" id="man">남성</label>
                    <input type="radio" id="select2" name="gndr" value="${f}" ${not empty kakao_id? '' : "onclick='return(false);'"}><label for="select2" id="woman">여성</label>
                </div>
            </div
            <div class="input-basic">
                <label for="">이메일</label><br>
                <input type="text" class="input-field" name="email" id="email" value="${email}" >
            </div>

            <div style="text-align: center">
                <input type="button" id="goback" onclick="history.back()" value="취소">
                <button id="submit">확인</button>
            </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    window.history.forward();

    <!-- 공백 사용 방지 -->
    function noSpaceForm(obj) {
        var str_space = /\s/;  // 공백체크
        if (str_space.exec(obj.value)) { //공백 체크
            obj.focus();
            obj.value = obj.value.replace(' ', '');
            return false;
        }
    }

    function moreThanSixId(){
        var idLength = $("#usr_id").val().length;

        if(idLength < 6){
            $("#moreThan6Id").show();
            $("#nonAlterId").hide();
            $("#usable").hide();
            $("#unusable").hide();
            $('#usr_id').css({"border-color": "red"});
            return false;
        }else{
            $("#moreThan6Id").hide();
            return true;
        }
    }

    function moreThanSixPwd(){
        var pwdLength = $("#pwd1").val().length;
        if(pwdLength < 6){
            $("#moreThan6Pwd").show();
            $("#nonAlterPwd1").hide();
            return false;
        }else{
            $("#moreThan6Pwd").hide();
            $("#nonAlterPwd1").show();
            return true;
        }
    }

    function checkPwd() {
        var pwdLength = $("#pwd1").val().length;
        var p1 = document.getElementById('pwd1').value;
        var p2 = document.getElementById('pwd2').value;
        if(pwdLength > 5) {
            if (p1 != p2) {
                $("#moreThan6Pwd").hide();
                $("#alertPwd").show();
                $("#nonAlterPwd2").hide();
                $("#okPwd").hide();
                $('#pwd1').css({"border-color": "red"});
                $('#pwd2').css({"border-color": "red"});
                return false;
            } else {
                $("#moreThan6Pwd").hide();
                $("#alertPwd").hide();
                $("#nonAlterPwd2").hide();
                $("#okPwd").show();
                $('#pwd1').css({"border-color": "forestgreen"});
                $('#pwd2').css({"border-color": "forestgreen"});
                return true;
            }
        }
    }
    $(document).ready(function(){
        $("#usr_id").keyup(function(){
            var idLength = $("#usr_id").val().length
            if(idLength>5) {
                let usr_id = $('#usr_id').val();
                let uri = "/checkId/" + usr_id
                $.get(uri, function (response) {
                    if(response=='usable'){
                        $("#usable").show();
                        $("#unusable").hide();
                        $("#nonAlterId").hide();
                        $('#usr_id').css({"border-color": "forestgreen"});
                    }else{
                        $("#usable").hide();
                        $("#unusable").show();
                        $("#nonAlterId").hide();
                        $('#usr_id').css({"border-color": "red"});

                    }
                });
            }

        });
    });

    $('#mail-Check-Btn').click(function() {
        const email = $('#email1').val() + $('#email2').val(); // 이메일 주소값 얻어오기!
        console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
        const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

        $.ajax({
            type : 'get',
            url : '<c:url value ="/mailCheck?email="/>'+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
            success : function (data) {
                console.log("data : " +  data);
                checkInput.attr('disabled',false);
                code =data;
                alert('인증번호가 전송되었습니다.');
            }
        });
    });

    $('.mail-check-input').blur(function () {
        const inputCode = $(this).val();
        const $resultMsg = $('#mail-check-warn');

        if(inputCode === code){
            $resultMsg.html('인증번호가 일치합니다.');
            $resultMsg.css('color','green');
            $('#mail-Check-Btn').attr('disabled',true);
            $('#email1').attr('readonly',true);
            $('#email2').attr('readonly',true);
            $('#email2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
            $('#email2').attr('onChange', 'this.selectedIndex = this.initialSelect');
        }else{
            $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
            $resultMsg.css('color','red');
        }
    });

    $.fn.radioSelect = function(val) {
        this.each(function() {
            var $this = $(this);
            if($this.val() == val)
                $this.attr('checked', true);
        });
        return this;
    };

    $(":radio[name='gndr']").radioSelect('${gndr}');

</script>
</body>
</html>
