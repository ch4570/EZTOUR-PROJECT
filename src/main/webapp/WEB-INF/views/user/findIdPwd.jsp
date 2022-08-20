<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>아이디/비밀번호 찾기</title>
<link rel="stylesheet" href="/css/user/user_find.css">
</head>
<body>

<br/>
<div class="outer-content">
    <div class="inner-content">
            <div class="find__form">
                <h2 class="find-title" id="findIdTitle">아이디 찾기</h2>
                <h2 class="find-title" id="findPwdTitle" style="display: none">비밀번호 찾기</h2>

                <nav class="find-nav">
                    <button  type="button" id="findId" onclick="findIdView()" >아이디 찾기</button>
                    <button  type="button" id="findPwd" onclick="findPwdView()" >비밀번호 찾기</button>
                </nav>

                <div class="form-inp">
                    <input id="usr_nm" type="text" name="usr_nm"  placeholder="이름 입력" >
                    <input id="phn" name="phn" placeholder="핸드폰 번호 입력">
                    <input class="hidden" type="button" id="checkAuthBtn" value="인증하기">
                    <button id="findIdBtn" onclick="authPhn()">인증번호 보내기</button>
                </div>

                <div class="form-inp-find" style="display:none;">
                    <input id="usrid" type="text" name="usr_id" placeholder="아이디를 입력하세요." >
                    <input id="usrnm"  name="usr_nm" placeholder="이름을 입력하세요.">
                    <div class="input-basic">
                        <input type="text" class="input-field" name="email1" id="email1"
                               onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"placeholder="이메일" style="width:250px;" >
                        <select class="input-field" name="email2" id="email2" style="width:241px;">
                            <option>@naver.com</option>
                            <option>@daum.net</option>
                            <option>@gmail.com</option>
                            <option>@hanmail.com</option>
                            <option>@yahoo.co.kr</option>
                        </select>
                        <div>
                            <input class="mail-check-input" placeholder="인증번호 6자리를 입력해주세요." disabled="disabled" maxlength="6">
                            <span id="mail-check-warn"></span>
                            <button id="mail-Check-Btn">인증하기</button>
                        </div>
            </div>
        </form>
    </div>
</div>

<!-- 아이디/비밀번호 찾기 결과 모달창 -->
<div class="modal hidden" id="resultModal">
    <div class="modal__overlay" id="resultOverlay"></div>
    <div class="modal__content">
        <br/>
        <h2 id="idHead">회원님의 아이디는 ...</h2>
        <h2 id="pwdHead" style="display: none">회원님의 비밀번호는 ...</h2>
        <hr>
            <div style="margin-left: 30px; font-size: 18px;">
                <div class="form-check" id="idChk">
                    <label>회원님의 아이디는 <input class="form-check-input" name="resultId" value="" readonly></label>
                </div>
                <div class="form-check" id="pwdChk" style="display: none">
                <label>회원님의 비밀번호는 <input class="form-check-input" name="resultPwd" value="" readonly></label>
                </div>
                <br/>
                <hr>
            </div>
        <button id="closeresultModalBtn"> 닫기 </button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    function findPwdView(){
        $(".form-inp").hide();
        $("#findTitle").hide();
        $(".form-inp-find").show();
        $("#rsvTitle").show();
        $("#findPwd").css({
            'border-bottom': '3px solid #333333',
            'color' : '#333333'
        });

        $("#findId").css({
            'border-bottom': '3px solid #E6E6E6',
            'color' : 'gray'
        });
    }

    function findIdView() {
        $("#rsvTitle").hide();
        $(".form-inp-find").hide();
        $(".form-inp").show();
        $("#findTitle").show();
        $("#findId").css({
            'border-bottom': '3px solid #333333',
            'color': '#333333'
        });

        $("#findPwd").css({
            'border-bottom': '3px solid #E6E6E6',
            'color': 'gray'
        });
    }

    function authPhn(){
        let phn = $("#phn").val()
        const findIdBtn = document.querySelector("#findIdBtn");
        const checkAuthBtn = document.querySelector("#checkAuthBtn");
        $.ajax({
            type:'GET',
            url: '/authPhn/' + phn,
            success : function(msg){
                alert(msg);
                findIdBtn.classList.add("hidden")
                checkAuthBtn.classList.remove("hidden")
                $("#checkAuthBtn").before("<input id='checkNum' name='checkNum' placeholder='인증번호를 입력해주세요.'>");
            },
            error   : function(){ alert("잘못된 요청입니다.") }
        });
    }

    $("#checkAuthBtn").click(function(){
        let checkNum = $("#checkNum").val();
        let phn = $("#phn").val();
        let usr_nm = $("#usr_nm").val();

        $.ajax({
            type:'GET',
            url: '/checkAuthNum/' + checkNum,
            success : function(msg){
                alert("인증에 성공했습니다.");
                $.ajax({
                    type:'POST',
                    url: '/findId/' +usr_nm+ "/" +phn,
                    success : function(usr_id){
                        // 1. 모달 열기
                        openresultModal();
                        // 2. 모달에 usr_id 전달
                        $('input[name=resultId]').attr('value',usr_id);
                    },
                    error   : function(){ alert("존재하지 않는 사용자입니다.") }
                });
            },
            error   : function(){ alert("인증번호가 일치하지 않습니다.") }
        });
    });
    var email = $('#email1').val() + $('#email2').val(); // 이메일 주소값 얻어오기!

    $('#mail-Check-Btn').click(function() {
        console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
        const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

        $.ajax({
            type : 'get',
            url : '<c:url value ="/mailCheck?email="/>'+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
            success : function (data) {
                console.log("data : " +  data);
                checkInput.attr('disabled',false);
                code =data;
                alert('인증번호가 전송되었습니다.');
            }
        });
    });

    $('.mail-check-input').blur(function () {
        const inputCode = $(this).val();
        const $resultMsg = $('#mail-check-warn');

        let usr_id = $("#usrId").val();
        let usr_nm = $("#usrNm").val();

        if(inputCode === code){
            $resultMsg.html('인증번호가 일치합니다.');
            $resultMsg.css('color','green');
            $('#mail-Check-Btn').attr('disabled',true);
            $('#email1').attr('readonly',true);
            $('#email2').attr('readonly',true);
            $('#email2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
            $('#email2').attr('onChange', 'this.selectedIndex = this.initialSelect');

            alert("인증에 성공했습니다.")
            $.ajax({
                type:'POST',
                url: '/findPwd/'+usr_id+ "/" +usr_nm+ "/" +email,
                success : function(pwd){
                    // 1. 모달 열기
                    openresultModal();
                    $("#pwdHead").show();
                    $("#pwdChk").show();
                    $("#idChk").hide();
                    $("#idHead").hide();

                    // 2. 모달에 usr_id 전달
                    $('input[name=resultPwd]').attr('value',pwd);
                },
                error   : function(){ alert("존재하지 않는 사용자입니다.") }
            });
        }else{
            $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
            $resultMsg.css('color','red');
        }
    });

    const resultModal = document.querySelector("#resultModal");
    const resultOverlay = resultModal.querySelector("#resultOverlay");
    const closeresultModalBtn = resultModal.querySelector("#closeresultModalBtn")
    const openresultModal = () => {
        resultModal.classList.remove("hidden");
    }
    const closeresultModal = () => {
        resultModal.classList.add("hidden")
    }
    closeresultModalBtn.addEventListener("click", closeresultModal);
    resultOverlay.addEventListener("click", closeresultModal);

</script>
</body>
</html>
