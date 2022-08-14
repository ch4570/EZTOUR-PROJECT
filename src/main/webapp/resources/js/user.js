<!-- 비밀번호 체크 모달 -->
const openPwCheck = document.getElementById("openPwCheck");
const pwCheckModal = document.querySelector("#pwCheckModal");
const pwCheckOverlay = pwCheckModal.querySelector("#pwCheckOverlay");
const closePwCheckBtn = pwCheckModal.querySelector("#closePwCheckBtn")
const openPwCheckModal = () => {
    delModal.classList.add("hidden")
    pwCheckModal.classList.remove("hidden");
}
const closePwCheckModal = () => {
    pwCheckModal.classList.add("hidden")
}
openPwCheck.addEventListener("click", openPwCheckModal);
closePwCheckBtn.addEventListener("click", closePwCheckModal);
pwCheckOverlay.addEventListener("click", closePwCheckModal);

<!-- 탈퇴 사유 모달 -->
const openDelButton = document.getElementById("openDelUsr");
const delModal = document.querySelector("#delModal");
const delOverlay = delModal.querySelector("#delOverlay");
const delBtn = delModal.querySelector("#closedelBtn")
const openDelUsrModal = () => {
    delModal.classList.remove("hidden");

}
const closeDelUsrModal = () => {
    delModal.classList.add("hidden")
}
openDelButton.addEventListener("click", openDelUsrModal);
delBtn.addEventListener("click", closeDelUsrModal);
delOverlay.addEventListener("click", closeDelUsrModal);

<!-- 에러/성공 메세지 -->
msg = "${msg}";
if(msg=="MOD_OK")    alert("회원정보 수정이 완료되었습니다.");
if(msg=="MOD_ERR")    alert("회원정보 수정에 실패했습니다. 다시 시도해주세요.");
if(msg=="DEL_ERR")    alert("회원 탈퇴 과정에 문제가 발생했습니다. 다시 시도해주세요.");



<!-- 탈퇴사유코드 다음 모달에 전달 -->
var drpRadioVal;
$(function() {
    $( "#openPwCheck:contains('탈퇴하기')" ).on("click" , function() {
        drpRadioVal = $('input[name="cmn_cd_drp"]:checked').val();
        alert(drpRadioVal);
        $('input[id=drpCd]').attr('value',drpRadioVal);
    });
});

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

<!-- 성별 라디오 체크 -->
$.fn.radioSelect = function(val) {
    this.each(function() {
        var $this = $(this);
        if($this.val() == val)
            $this.attr('checked', true);
    });
    return this;
};

$(":radio[name='gndr']").radioSelect('${userDto.gndr}');