<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>
<html>
<head>
    <title>My Page</title>
</head>
<body>
  <div>
    <span style="font-size: xx-large; color: crimson">${loginName}</span>
    <span>님 환영합니다.</span>
  </div>

  <br>
  <br>
  <a href="/user/usrMod">회원정보수정(클릭)</a>

</body>
</html>
