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
    <link rel="stylesheet" href="/css/user/user_join.css">
<style>
    .label-header{
        margin-right: 490px;
    }

    img{
        width: 150px;
    }
    .img-box{
        height: 150px;
        width: 150px;
        padding: 0px 5px;
    }

</style>
</head>
<body>
<h2 class="login-title" id="loginTitle">회원 정보 수정</h2>
<br><br>
<div style="display: flex; justify-content: center;">
    <form action="<c:url value="/user/usrMod"/>" method="POST" onsubmit="">
        <div style="display: flex; flex-direction: column; align-items: center;">
            <label class="label-header" for="">아이디</label>
            <input class="input-field" type="text" name="usr_id" value="<c:out value='${userDto.usr_id}'/>" readonly><br>
<%--            <label class="label-header" for="">비밀번호</label>--%>
<%--            <input class="input-field" type="text" name="pwd" value="<c:out value='${userDto.pwd}'/>" ><br>--%>
            <input type="button" value="비밀번호 바꾸기"><br/>
            <label class="label-header" for="">이름</label>
            <input class="input-field" type="text" name="usr_nm" value="<c:out value='${userDto.usr_nm}'/>" readonly><br>
            <label class="label-header" for="">생년월일</label>
            <input class="input-field" type="text" name="brth" value="<c:out value='${userDto.brth}'/>" readonly><br>
            <label class="label-header" for="">성별</label>
                <div class="select"  style="display: flex; justify-content: space-between; padding-bottom: 35px;">
                    <input type="radio" id="select" name="gndr" value="남성" readonly><label for="select" id="man">남성</label>
                    <input type="radio" id="select2" name="gndr" value="여성" readonly><label for="select2" id="woman">여성</label>
                </div>
            <label class="label-header" for="">이메일</label>
            <input class="input-field" type="text" name="email" value="<c:out value='${userDto.email}'/>"><br>
            <label class="label-header" for="">핸드폰</label>
            <input class="input-field" type="text" name="phn" style="margin-bottom: 50px" value="<c:out value='${userDto.phn}'/>"><br>
            <label for="">프로필을 선택하세요</label>
            <div style="display: flex; justify-content: space-around; margin-top: 30px;">
                <span  class="span-prf-image">
                    <div class="img-box">
                        <img src="../image/user/Bikini.png" alt="" >
                    </div>
                    <input type="radio" name="cmn_cd_prf_img" value="3A">
                </span>

                <span class="span-prf-image">
                    <div class="img-box">
                        <img src="../image/user/Dancing.png" alt="" style="width: 150px;">
                    </div>
                    <input type="radio" name="cmn_cd_prf_img" value="3B">
                </span>

                <span class="span-prf-image">
                    <div class="img-box">
                        <img src="../image/user/Doggie.png" alt="" style="width: 150px;">
                    </div>
                    <input type="radio" name="cmn_cd_prf_img" value="3C">
                </span>

                <span class="span-prf-image">
                     <div class="img-box">
                        <img src="../image/user/Laying Down.png" alt="" style="width: 150px;">
                     </div>
                    <input type="radio" name="cmn_cd_prf_img" value="3D">
                </span>

                <span class="span-prf-image">
                    <div class="img-box">
                        <img src="../image/user/Meditating.png" alt="" style="width: 150px;">
                    </div>
                    <input type="radio" name="cmn_cd_prf_img" value="3E">
                </span>
            </div>
            <div style="text-align: center; padding: 50px 50px;">
                <input type="button" id="goback" onclick="history.back()" value="취소">
                <button id="submit">수정하기</button>
                <div style="text-align: center">
                    <a id="openDelUsr">탈퇴하기</a>
                </div>
            </div>

        </div>
    </form>
</div>

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


<div class="modal hidden" id="delModal">
    <div class="modal__overlay" id="delOverlay"></div>
    <div class="modal__content" style="width: 400px; height: 400px; padding: 30px;">
        <h2 style="font-weight: bolder; font-size: x-large; padding-right: 150px;">탈퇴 사유 선택 (필수)</h2>
        <hr>
            <div style="height: 300px; font-size: 18px; display: flex; flex-direction: column;
            justify-content: space-evenly; align-items: flex-start; margin-top: 20px">
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
                <button id="openPwCheck">탈퇴하기</button>
            </div>
    </div>
</div>

<div class="modal hidden" id="pwCheckModal">
    <div class="modal__overlay" id="pwCheckOverlay"></div>
    <div class="modal__content" style="width: 420px; height: 200px; padding: 30px 20px;">
        <div>
            <h2 style="font-weight: bolder; font-size: x-large; padding-right: 220px;">비밀번호 입력</h2>
            <h2 id="auth-phn-info" style="padding-top: 10px; padding-right: 20px; font-size: 15px;"><i class="fa fa-check" aria-hidden="true"></i> &nbsp;회원 탈퇴 진행을 위한 비밀번호를 입력해주세요</h2>
            <hr style="width: 400px;">
            <form name="detailForm" method="post">
                <input id="drpCd" type="hidden" name="cmn_cd_drp" value="">
                <div style="font-size: 18px;">
                    <div class="form-check">
                        <span style="font-size: 15px;">비밀번호</span>
                        <input class="pwd-check-input" name="pwd" placeholder="비밀번호를 입력해주세요">
                    </div>
                    <button id="delBtn">탈퇴하기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="/js/user.js"></script>
<script>
    let msg = "${msg}";
    if(msg=="MOD_ERR")   alert("회원정보 수정 도중 문제가 발생했습니다. 다시 시도해주세요.");
    if(msg=="DEL_ERR")    alert("회원 탈퇴 과정에 문제가 발생했습니다. 다시 시도해주세요.");

    $.fn.radioSelect = function(val) {
        this.each(function() {
            var $this = $(this);
            if($this.val() == val)
                $this.attr('checked', true);
        });
        return this;
    };

    $(":radio[name='gndr']").radioSelect('${userDto.gndr}');

    <!-- 탈퇴 실행 -->
    $(function() {
        $( "#delBtn:contains('탈퇴하기')" ).on("click" , function() {
            if(confirm('정말 탈퇴하시겠습니까?')){
                fncUsrDel();
            }
            else{
                return false;
            }
        });
    });

    function fncUsrDel(){
        document.detailForm.action='/user/usrPwdCheck';
        document.detailForm.submit();
    }
</script>
</body>
</html>
