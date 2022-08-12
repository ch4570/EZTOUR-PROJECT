<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loginId" value="${sessionScope.usr_id==null ? '' : sessionScope.usr_id}"/>
<c:set var="loginName" value="${sessionScope.usr_nm==null ? '' : sessionScope.usr_nm}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/user/login' : '/user/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<html>
<head>
    <title>유저 테스트</title>
</head>
<body>
<script>
    let msg = "${msg}";
    if(msg=="REG_OK")   alert("회원가입이 완료되었습니다. 로그인 해주세요.");
    if(msg=="DEL_OK")   alert("정상적으로 회원 탈퇴 되셨습니다.");
</script>
<h1> 유저 테스트 메인 </h1><br>
<c:if test="${loginId!=''}">
    <p> ${loginName}님 환영합니다. </p>
</c:if>
<a href="/user/join">회원가입</a>
<a href="<c:url value='${loginOutLink}'/>">${loginOut}</a>
<c:if test="${loginId!=''}">
<a href="/user/usrMod">회원정보수정</a>
</c:if>
</body>
</html>
