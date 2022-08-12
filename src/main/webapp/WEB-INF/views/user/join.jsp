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
  </style>
</head>
<body>
<script>
  let msg = "${msg}";
  if(msg=="REG_ERROR")   alert("회원가입 도중 문제가 생겼습니다. 다시 시도해주세요.");
</script>
<h1>
  회원가입 공사중 ...
</h1>
<br><br>
<form action="<c:url value="/user/join"/>" method="POST" onsubmit="" accept-charset="utf-8">
  <label for="">아이디</label>
  <input class="input-field" type="text" name="usr_id" placeholder="8~12자리의 영대소문자와 숫자 조합"><br>
  <lael for="">비밀번호</lael>
  <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합"><br>
  <label for="">이름</label>
  <input class="input-field" type="text" name="usr_nm" placeholder="홍길동"><br>
  <label for="">생년월일</label>
  <input class="input-field" type="text" name="brth" placeholder="20201231"><br>
  <label for="">성별</label>
  <div class="select">
    <input type="radio" id="select" name="gndr"><label for="select">남성</label>
    <input type="radio" id="select2" name="gndr"><label for="select2">여성</label>
  </div>
  <label for="">이메일</label>
  <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr"><br>
  <label for="">핸드폰</label>
  <input class="input-field" type="text" name="phn" placeholder="010-0000-0000"><br>
  <button>회원 가입</button>
</form>
</body>
</html>
