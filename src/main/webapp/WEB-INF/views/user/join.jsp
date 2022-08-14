<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>Join</title>

  <style>
    .select {
      padding: 15px 10px;
    }
    .select input[type=radio]{
      display: none;
    }
    .select input[type=radio]+label{
      display: inline-block;
      cursor: pointer;
      height: 24px;
      width: 90px;
      border: 1px solid #333;
      line-height: 24px;
      text-align: center;
      font-weight:bold;
      font-size:13px;
    }
    .select input[type=radio]+label{
      background-color: #fff;
      color: #333;
    }
    .select input[type=radio]:checked+label{
      background-color: #333;
      color: #fff;
    }


    .input-field{
      width: 500px;
      font-size: 15px;
      font-weight: 400;
      line-height: 30px;
      outline: none;
      border: 1px solid #e5e5e5;
      border-radius: 2px;
      padding: 0px 20px;
      min-height: 50px;
      margin-bottom: 20px;
      margin-top: 10px;
    }

    #man, #woman{
      width: 265px;
      height: 40px;
      line-height: 40px;
      text-align: center;
    }

    .login-title {
      display: flex;
      justify-content: center;
      margin-top: 100px;
      margin-bottom: 30px;
      font-size: 30px;
      font-weight: 800;
    }

    #goback, #submit{
      width: 120px;
      height: 30px;
      margin: 20px 7px;
      background-color: #333333;
      color: white;
    }

  </style>
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
        <p style="font-size: 12px; margin-bottom: 20px; color: gray">회원정보는 예약이나 각종 이벤트 참여에 따른 정보 제공에 활용되므로 정확하게 입력해주세요.</p>
        <hr>
      </div>
    <div>
      <label for="">아이디</label><br>
      <input class="input-field" type="text" name="usr_id" placeholder="8~12자리의 영대소문자와 숫자 조합" oninput="checkId()"><br>
    </div>
    <div>
      <lael for="">비밀번호</lael><br>
      <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합"><br>
    </div>
    <div>
      <label for="">이름</label><br>
      <input class="input-field" type="text" name="usr_nm" placeholder="홍길동"><br>
    </div>
    <div>
       <label for="">생년월일</label><br>
       <input class="input-field" type="text" name="brth" placeholder="20201231"><br>
    </div>
    <div>
        <label for="">성별</label><br>
        <div class="select"  style="display: flex; justify-content: space-between; padding-left: 0px; padding-right: 0px;">
        <input type="radio" id="select" name="gndr" value="남성" checked><label for="select" id="man">남성</label>
        <input type="radio" id="select2" name="gndr" value="여성"><label for="select2" id="woman">여성</label>
        </div>
    </div>
    <div>
    <label for="">이메일</label><br>
    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr"><br>
    </div>
    <div>
    <label for="">핸드폰</label><br>
    <input class="input-field" type="text" name="phn" placeholder="010-0000-0000"><br>
    </div>
    <div style="text-align: center">
      <input type="button" id="goback" onclick="history.back()" value="취소">
      <button id="submit">확인</button>
    </div>
    </div>
  </form>
</div>

<script>
  function checkId(){


  }
</script>
</body>
</html>
