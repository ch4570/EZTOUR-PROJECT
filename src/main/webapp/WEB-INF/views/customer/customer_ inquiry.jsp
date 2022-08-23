<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>EZTour</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="board">
    <h1> 1:1 문의 </h1>
    <div class="customer-inquiry">
        <h2>확인사항</h2>
        <ul>
            <li>패키지상품 관련문의는 예약문의 > 해외패키지 > 지역선택으로 올려주세요.</li>
            <li>항공권 문의는 마이페이지 > 항공권예약조회 > 예약번호 클릭 > 1:1 문의하기 또는 구입한 해당사이트에서 (G마켓, 옥션, 11번가 등) 문의해주세요.</li>
            <li>해당 서비스는 회원 로그인 후 작성이 가능하며 모든 게시물은 비공개로 운영됩니다.</li>
        </ul>
        <form id="form">
            <p>제목</p>
            <input type="hidden" name="usr_id" value="${sessionScope.userDto.usr_id}"/>
            <input type="hidden" name="email" value="${sessionScope.userDto.email}"/>
            <input type="hidden" name="phn" value="${sessionScope.userDto.phn}"/>

            <input type="text" name="prop_ttl" placeholder="내용을 입력해주세요"/><br>
            <p>내용</p>
            <textarea name="prop_cont" cols="30" rows="50" placeholder="내용을 입력해주세요"></textarea><br>
            <button type="button" id="cancel_btn" class="Btn"> 취소</button>
            <button type="button" id="submit_btn" class="Btn"> 등록</button>
            <h2>이용안내</h2>
            <ul>
                <li>개인의 신상정보(주민번호, 여권번호, 연락처 등)는 가급적 기재를 삼가해 주시기 바랍니다</li>
                <li>다음에 해당되는 게시물들에 대해서는 게시판 등록을 금하며, 사전통지 없이 게시물을 수정 또는 삭제할 수 있습니다.</li>
            </ul>
            <p>-사생활침해, 명예훼손, 인신공격의 경우<br>-미풍양속저해 및 음란사진이나 음란자료를 담고 있는 경우<br>-확인되지 않거나 근거없는 내용일 경우<br>-저작권에 위배되는 경우<br>-광고 게시물은 어떠한 경우에서든 게시물 삭제 및 서비스정지 처리됩니다.</p>
        </form>
    </div>
    <script>
        $(document).ready(function(){
            $('#submit_btn').on("click", function(){
                let form= $('#form');
                form.attr("action", "<c:url value='/customer/inquiry'/>");
                form.attr("method", "post");
                form.submit();
            });
            $('#cancel_btn').on("click", function(){
                let form= $('#form');
                form.attr("action", "<c:url value='/customer/inquirylist'/>");
                form.attr("method", "get");
                form.submit();
            });
        });
    </script>
</div>
</body>
</html>