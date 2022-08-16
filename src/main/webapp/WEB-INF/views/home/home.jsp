<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.userDto.usr_id==null ? '' : sessionScope.userDto.usr_id}"/>
<c:set var="loginName" value="${loginId=='' ? '' : sessionScope.userDto.usr_nm}"/>
<html>
<head>
	<title>Home</title>
</head>
<body>
<br>
<c:if test="${loginId!=''}">
	<div>
		<span style="font-size: xx-large; color: crimson">${loginName}</span>
		<span>님 환영합니다.</span>
	</div>
</c:if>

<script>
	let msg = "${msg}";
	if(msg=="REG_OK")   alert("회원가입이 완료되었습니다. 로그인 해주세요.");
	if(msg=="DEL_OK")   alert("정상적으로 회원 탈퇴 되셨습니다.");
	if(msg=="GET_ERR")   alert("회원정보를 불러오는데 문제가 생겼습니다.");
	if(msg=="ACC_ERR")   alert("잘못된 접근입니다.");
</script>
</body>
</html>
