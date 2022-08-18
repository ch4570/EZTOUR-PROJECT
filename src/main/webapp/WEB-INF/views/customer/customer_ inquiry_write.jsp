<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--DTO값 수정--%>
<c:forEach var="CustomerInquryDto" items="${list}"></c:forEach>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Document</title>
</head>
<div class="wrap">
    <div class="content">
        <h1>문의하기</h1>
        <p>문의 유형</p>
        <form action="" id="form">
            <select>
                <option value="" disabled selected>선택</option>
                <option>예약문의</option>
                <option>결제문의</option>
                <option>취소문의</option>
                <option>기타문의</option>
            </select>
            <select>
                <option value="" disabled selected>선택</option>
                <option>예약문의</option>
                <option>결제문의</option>
                <option>취소문의</option>
                <option>기타문의</option>
            </select>

            <p>지역</p>
            <select>
                <option value="" disabled selected>선택</option>
                <option>서울</option>
                <option>경기도</option>
                <option>인천</option>
                <option>강원도</option>
                <option>전라도</option>
                <option>경상도</option>
                <option>충청도</option>
                <option>제주도</option>
            </select>

            <p>이메일</p>
            <input type="text" name="email" value="${CustomerInquiryDto.email}" placeholder="내용을 입력해주세요."> &nbsp;@&nbsp;
            <select>
                <option disabled selected>직접입력</option>
                <option>naver.com</option>
                <option>daum.net</option>
                <option>gmail.com</option>
                <option>nate.com</option>
            </select>

            <p>연락처</p>
            <select>
                <option>선택</option>
                <option>010</option>
                <option>011</option>
                <option>012</option>
                <option>013</option>
            </select>

            <p>연락처</p>
            <select>
                <option>선택</option>
                <option>010</option>
                <option>011</option>
                <option>012</option>
                <option>013</option>
            </select>
            <input type="text" name="phn" value="${CustomerInquiryDto.phn}" placeholder="내용을 입력해주세요.">


            <p>제목</p>
            <input type="text" placeholder="제목을 입력하세요"><br/>

            <p>제안 내용</p>
            <textarea placeholder="내용을 입력해주세요" name="prop_cont" value="${CustomerInquiryDto.qna_cont}" rows="10" cols="70"></textarea>
        </form>

        <p>이용안내</p>
        <p>이용안내입니다.이용안내입니다.이용안내입니다.이용안내입니다.이용안내입니다.이용안내입니다.이용안내입니다.</p>

<%--        이동주소 입력--%>
        <button type="button" id="cancelBtn" onclick="location.href='<c:url value="#"/>'">취소</button>
        <button type="button" id="writeBtn" onclick="location.href='<c:url value="#"/>'">등록</button>
    </div>
</div>
<script>
<%--    입력 값 customer_inquiry_write로 보내기--%>
    $(document).ready(function (){
        $('#writeBtn').on("click", function(){
            let form = ${'#form'};
            form.attr("action", "<c:url value='/customer/inquiryWrite'/>");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
<script>
    let msg ="${msg}";
    if(msg=="WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 시도해주세요.");
</script>
</body>
</html>