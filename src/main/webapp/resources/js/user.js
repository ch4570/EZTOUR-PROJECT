<!-- 비밀번호 체크 모달 -->
const openPwCheck = document.getElementById("openPwCheck");
const pwCheckModal = document.querySelector("#pwCheckModal");
const pwCheckOverlay = pwCheckModal.querySelector("#pwCheckOverlay");
const openPwCheckModal = () => {
    delModal.classList.add("hidden")
    pwCheckModal.classList.remove("hidden");
}
const closePwCheckModal = () => {
    pwCheckModal.classList.add("hidden")
}
openPwCheck.addEventListener("click", openPwCheckModal);
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

<!-- 탈퇴사유코드 다음 모달에 전달 -->
var drpRadioVal;
$(function() {
    $( "#openPwCheck:contains('탈퇴하기')" ).on("click" , function() {
        drpRadioVal = $('input[name="cmn_cd_drp"]:checked').val();
        alert(drpRadioVal);
        $('input[id=drpCd]').attr('value',drpRadioVal);
    });
});



