<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/rvw/menu.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">작성하기</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>
<script>
    let msg="${msg}";
    if(msg=="RVW_REGISTER_ERR") alert("여행 지역, 제목, 내용 하나라도 비어 있으면 등록할 수 없어요. 확인해주세요.");
    if(msg=="WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 시도해주세요.");
</script>
<div style="text-align:center">
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
    <h1>This is HOME</h1>
</div>
<div style="text-align:center">
    <h2> 후기글 등록 or 후기글 수정 </h2>
    <form action="" id="form">
        <input type="hidden" name="rvw_no" value="${rvwDto.rvw_no}" readonly="readonly">
        <input type="hidden" name="usr_id" value="${rvwDto.usr_id}" readonly="readonly">
        <input type="text" name="wrt_nm" value="${rvwDto.wrt_nm}" readonly="readonly">작성자<br>
        <input type="text" name="wrt_email" value="${rvwDto.wrt_email}" readonly="readonly">이메일<br>
        <select name="prd_dtl_cd">
            <option value="">선택해주세요</option>
                <c:forEach var="rvwDto" items="${list}">
                    <option value="${rvwDto.prd_dtl_cd}">${rvwDto.prd_nm}</option>
                </c:forEach>
        </select>
        <input type="text" name="rvw_ttl" value="${rvwDto.rvw_ttl}" placeholder="리뷰 제목">제목<br>
        <input type="text" name="rvw_cont" value="${rvwDto.rvw_cont}" placeholder="리뷰 내용">내용<br>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
    </form>
</div>
<script>
    $(document).ready(function (){
        $('#writeBtn').on("click", function(){
            let form = $('#form');
            form.attr("action", "<c:url value='/review/write'/>");
            form.attr("method", "post");
            form.submit();
        });
        $('#modifyBtn').on("click", function(){
            let form = $('#form');
            form.attr("action", "<c:url value='/review/modify'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
</body>
</html>