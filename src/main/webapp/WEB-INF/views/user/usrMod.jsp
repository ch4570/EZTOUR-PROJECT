<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>회원정보수정</title>
</head>
<body>
<h1>
    회원정보수정 공사중 ...
</h1>
<br><br>
<form action="<c:url value="/user/usrMod"/>" method="POST" onsubmit="">
    <label for="">아이디</label>
    <input class="input-field" type="text" name="usr_id" value="<c:out value='${userDto}'/>" readonly><br>
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" value="${userDto.pwd}"><br>
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
    <input class="input-field" type="text" name="phn" placeholder=""><br>

    <button>수정</button>

</form>
<a>회원탈퇴</a>
</body>
</html>
