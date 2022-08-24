<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Join</title>
  <link rel="stylesheet" href="/css/user/user_join.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
<script>
  let msg = "${msg}";
  if(msg=="REG_ERR")   alert("회원가입 도중 문제가 생겼습니다. 다시 시도해주세요.");
</script>

<h2 class="login-title" id="loginTitle">회원가입</h2>

<br><br>
<div class="form__join" style="display: flex; justify-content: center;">

  <form action="<c:url value="/user/join"/>" method="POST" onsubmit="" accept-charset="utf-8">
    <div style="display: flex; flex-direction: column;">
      <div class="login-subtitle" style="margin-bottom: 20px; width: 540px;">
        <h2 style="font-size: 20px; font-weight: 700; margin-bottom: 10px;">정보입력</h2>
          <p style="font-size: 12px; color: gray"><i class="fa fa-check" aria-hidden="true"></i>  모든 항목은 필수입니다.</p>
          <p style="font-size: 12px; margin-bottom: 20px; color: gray"><i class="fa fa-check" aria-hidden="true"></i>  회원정보는 예약이나 각종 이벤트 참여에 따른 정보 제공에 활용되므로 정확하게 입력해주세요.</p>
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
      <input class="input-field" type="text" name="pwd" id="pwd1" placeholder="6~12자리의 영문자와 숫자 조합"
             onkeyup="noSpaceForm(this); moreThanSixPwd();" onchange="noSpaceForm(this);" style="margin-bottom: 0px;"><br>
        <p id="nonAlterPwd1" style="margin-bottom: 20px; padding: 5px 5px;"> </p>
        <p id="moreThan6Pwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >6자이상 입력해주세요.</p>
    </div>
    <div class="input-pwd">
        <label for="">비밀번호 확인</label><br>
        <input class="input-field" type="text" id="pwd2" placeholder="6~12자리의 영문자와 숫자 조합"
               onkeyup="noSpaceForm(this); checkPwd();" onchange="noSpaceForm(this);" style="margin-bottom: 0px;"><br>
        <p id="nonAlterPwd2" style="margin-bottom: 20px; padding: 5px 5px;"> </p>
        <p id="alertPwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: red" >비밀번호가 일치하지 않습니다.</p>
        <p id="okPwd" style="margin-bottom: 20px; padding: 5px 5px; display: none; color: forestgreen" >비밀번호가 일치합니다.</p>
    </div>
    <div class="input-basic">
       <label for="">이름</label><br>
       <input class="input-field" type="text" name="usr_nm" value="${param.usr_nm}"
             onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" readonly><br>
    </div>
    <div class="input-basic">
        <label for="">핸드폰 번호</label><br>
        <input class="input-field" type="text" name="brth" value="${param.phn}"
               onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" readonly><br>
    </div>
    <div class="input-basic">
       <label for="">생년월일</label><br>
       <input class="input-field" type="text" name="brth" placeholder="20201231"
              onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"><br>
    </div>
    <div class="input-basic">
        <label for="">성별</label><br>
        <div class="select"  style="display: flex; justify-content: space-between;">
        <input type="radio" id="select" name="gndr" value="남성" checked><label for="select" id="man">남성</label>
        <input type="radio" id="select2" name="gndr" value="여성"><label for="select2" id="woman">여성</label>
        </div>
    </div>
    <div class="input-basic">
        <label for="">이메일</label><br>
        <p style="font-size: 12px; color: gray; margin-top: 10px;"><i class="fa fa-check" aria-hidden="true"></i> 이메일을 입력하고 인증하기 버튼을 눌러주세요. </p>
        <p style="font-size: 12px; color: gray"><i class="fa fa-check" aria-hidden="true"></i> 입력하신 이메일에서 인증번호를 확인해주세요. </p>
        <p style="font-size: 12px; color: gray"><i class="fa fa-check" aria-hidden="true"></i> 이메일은 예약 관련 정보 전달과 회원 정보 찾기 등에 이용됩니다.</p>
        <input type="text" class="input-field" name="email1" id="email1"
               onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"placeholder="이메일" style="width:270px; margin-bottom: 0px;" >
        <select class="input-field" name="email2" id="email2" style="width:229px; margin-bottom: 0px;">
            <option>@naver.com</option>
            <option>@daum.net</option>
            <option>@gmail.com</option>
            <option>@hanmail.com</option>
            <option>@yahoo.co.kr</option>
        </select>
        <div>
            <input class="input-field mail-check-input" placeholder="인증번호 6자리를 입력해주세요." style="width:270px;" disabled="disabled" maxlength="6">
            <input type="button" id="mail-Check-Btn" value="이메일 인증">
            <div id="mail-check-warn"></div>
        </div>
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

</script>
</body>
</html>
