<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>회원정보수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <style>
        input[readonly] {background-color:gray;}
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
    <div class="select">
        <input type="radio" id="select" name="gndr"><label for="select">남성</label>
        <input type="radio" id="select2" name="gndr"><label for="select2">여성</label>
    </div>
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" value="<c:out value='${userDto.email}'/>"><br>
    <label for="">핸드폰</label>
    <input class="input-field" type="text" name="phn" value="<c:out value='${userDto.phn}'/>"><br>
    <label for="">프로필 이미지 코드</label>
    <input class="input-field" type="text" name="cmn_cd_prf_img" value="<c:out value='${userDto.cmn_cd_prf_img}'/>"><br>
    <button>수정</button>
</form>

<a>회원탈퇴</a>
<!--
    1. 모달 폼 작성
    2. 모달에 넣은 데이터 끌고 오기 (탈퇴 사유 코드)
    3. 비밀번호 입력 모달 폼 작성
    4. 세션아이디와 일치하는지 확인후
    5. 탈퇴 메서드 실행
-->

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#usrDelModal">
    회원탈퇴
</button>

<!-- Modal -->
<div class="modal fade" id="usrDelModal" tabindex="-1" aria-labelledby="usrDelModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="usrDelModalLabel">탈퇴 사유 선택 (필수)</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="detailForm" method="post" enctype="multipart/form-data">
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
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                        <button type="button" id="delBtn" class="btn btn-primary">탈퇴하기</button>
                    </div>
                </form>
            </div>
            </div>

        </div>
    </div>
</div>

<script>
    let msg = "${msg}";
    if(msg=="MOD_OK")    alert("회원정보 수정이 완료되었습니다.");
    if(msg=="MOD_ERR")    alert("회원정보 수정에 실패했습니다. 다시 시도해주세요.");
    if(msg=="DEL_ERR")    alert("회원 탈퇴 과정에 문제가 발생했습니다. 다시 시도해주세요.");

    $(function() {
        $( "#delBtn:contains('탈퇴하기')" ).on("click" , function() {
            var radioVal = $('input[name="cmn_cd_drp"]:checked').val();
            alert(radioVal);
            fncAddComplain();
        });
    });

    function fncAddComplain(){
        document.detailForm.action='/user/usrDel';
        document.detailForm.submit();
    }

</script>
</body>
</html>
