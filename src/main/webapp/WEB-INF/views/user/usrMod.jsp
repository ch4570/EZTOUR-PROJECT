<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>회원정보수정</title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/46b11191c3.js" crossorigin="anonymous"></script>
    <title>document</title>
    <link rel="stylesheet" href="/css/common/header.css" />

    <style>
        input[readonly] {background-color:gray;}

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
<h1>
    회원정보수정 공사중 ...
</h1>
<br><br>
<form action="<c:url value="/user/usrMod"/>" method="POST" onsubmit="">
    <label for="">아이디</label>
    <input class="input-field" type="text" name="usr_id" value="<c:out value='${userDto.usr_id}'/>" readonly><br>
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" value="<c:out value='${userDto.pwd}'/>" ><br>
    <label for="">이름</label>
    <input class="input-field" type="text" name="usr_nm" value="<c:out value='${userDto.usr_nm}'/>" readonly><br>
    <label for="">생년월일</label>
    <input class="input-field" type="text" name="brth" value="<c:out value='${userDto.brth}'/>" readonly><br>
    <label for="">성별</label>
    <div class="select"/>
        <input type="radio" id="select" name="gndr" value="남성" readonly><label for="select">남성</label>
        <input type="radio" id="select2" name="gndr" value="여성" readonly><label for="select2">여성</label>
    </div>
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" value="<c:out value='${userDto.email}'/>"><br>
    <label for="">핸드폰</label>
    <input class="input-field" type="text" name="phn" value="<c:out value='${userDto.phn}'/>"><br>
    <label for="">프로필 이미지 코드</label>
    <input class="input-field" type="text" name="cmn_cd_prf_img" value="<c:out value='${userDto.cmn_cd_prf_img}'/>"><br>
    <button>수정</button>
</form>

<!--
    1. 모달 폼 작성
    2. 모달에 넣은 데이터 끌고 오기 (탈퇴 사유 코드)
    3. 비밀번호 입력 모달 폼 작성
    4. 세션아이디와 일치하는지 확인후
    5. 탈퇴 메서드 실행
-->

<%--<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#usrDelModal">--%>
<%--    회원탈퇴--%>
<%--</button>--%>


<button id="openDelUsr">탈퇴하기</button>
<div class="modal hidden" id="delModal">
    <div class="modal__overlay" id="delOverlay"></div>
    <div class="modal__content">
        <h5>탈퇴 사유 선택 (필수)</h5>

            <div style="margin-left: 30px; font-size: 18px;">
                <div class="form-check">
                    <input class="form-check-input" id="4a" type="radio" name="cmn_cd_drp"
                           value="4a" > <label class="form-check-label"> 여행 상품에 대한 불만 </label>
                </div>
                <br/>
                <div class="form-check">
                    <input class="form-check-input" id="4b" type="radio" name="cmn_cd_drp"
                           value="4b" checked> <label class="form-check-label"> 예약 과정의 어려움 </label>
                </div>
                <br/>
                <div class="form-check">
                    <input class="form-check-input" id="4c" type="radio" name="cmn_cd_drp"
                           value="4c"> <label class="form-check-label"> 가격 불만 </label>
                </div>
                <br/>
                <div class="form-check">
                    <input class="form-check-input" id="4d" type="radio" name="cmn_cd_drp"
                           value="4d"> <label class="form-check-label"> 회원 혜택 부족 </label>
                </div>
                <br/>
                <div class="form-check">
                    <input class="form-check-input" id="4e" type="radio" name="cmn_cd_drp"
                           value="4e"> <label class="form-check-label"> 불친절 및 지연 </label>
                </div>
                <br/>
                <hr>
                <button id="openPwCheck">탈퇴하기</button>
            </div>
        <button id="closedelBtn"> 닫기 </button>
    </div>
</div>

<div class="modal hidden" id="pwCheckModal">
    <div class="modal__overlay" id="pwCheckOverlay"></div>
    <div class="modal__content">
        <h5>비밀번호 입력</h5>

        <form name="detailForm" method="post">
            <input id="drpCd" type="hidden" name="cmn_cd_drp" value="">
            <div style="margin-left: 30px; font-size: 18px;">
                <div class="form-check">
                    <input class="form-check-input"  name="pwd">

                </div>
                <br/>

                <br/>
                <hr>
                <button id="delBtn">탈퇴하기</button>
            </div>
        </form>
        <button id="closePwCheckBtn"> 닫기 </button>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="/js/user.js"></script>
</body>
</html>
