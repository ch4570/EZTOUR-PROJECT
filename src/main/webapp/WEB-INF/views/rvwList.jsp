<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/rvw/menu.css'/>">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/review/review'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<script>
    let msg = "${param.msg}"
    if(msg=="DEL_OK") alert("성공적으로 삭제되었습니다.");
    if(msg=="DEL_ERR") alert("삭제에 실패했습니다.");
</script>
<h1>총 게시물 ${totalCnt}건</h1>
<div style="text-align:center">
    <button type="button" id="writeBtn" onclick="location.href='<c:url value="/review/write"/>'">작성하기</button>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>이미지</th>
            <th>등록일</th>
            <th>조회수</th>
            <th>좋아요</th>
        </tr>
        <c:forEach var="rvwDto" items="${list}">
        <tr>
            <td>${rvwDto.rvw_no}</td>
            <td><a href="<c:url value='/review/read?rvw_no=${rvwDto.rvw_no}&page=${page}&pageSize=${pageSize}'/>">${rvwDto.rvw_ttl}</a></td>
            <td>${rvwDto.rvw_cont}</td>
            <td>${rvwDto.wrt_nm}</td>
            <td>${rvwDto.img_pth}</td>
            <td>${rvwDto.rvw_reg_date}</td>
            <td>${rvwDto.rvw_vcnt}</td>
            <td>${rvwDto.lk_cnt}</td>
        </tr>
        </c:forEach>
    </table>
    <br>
    <div>
       <c:if test="${ph.showPrev}">
           <a href="<c:url value='/review/list?page=${ph.firstPage-1}"&pageSize=${ph.pageSize}'/>">&lt;</a>
       </c:if>
       <c:forEach var="i" begin="${ph.firstPage}" end="${ph.endPage}">
           <a href="<c:url value='/review/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
       </c:forEach>
       <c:if test="${ph.showNext}">
           <a href="<c:url value='/review/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
       </c:if>
    </div>
</div>
</body>
</html>