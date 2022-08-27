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
<div style="text-align:center">
    <h2>후기글 읽기</h2>
    <form action="" id="form">
        <input type="hidden" name="rvw_no" value="${rvwDto.rvw_no}" readonly="readonly">
        <input type="text" name="rvw_ttl" value="${rvwDto.rvw_ttl}" readonly="readonly">제목<br>
        <input type="text" name="lk_cnt" value="${rvwDto.lk_cnt}" readonly="readonly">좋아요수<br>
        <input type="text" name="wrt_nm" value="${rvwDto.wrt_nm}" readonly="readonly">작성자<br>
        <input type="text" name="rvw_reg_date" value="${rvwDto.rvw_reg_date}" readonly="readonly">등록일<br>
        <input type="text" name="rvw_vcnt" value="${rvwDto.rvw_vcnt}" readonly="readonly">조회수<br>
        <input type="text" name="rvw_cont" value="${rvwDto.rvw_cont}" readonly="readonly">내용<br>
        <input type="text" name="img_pth" value="${rvwDto.img_pth}" readonly="readonly">이미지<br>
        <input type="text" name="prd_nm" value="${rvwDto.prd_nm}" readonly="readonly">상품명<br>
        <c:if test="${check.equals('me')}">
            <button type="button" id="modifyBtn" class="btn">수정</button>
            <button type="button" id="removeBtn" class="btn">삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    $(document).ready(function (){
        // 좋아요가 있는지 확인한 값을 heartval에 저장
        <%--var heartval = "${rvwLkAdmDto.rvw_lk_yn}"--%>
        // heartval이 1이면 좋아요가 이미 되있는것이므로 full_heart_icon.svg를 출력하는 코드
        // if(heartval == "1") {
        //     console.log(heartval);
        // $("#heart").prop("src", "/icon/full_heart_icon.svg");
        // }
        // else if(heartval == "0" || heartval == ""){
        //     console.log(heartval);
        // $("#heart").prop("src", "/icon/empty_heart_icon.svg");
        // }

        // 좋아요 버튼을 클릭 시 실행되는 코드
        var heartval = "${rvwLkAdmDto.rvw_lk_yn}"

        $('i[name=fill-heart]').on("click", function () {
            if(heartval == ""){
                alert("로그인을 해야 후기에 좋아요 버튼를 누를 수 있습니다.");
                var confirmUser = confirm("로그인 하시겠습니까?");
                if(confirmUser){
                    location.href = "<c:url value='/user/login'/>"
                }else{
                    return;
                }
            }
            $.ajax({
                url : '/heart',
                type : 'POST',
                // headers : {"content-type": "application/json"}, // 요청 헤더
                <%--data : JSON.stringify({'rvw_no': ${rvwDto.rvw_no}}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.--%>
                data : {'rvw_no': "${rvwDto.rvw_no}"},
                success : function (data){
                    if(data=="HeartUp") {
                        $('#heart').prop("src", "/icon/full_heart_icon.svg");
                        location.reload();
                    }
                    else {
                        $('#heart').prop("src", "/icon/empty_heart_icon.svg");
                        location.reload();
                    }
                }

            });
        });

        $('#listBtn').on("click", function(){
            alert("listBtn clicked")
            location.href = "<c:url value='/review/list${searchCondition.queryString}'/>";
        });
        $('#removeBtn').on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/review/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
        $('#modifyBtn').on("click", function(){
            alert("modifyBtn clicked")
            location.href = "<c:url value='/review/modify${searchCondition.queryString}&rvw_no=${rvwDto.rvw_no}'/>";
        });
    });
</script>
</body>
</html>